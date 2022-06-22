package com.mcavlak.sosyobazaar.repositories;

import com.mcavlak.sosyobazaar.models.entities.SellerComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerCommentRepository extends JpaRepository<SellerComment, Long> {

    List<SellerComment> findBySeller_IdOrderByCreatedDateTimeDesc(Long sellerId);

}
