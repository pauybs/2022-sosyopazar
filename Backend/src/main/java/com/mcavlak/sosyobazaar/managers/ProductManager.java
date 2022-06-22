package com.mcavlak.sosyobazaar.managers;

import com.mcavlak.sosyobazaar.dtos.ProductDto;
import com.mcavlak.sosyobazaar.dtos.ProductSaveDto;
import com.mcavlak.sosyobazaar.dtos.ProductWithoutSellerPhotoDto;
import com.mcavlak.sosyobazaar.exception.EntityNotFoundException;
import com.mcavlak.sosyobazaar.exception.base.BaseException;
import com.mcavlak.sosyobazaar.mappers.ProductMapper;
import com.mcavlak.sosyobazaar.mappers.ProductWithoutSellerPhotoMapper;
import com.mcavlak.sosyobazaar.models.entities.Product;
import com.mcavlak.sosyobazaar.models.entities.ProductPhoto;
import com.mcavlak.sosyobazaar.repositories.ProductPhotoRepository;
import com.mcavlak.sosyobazaar.repositories.ProductRepository;
import com.mcavlak.sosyobazaar.services.ProductService;
import com.mcavlak.sosyobazaar.utils.TempFileUtil;
import com.mcavlak.sosyobazaar.utils.argumentresolver.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductPhotoRepository productPhotoRepository;
    private final ProductWithoutSellerPhotoMapper productWithoutSellerPhotoMapper;

    @Override
    public void save(ProductSaveDto productSaveDto) {
        //String fileName = StringUtils.cleanPath(Objects.requireNonNull(productSaveDto.getFile().getOriginalFilename()));
        Product product = Product.create(productSaveDto);
        productRepository.save(product);
    }

    @Override
    public ProductDto getByProductId(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        List<ProductPhoto> productPhotoList = productPhotoRepository.findByProduct_Id(productId);
        List<Long> photoIdlist = productPhotoList.stream().map(ProductPhoto::getId).collect(Collectors.toList());
        ProductDto productDto = productMapper.entityToDto(optionalProduct.orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı.")));
        productDto.setPhotoIdList(photoIdlist);
        return productDto;
    }

    @Override
    public void saveProductPhoto(Long productId, FileUploadUtil.FileResult fileResult) {
        try (TempFileUtil.TempFileResult tempFileResult = TempFileUtil.cache(fileResult.getFilename(), fileResult.getFileStream())) {
            Blob blob = BlobProxy.generateProxy(tempFileResult.getFileInputStream(), tempFileResult.getLength());
            Product product = productRepository.findById(productId).get();
            productPhotoRepository.save(ProductPhoto.create(product, blob));
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }

    }

    @Override
    public List<ProductWithoutSellerPhotoDto> findAllByContentOwnerId(Long contentOwnerId) {
        List<ProductWithoutSellerPhotoDto> productWithoutSellerPhotoDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findByContentOwner_Id(contentOwnerId);

        for (Product product : productList){

            List<ProductPhoto> productPhotoList = productPhotoRepository.findByProduct_Id(product.getId());
            List<Long> photoIdlist = productPhotoList.stream().map(ProductPhoto::getId).collect(Collectors.toList());
            ProductWithoutSellerPhotoDto productWithoutSellerPhotoDto = productWithoutSellerPhotoMapper.entityToDto(product);
            productWithoutSellerPhotoDto.setPhotoIdList(photoIdlist);
            productWithoutSellerPhotoDtoList.add(productWithoutSellerPhotoDto);
        }

       // return productWithoutSellerPhotoMapper.entityListToDtoList(productList);
        return productWithoutSellerPhotoDtoList;
    }

    @Override
    public List<ProductWithoutSellerPhotoDto> findAllByProvinceIdAndStoreNameOrProductName(Long provinceId, String searchName) {
        List<ProductWithoutSellerPhotoDto> productWithoutSellerPhotoDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findByProductNameOrStoreNameByProvince(searchName, searchName, provinceId);

        for (Product product : productList){

            List<ProductPhoto> productPhotoList = productPhotoRepository.findByProduct_Id(product.getId());
            List<Long> photoIdlist = productPhotoList.stream().map(ProductPhoto::getId).collect(Collectors.toList());
            ProductWithoutSellerPhotoDto productWithoutSellerPhotoDto = productWithoutSellerPhotoMapper.entityToDto(product);
            productWithoutSellerPhotoDto.setPhotoIdList(photoIdlist);
            productWithoutSellerPhotoDtoList.add(productWithoutSellerPhotoDto);
        }

        // return productWithoutSellerPhotoMapper.entityListToDtoList(productList);
        return productWithoutSellerPhotoDtoList;
    }

    @Override
    public ProductPhoto getProductPhoto(Long id, Long productPhotoId) {
        return productPhotoRepository.findByIdAndProduct_Id(productPhotoId, id);
    }


}
