package com.mcavlak.sosyobazaar.managers;

import com.mcavlak.sosyobazaar.dtos.PostDto;
import com.mcavlak.sosyobazaar.dtos.PostSaveDto;
import com.mcavlak.sosyobazaar.dtos.SellerDto;
import com.mcavlak.sosyobazaar.mappers.PostMapper;
import com.mcavlak.sosyobazaar.models.entities.Post;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import com.mcavlak.sosyobazaar.repositories.CustomerRepository;
import com.mcavlak.sosyobazaar.repositories.PostRepository;
import com.mcavlak.sosyobazaar.services.PostService;
import com.mcavlak.sosyobazaar.utils.SecurityContextUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostManager implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CustomerRepository customerRepository;

    public PostManager(PostRepository postRepository, PostMapper postMapper, CustomerRepository customerRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public void save(PostSaveDto postSaveDto) {
        postRepository.save(Post.create(postSaveDto));
    }

    @Override
    public List<PostDto> findAll() {
        return postMapper.entityListToDtoList(postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDateTime")));
    }

    @Override
    public List<PostDto> findAllBySellerId(Long sellerId) {
        return postMapper.entityListToDtoList(postRepository.findByContentOwner_IdOrderByCreatedDateTimeDesc(sellerId));
    }

    @Override
    public List<PostDto> findAllMyFollow() {

        Customer customer = SecurityContextUtil.getCurrentCustomer();
        customer = customerRepository.findById(customer.getId()).get();
        List<Long> sellerIdList = customer.getFollows().stream().map(Seller::getId).collect(Collectors.toList());
        List<Post> postList = postRepository.findByContentOwner_IdIn(sellerIdList);
        return postMapper.entityListToDtoList(postList);
    }


}
