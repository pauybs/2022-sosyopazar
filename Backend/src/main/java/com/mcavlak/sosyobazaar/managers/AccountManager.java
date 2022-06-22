package com.mcavlak.sosyobazaar.managers;

import com.mcavlak.sosyobazaar.dtos.*;
import com.mcavlak.sosyobazaar.enums.Role;
import com.mcavlak.sosyobazaar.exception.IncorrectEntryException;
import com.mcavlak.sosyobazaar.mappers.CustomerMapper;
import com.mcavlak.sosyobazaar.mappers.SellerMapper;
import com.mcavlak.sosyobazaar.mappers.base.SellerWithoutDataMapper;
import com.mcavlak.sosyobazaar.models.CustomUserDetails;
import com.mcavlak.sosyobazaar.models.entities.Industry;
import com.mcavlak.sosyobazaar.models.entities.Province;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import com.mcavlak.sosyobazaar.models.entities.users.User;
import com.mcavlak.sosyobazaar.repositories.UserRepository;
import com.mcavlak.sosyobazaar.security.JwtTokenUtil;
import com.mcavlak.sosyobazaar.services.AccountService;
import com.mcavlak.sosyobazaar.services.IndustryService;
import com.mcavlak.sosyobazaar.services.ProvinceService;
import com.mcavlak.sosyobazaar.utils.SecurityContextUtil;
import com.mcavlak.sosyobazaar.validations.Validator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountManager implements AccountService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Validator validator;
    private final IndustryService industryService;
    private final ProvinceService provinceService;
    private final CustomerMapper customerMapper;
    private final SellerMapper sellerMapper;
    private final SellerWithoutDataMapper sellerWithoutDataMapper;

    public AccountManager(UserRepository userRepository,
                          AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          Validator validator,
                          IndustryService industryService,
                          ProvinceService provinceService, CustomerMapper customerMapper, SellerMapper sellerMapper, SellerWithoutDataMapper sellerWithoutDataMapper) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.validator = validator;
        this.industryService = industryService;
        this.provinceService = provinceService;
        this.customerMapper = customerMapper;
        this.sellerMapper = sellerMapper;
        this.sellerWithoutDataMapper = sellerWithoutDataMapper;

    }


    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User currentUser = customUserDetails.getUser();

            //bura hibernate follower ve follows çekmesi için, yoksa hibernate çekmiyor
            if(currentUser.getRole() == Role.ROLE_SELLER){
                Seller seller = (Seller) currentUser;
                seller.getFollowers().size();
            }
            if(currentUser.getRole() == Role.ROLE_CUSTOMER){
                Customer customer = (Customer) currentUser;
                customer.getFollows().size();
            }

            final String token = jwtTokenUtil.generate(customUserDetails, loginRequestDto.isRememberMe());

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);

            return new LoginResponseDto(currentUser.getUsername(), token, currentUser.getRole(),currentUser.getId());

        } catch (BadCredentialsException badCredentialsException) {

            throw new IncorrectEntryException("Kullanıcı Adınızı veya Şifrenizi Yanlış Girdiniz.");
        }
    }

    @Override
    public void registerCustomer(CustomerRegisterRequestDto customerRegisterRequestDto) {
        validator.usernameValidator(customerRegisterRequestDto.getUsername());
        userRepository.save(Customer.create(customerRegisterRequestDto.getUsername(), bCryptPasswordEncoder.encode(customerRegisterRequestDto.getPassword()), customerRegisterRequestDto.getName()));
    }

    @Override
    public void registerSeller(SellerRegisterRequestDto sellerRegisterRequestDto) {
        validator.usernameValidator(sellerRegisterRequestDto.getUsername());
        validator.storeNameValidator(sellerRegisterRequestDto.getStoreName());

        Province province = provinceService.findDataById(sellerRegisterRequestDto.getProvinceId());
        Industry industry = industryService.findDataById(sellerRegisterRequestDto.getIndustryId());

        userRepository.save(Seller.create(sellerRegisterRequestDto.getUsername(), bCryptPasswordEncoder.encode(sellerRegisterRequestDto.getPassword()), sellerRegisterRequestDto.getStoreName(), province, industry));
    }

    @Override
    public UserDto getMe() {

        User user = SecurityContextUtil.getCurrentUser();
        if(user.getRole() == Role.ROLE_CUSTOMER){
            CustomerDto c = customerMapper.entityToDto(SecurityContextUtil.getCurrentCustomer());
            return c;
        }
        if(user.getRole() == Role.ROLE_SELLER){
            Seller seller = SecurityContextUtil.getCurrentSeller();
            SellerWithoutDataDto sellerDto = sellerWithoutDataMapper.entityToDto(seller);
            return sellerDto;
        }
        return null;
    }

    @Override
    public Role getMyRole() {
        return SecurityContextUtil.getCurrentUser().getRole();
    }
}
