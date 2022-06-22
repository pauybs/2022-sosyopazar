package com.mcavlak.sosyobazaar.managers;

import com.mcavlak.sosyobazaar.dtos.FollowSellerDto;
import com.mcavlak.sosyobazaar.exception.EntityNotFoundException;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import com.mcavlak.sosyobazaar.repositories.CustomerRepository;
import com.mcavlak.sosyobazaar.services.CustomerService;
import com.mcavlak.sosyobazaar.services.SellerService;
import com.mcavlak.sosyobazaar.utils.SecurityContextUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerManager implements CustomerService {

    private final CustomerRepository customerRepository;
    private final SellerService sellerService;

    public CustomerManager(CustomerRepository customerRepository,
                           SellerService sellerService) {
        this.customerRepository = customerRepository;
        this.sellerService = sellerService;
    }

    @Override
    public Customer findDataById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Müşteri bulunamamıştır."));
    }

    @Override
    public void followSeller(FollowSellerDto followSellerDto) {

      Seller seller = sellerService.findDataById(followSellerDto.getSellerId());
      Customer currentCustomer = SecurityContextUtil.getCurrentCustomer();

      currentCustomer.getFollows().add(seller);
      customerRepository.save(currentCustomer);

    }
}
