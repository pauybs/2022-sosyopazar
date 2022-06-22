package com.mcavlak.sosyobazaar.controllers;

import com.mcavlak.sosyobazaar.annotations.PermitAllCustom;
import com.mcavlak.sosyobazaar.dtos.ProductDto;
import com.mcavlak.sosyobazaar.dtos.ProductSaveDto;
import com.mcavlak.sosyobazaar.dtos.ProductWithoutSellerPhotoDto;
import com.mcavlak.sosyobazaar.models.entities.ProductPhoto;
import com.mcavlak.sosyobazaar.services.ProductService;
import com.mcavlak.sosyobazaar.utils.argumentresolver.FileUploadUtil;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
@PermitAllCustom
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void save(@Valid @RequestBody ProductSaveDto productSaveDto) {
        productService.save(productSaveDto);
    }

    @GetMapping
    public ResponseEntity<ProductDto> getWithFileByProductId(@RequestParam Long productId) {
        return ResponseEntity.ok(productService.getByProductId(productId));
    }

    @GetMapping("/all")
    public List<ProductWithoutSellerPhotoDto> findAllProductByContentOwnerId(@RequestParam Long contentOwnerId) {
        List<ProductWithoutSellerPhotoDto> productDtoList=productService.findAllByContentOwnerId(contentOwnerId);
        return productService.findAllByContentOwnerId(contentOwnerId);
    }

    @GetMapping("/search/byProvince")
    public ResponseEntity<List<ProductWithoutSellerPhotoDto>> findByStoreNameOrIndustryNameAndProvinceId(@RequestParam(name = "searchText") String searchText, @RequestParam(name = "provinceId") Long provinceId) {
        return ResponseEntity.ok(productService.findAllByProvinceIdAndStoreNameOrProductName(provinceId, searchText));
    }

    @GetMapping(value = "/{id}/photo/{photoId}", produces = MediaType.ALL_VALUE)
    public ResponseEntity getPhotoUrlByActivityAndSequence(@PathVariable Long id, @PathVariable Long photoId) throws SQLException {
        ProductPhoto productPhoto = productService.getProductPhoto(id, photoId);

        InputStream inputStream = productPhoto.getPhoto().getBinaryStream();
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        ResponseEntity entity = ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .body(inputStreamResource);
        return entity;
    }

    @RequestMapping(value = "/{id}/savePhoto/", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public void savePhoto(@PathVariable Long id, FileUploadUtil.FileResult fileResult) {
        productService.saveProductPhoto(id, fileResult);
    }

}
