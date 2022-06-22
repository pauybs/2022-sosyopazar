package com.mcavlak.sosyobazaar.managers;

import com.mcavlak.sosyobazaar.dtos.AverageScoreDto;
import com.mcavlak.sosyobazaar.dtos.SaveSellerCommentDto;
import com.mcavlak.sosyobazaar.dtos.SellerCommentDto;
import com.mcavlak.sosyobazaar.mappers.SellerCommentMapper;
import com.mcavlak.sosyobazaar.models.entities.SellerComment;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import com.mcavlak.sosyobazaar.repositories.SellerCommentRepository;
import com.mcavlak.sosyobazaar.repositories.SellerRepository;
import com.mcavlak.sosyobazaar.services.SellerCommentService;
import com.mcavlak.sosyobazaar.services.SellerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class SellerCommentManager implements SellerCommentService {

    private final SellerCommentRepository sellerCommentRepository;
    private final SellerService sellerService;
    private final SellerCommentMapper sellerCommentMapper;
    private final SellerRepository sellerRepository;

    public SellerCommentManager(SellerCommentRepository sellerCommentRepository,
                                SellerService sellerService,
                                SellerCommentMapper sellerCommentMapper, SellerRepository sellerRepository) {
        this.sellerCommentRepository = sellerCommentRepository;
        this.sellerService = sellerService;
        this.sellerCommentMapper = sellerCommentMapper;
        this.sellerRepository = sellerRepository;
    }


    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void onlySave(Seller seller, SaveSellerCommentDto saveSellerCommentDto) {
        sellerCommentRepository.save(SellerComment.create(seller, saveSellerCommentDto.getComment(), saveSellerCommentDto.getScore()));
    }

    @Override
    public void save(SaveSellerCommentDto saveSellerCommentDto) {

        Seller seller = sellerService.findDataById(saveSellerCommentDto.getSellerId());
        onlySave(seller, saveSellerCommentDto);

        List<SellerComment> sellerCommentList = sellerCommentRepository.findBySeller_IdOrderByCreatedDateTimeDesc(seller.getId());
        int size = sellerCommentList.size();
        BigDecimal totalScore = sellerCommentList.stream().map(SellerComment::getScore).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averageScore = new BigDecimal(0);
        if (size != 0) {
            averageScore = totalScore.divide(new BigDecimal(size), 1, RoundingMode.HALF_UP);
        }

        seller.updateAverageScore(averageScore);
        sellerRepository.save(seller);

    }

    @Override
    public List<SellerCommentDto> findAllBySellerId(Long sellerId) {

        return sellerCommentMapper.entityListToDtoList(sellerCommentRepository.findBySeller_IdOrderByCreatedDateTimeDesc(sellerId));

    }

    @Override
    public AverageScoreDto getAverageScoreBySellerId(Long sellerId) {

        Seller seller = sellerService.findDataById(sellerId);

        List<SellerComment> sellerCommentList = sellerCommentRepository.findBySeller_IdOrderByCreatedDateTimeDesc(sellerId);

        int size = sellerCommentList.size();

        BigDecimal totalScore = sellerCommentList.stream().map(SellerComment::getScore).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal averageScore = new BigDecimal(0);
        if (size != 0) {
            averageScore = totalScore.divide(new BigDecimal(size), 1, RoundingMode.HALF_UP);
        }
        return new AverageScoreDto(seller.getId(), averageScore);

    }

}
