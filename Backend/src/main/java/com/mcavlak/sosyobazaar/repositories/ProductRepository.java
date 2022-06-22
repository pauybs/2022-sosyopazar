package com.mcavlak.sosyobazaar.repositories;

import com.mcavlak.sosyobazaar.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByContentOwner_Id(Long contentOwnerId);

    @Query(value = "select distinct prd from Product prd left join fetch prd.contentOwner sl left join fetch sl.province pr where (pr.id= :provinceId) and (sl.storeName like %:storeName%) or (prd.productName like %:productName%) ")
    List<Product> findByProductNameOrStoreNameByProvince(@Param("storeName") String storeName, @Param("productName") String productName, @Param("provinceId") Long provinceId);
    
}
