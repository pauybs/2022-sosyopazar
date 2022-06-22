package com.mcavlak.sosyobazaar.managers;

import com.mcavlak.sosyobazaar.annotations.OnlyCustomer;
import com.mcavlak.sosyobazaar.dtos.SellerDto;
import com.mcavlak.sosyobazaar.dtos.SellerPhotosDto;
import com.mcavlak.sosyobazaar.dtos.SellerWithoutDataDto;
import com.mcavlak.sosyobazaar.dtos.UpdateContactDto;
import com.mcavlak.sosyobazaar.exception.EntityNotFoundException;
import com.mcavlak.sosyobazaar.mappers.SellerMapper;
import com.mcavlak.sosyobazaar.mappers.SellerPhotosDtoMapper;
import com.mcavlak.sosyobazaar.mappers.SellerWithoutPhotoDtoMapper;
import com.mcavlak.sosyobazaar.mappers.base.SellerWithoutDataMapper;
import com.mcavlak.sosyobazaar.models.entities.Province;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import com.mcavlak.sosyobazaar.repositories.SellerRepository;
import com.mcavlak.sosyobazaar.services.ProvinceService;
import com.mcavlak.sosyobazaar.services.SellerService;
import com.mcavlak.sosyobazaar.utils.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SellerManager implements SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final SellerWithoutDataMapper sellerWithoutDataMapper;
    private final ProvinceService provinceService;
    private final SellerWithoutPhotoDtoMapper sellerWithoutPhotoDtoMapper;
    private final SellerPhotosDtoMapper sellerPhotosDtoMapper;

/*
    @Override
    public void addFollowerToSeller(AddFollowerToSellerDto addFollowerToSellerDto) {

        Seller seller = findDataById(addFollowerToSellerDto.getSellerId());

        Set<Customer> followerList = seller.getFollowers();
        followerList.add(SecurityContextUtil.getCurrentCustomer());
        sellerRepository.save(seller);
    }*/

    @Override
    public Seller findDataById(Long id) {
        return sellerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Satıcı bulunamadı."));
    }

    @Override
    public List<SellerWithoutDataDto> findAllByProvinceId(Long provinceId) {
        List<Seller> sellerList = sellerRepository.findAllByProvince_Id(provinceId);
        log.info(sellerList.toString());
        List<SellerWithoutDataDto> sellerDtoList = sellerWithoutPhotoDtoMapper.entityListToDtoList(sellerList);
        return sellerDtoList;
    }

    @Override
    public List<SellerWithoutDataDto> findAllByProvinceIdAndStoreNameOrIndustryName(Long provinceId, String searchName) {
        return sellerWithoutDataMapper.entityListToDtoList(sellerRepository.findByStoreNameOrIndustryNameByProvince(searchName, searchName, provinceId));
    }

    @Override
    public List<SellerWithoutDataDto> findAllByStoreNameOrIndustryName(String searchName) {
        return sellerWithoutDataMapper.entityListToDtoList(sellerRepository.findByStoreNameOrIndustryName(searchName, searchName));
    }

    @Override
    public List<SellerDto> findAll() {
        List<SellerDto> list = sellerMapper.entityListToDtoList(sellerRepository.findAll());
        return list;
    }

    @Override
    public SellerDto findBySellerId(Long id) {
        log.info(sellerRepository.getById(id).toString());
        return sellerMapper.entityToDto(sellerRepository.getById(id));
    }

    @Override
    public void addFollowerToSeller(Long id) {
        Customer customer = SecurityContextUtil.getCurrentCustomer();
        Seller seller = sellerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Satıcı bulunamadı."));
        seller.addFollower(customer);
        sellerRepository.save(seller);
    }

    @Override
    public void removeFollowerFromSeller(Long id) {
        Customer me = SecurityContextUtil.getCurrentCustomer();
        Seller seller = sellerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Satıcı bulunamadı."));
        seller.removeFollower(me);
        sellerRepository.save(seller);
    }

    @Override
    public void uploadProfilePhoto(MultipartFile file) {

        Seller seller = SecurityContextUtil.getCurrentSeller();
        if (Objects.nonNull(seller.getProfilePhoto())) {
            deleteProfilePhoto(seller);
        }
        try {
            Objects.requireNonNull(seller).updateProfilePhoto(file);
            sellerRepository.save(seller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadCoverPhoto(MultipartFile file) {
        Seller seller = SecurityContextUtil.getCurrentSeller();
        if (Objects.nonNull(seller.getProfilePhoto())) {
            deleteCoverPhoto(seller);
        }
        try {
            Objects.requireNonNull(seller).updateCoverPhoto(file);
            sellerRepository.save(seller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteProfilePhoto(Seller seller) {
        seller.deleteProfilePhoto();
        sellerRepository.save(seller);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteCoverPhoto(Seller seller) {
        seller.deleteCoverPhoto();
        sellerRepository.save(seller);
    }

    @Override
    @OnlyCustomer
    public List<SellerDto> myFollowSeller() {

        Customer currentCustomer = SecurityContextUtil.getCurrentCustomer();
        Set<Seller> myFollows = currentCustomer.getFollows();
        List<Seller> sellerList = new ArrayList<>(myFollows);
        return sellerMapper.entityListToDtoList(sellerList);

    }

    @Override
    public SellerPhotosDto getPhotos(Long id) {
        return sellerPhotosDtoMapper.entityToDto(findDataById(id));
    }

    @Override
    public void updateContact(UpdateContactDto updateContactDto) {
        Seller seller = SecurityContextUtil.getCurrentSeller();
        Province province= provinceService.findDataById(updateContactDto.getProvinceId());
        sellerRepository.save(Seller.updateContact(seller,updateContactDto,province));
    }


}
