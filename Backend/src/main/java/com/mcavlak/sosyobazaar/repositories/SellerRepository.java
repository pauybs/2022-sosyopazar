package com.mcavlak.sosyobazaar.repositories;

import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    boolean existsByStoreName(String storeName);

    List<Seller> findAllByProvince_Id(Long provinceId);

    @Query(value = "select distinct sl from Seller sl join sl.industry ind where ( (sl.storeName like CONCAT('%',:storeName,'%')) or (ind.name like CONCAT('%',:industryName,'%')) )")
    List<Seller> findByStoreNameOrIndustryName(@Param("storeName") String storeName, @Param("industryName") String industryName);

    @Query(value = "select distinct sl from Seller sl join sl.industry ind join sl.province pr where ( (sl.storeName like CONCAT('%',:storeName,'%')) or (ind.name like CONCAT('%',:industryName,'%')) ) and pr.id= :provinceId")
    List<Seller> findByStoreNameOrIndustryNameByProvince(@Param("storeName") String storeName, @Param("industryName") String industryName, @Param("provinceId") Long provinceId);


}
