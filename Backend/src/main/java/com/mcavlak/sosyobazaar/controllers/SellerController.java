package com.mcavlak.sosyobazaar.controllers;

import com.mcavlak.sosyobazaar.annotations.Authenticated;
import com.mcavlak.sosyobazaar.annotations.OnlySeller;
import com.mcavlak.sosyobazaar.annotations.PermitAllCustom;
import com.mcavlak.sosyobazaar.dtos.SellerDto;
import com.mcavlak.sosyobazaar.dtos.SellerPhotosDto;
import com.mcavlak.sosyobazaar.dtos.SellerWithoutDataDto;
import com.mcavlak.sosyobazaar.dtos.UpdateContactDto;
import com.mcavlak.sosyobazaar.models.entities.ProductPhoto;
import com.mcavlak.sosyobazaar.services.SellerService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller")
@CrossOrigin
@PermitAllCustom
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @Authenticated
    @PutMapping("/{id}/follow")
    public void follow(@PathVariable Long id) {
        sellerService.addFollowerToSeller(id);
    }

    @Authenticated
    @PutMapping("/{id}/unfollow")
    public void unfollow(@PathVariable Long id) {
        sellerService.removeFollowerFromSeller(id);
    }

    @GetMapping
    public ResponseEntity<List<SellerDto>> findAll() {
        return ResponseEntity.ok(sellerService.findAll());
    }

    @GetMapping("/province/{provinceId}")
    public List<SellerWithoutDataDto> findAllByProvinceId(@PathVariable Long provinceId) {
        return sellerService.findAllByProvinceId(provinceId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> findBySellerId(@PathVariable Long id) {
        return ResponseEntity.ok(sellerService.findBySellerId(id));
    }

    @PutMapping("/upload/profilePhoto")
    @OnlySeller
    public void uploadProfilePhoto(@RequestParam(name = "file") MultipartFile file) {
        sellerService.uploadProfilePhoto(file);
    }

    @PutMapping("/upload/coverPhoto")
    @OnlySeller
    public void uploadCoverPhoto(@RequestParam(name = "file") MultipartFile file) {
        sellerService.uploadCoverPhoto(file);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SellerWithoutDataDto>> findByStoreNameOrIndustryName(@RequestParam(name = "searchText") String searchText) {
        return ResponseEntity.ok(sellerService.findAllByStoreNameOrIndustryName(searchText));
    }

    @GetMapping("/search/byProvince")
    public ResponseEntity<List<SellerWithoutDataDto>> findByStoreNameOrIndustryNameAndProvinceId(@RequestParam(name = "searchText") String searchText, @RequestParam(name = "provinceId") Long provinceId) {
        return ResponseEntity.ok(sellerService.findAllByProvinceIdAndStoreNameOrIndustryName(provinceId, searchText));
    }


    @PutMapping("/updateContact")
    public void updateContact(@RequestBody UpdateContactDto updateContactDto){

        sellerService.updateContact(updateContactDto);
    }
    @GetMapping(value = "/{id}/profilePhoto", produces = MediaType.ALL_VALUE)
    public ResponseEntity getProfilePhoto(@PathVariable Long id) throws SQLException {
        SellerPhotosDto sellerPhotosDto = sellerService.getPhotos(id);
        if(Optional.ofNullable(sellerPhotosDto.getProfilePhoto()).isPresent()){
            InputStream inputStream =  new ByteArrayInputStream(sellerPhotosDto.getProfilePhoto());
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            ResponseEntity entity = ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                    .body(inputStreamResource);
            return entity;
        }
       return  null;
    }
    @GetMapping(value = "/{id}/coverPhoto", produces = MediaType.ALL_VALUE)
    public ResponseEntity getCoverPhoto(@PathVariable Long id) throws SQLException {
        SellerPhotosDto sellerPhotosDto = sellerService.getPhotos(id);
         if(Optional.ofNullable(sellerPhotosDto.getCoverPhoto()).isPresent()){
             InputStream inputStream =  new ByteArrayInputStream(sellerPhotosDto.getCoverPhoto());
             InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

             ResponseEntity entity = ResponseEntity.status(HttpStatus.OK)
                     .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                     .body(inputStreamResource);
             return entity;
         }
      return null;
    }


}
