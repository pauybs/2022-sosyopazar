package com.mcavlak.sosyobazaar.services;

import com.mcavlak.sosyobazaar.dtos.ProductDto;
import com.mcavlak.sosyobazaar.dtos.ProductSaveDto;
import com.mcavlak.sosyobazaar.dtos.ProductWithoutSellerPhotoDto;
import com.mcavlak.sosyobazaar.dtos.SellerWithoutDataDto;
import com.mcavlak.sosyobazaar.models.entities.ProductPhoto;
import com.mcavlak.sosyobazaar.utils.argumentresolver.FileUploadUtil;

import javax.persistence.ManyToOne;
import java.util.List;

public interface ProductService {

    void save(ProductSaveDto productSaveDto);

    ProductDto getByProductId(Long productId);
    void saveProductPhoto(Long productId, FileUploadUtil.FileResult fileResult );
    List<ProductWithoutSellerPhotoDto> findAllByContentOwnerId(Long contentOwnerId);
    List<ProductWithoutSellerPhotoDto> findAllByProvinceIdAndStoreNameOrProductName(Long provinceId, String searchName);
    ProductPhoto getProductPhoto(Long id,Long productPhotoId);
}
