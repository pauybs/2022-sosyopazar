package com.mcavlak.sosyobazaar.controllers;

import com.mcavlak.sosyobazaar.annotations.Authenticated;
import com.mcavlak.sosyobazaar.annotations.PermitAllCustom;
import com.mcavlak.sosyobazaar.dtos.AverageScoreDto;
import com.mcavlak.sosyobazaar.dtos.SaveSellerCommentDto;
import com.mcavlak.sosyobazaar.dtos.SellerCommentDto;
import com.mcavlak.sosyobazaar.services.SellerCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellerComment")
@CrossOrigin
public class SellerCommentController {

    private final SellerCommentService sellerCommentService;

    public SellerCommentController(SellerCommentService sellerCommentService) {
        this.sellerCommentService = sellerCommentService;
    }
    @Authenticated
    @PostMapping
    public void save(@RequestBody SaveSellerCommentDto saveSellerCommentDto) {
        sellerCommentService.save(saveSellerCommentDto);
    }
    @PermitAllCustom
    @GetMapping
    public ResponseEntity<List<SellerCommentDto>> findAllBySellerId(@RequestParam(name = "sellerId") Long sellerId) {
        return ResponseEntity.ok(sellerCommentService.findAllBySellerId(sellerId));
    }
    @PermitAllCustom
    @GetMapping("/averageScore")
    public ResponseEntity<AverageScoreDto> getAverageScoreBySellerId(@RequestParam(name = "sellerId") Long sellerId) {
        return ResponseEntity.ok(sellerCommentService.getAverageScoreBySellerId(sellerId));
    }


}
