package com.mcavlak.sosyobazaar.controllers;

import com.mcavlak.sosyobazaar.annotations.Authenticated;
import com.mcavlak.sosyobazaar.annotations.OnlyCustomer;
import com.mcavlak.sosyobazaar.dtos.FollowSellerDto;
import com.mcavlak.sosyobazaar.dtos.SellerDto;
import com.mcavlak.sosyobazaar.services.CustomerService;
import com.mcavlak.sosyobazaar.services.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
@Authenticated
public class CustomerController {

    private final CustomerService customerService;
    private final SellerService sellerService;

    public CustomerController(CustomerService customerService, SellerService sellerService) {
        this.customerService = customerService;
        this.sellerService = sellerService;
    }

    @PostMapping("/follow")
    @OnlyCustomer
    public void followSeller(@RequestBody FollowSellerDto followSellerDto) {
        customerService.followSeller(followSellerDto);
    }

    @GetMapping("/myFollows")
    public ResponseEntity<List<SellerDto>> getMyFollows() {
        return ResponseEntity.ok(sellerService.myFollowSeller());
    }

}
