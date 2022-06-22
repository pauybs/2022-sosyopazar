package com.mcavlak.sosyobazaar.repositories;

import com.mcavlak.sosyobazaar.models.entities.Product;
import com.mcavlak.sosyobazaar.models.entities.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPhotoRepository extends JpaRepository<ProductPhoto,Long> {
    List<ProductPhoto> findByProduct_Id(Long productId);
    ProductPhoto findByIdAndProduct_Id(Long id,Long productId);
}
