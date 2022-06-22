package com.mcavlak.sosyobazaar.services;

import com.mcavlak.sosyobazaar.dtos.SellerDto;
import com.mcavlak.sosyobazaar.dtos.SellerPhotosDto;
import com.mcavlak.sosyobazaar.dtos.SellerWithoutDataDto;
import com.mcavlak.sosyobazaar.dtos.UpdateContactDto;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface SellerService {

    //  void addFollowerToSeller(AddFollowerToSellerDto addFollowerToSellerDto);

    Seller findDataById(Long id);

    List<SellerWithoutDataDto> findAllByProvinceId(Long provinceId);

    List<SellerWithoutDataDto> findAllByProvinceIdAndStoreNameOrIndustryName(Long provinceId, String searchName);

    List<SellerWithoutDataDto> findAllByStoreNameOrIndustryName(String searchName);

    List<SellerDto> findAll();

    SellerDto findBySellerId(Long id);

    void addFollowerToSeller(Long id);

    void removeFollowerFromSeller(Long id);

    void uploadProfilePhoto(MultipartFile file);

    void uploadCoverPhoto(MultipartFile file);

    void deleteProfilePhoto(Seller seller);

    void deleteCoverPhoto(Seller seller);

    List<SellerDto> myFollowSeller();

    SellerPhotosDto getPhotos(Long id);

    void updateContact(UpdateContactDto updateContactDto);

}
