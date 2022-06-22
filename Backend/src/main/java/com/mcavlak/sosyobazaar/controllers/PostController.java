package com.mcavlak.sosyobazaar.controllers;

import com.mcavlak.sosyobazaar.annotations.OnlyCustomer;
import com.mcavlak.sosyobazaar.annotations.OnlySeller;
import com.mcavlak.sosyobazaar.annotations.PermitAllCustom;
import com.mcavlak.sosyobazaar.dtos.PostDto;
import com.mcavlak.sosyobazaar.dtos.PostSaveDto;
import com.mcavlak.sosyobazaar.dtos.SellerDto;
import com.mcavlak.sosyobazaar.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin
@PermitAllCustom
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @OnlyCustomer
    public ResponseEntity<List<PostDto>> findAllPost() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<PostDto>> findAllBySellerId(@PathVariable Long sellerId) {
        return ResponseEntity.ok(postService.findAllBySellerId(sellerId));
    }

    @GetMapping("/myFollow")
    @OnlyCustomer
    public ResponseEntity<List<PostDto>> findAllMyFollow() {
        return ResponseEntity.ok(postService.findAllMyFollow());
    }

    @PostMapping
    @OnlySeller
    public void save(@RequestBody PostSaveDto postSaveDto) {
        postService.save(postSaveDto);
    }


}
