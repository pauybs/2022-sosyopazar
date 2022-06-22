package com.mcavlak.sosyobazaar.models.entities;

import com.mcavlak.sosyobazaar.dtos.PostSaveDto;
import com.mcavlak.sosyobazaar.models.entities.abstracts.LocalDateTimeEntity;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import com.mcavlak.sosyobazaar.utils.SecurityContextUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends LocalDateTimeEntity {

    @Lob
    private String content;

    @ManyToOne
    private Seller contentOwner;

    public static Post create(PostSaveDto postSaveDto) {
        Post post = new Post();
        post.content = postSaveDto.getContent();
        post.contentOwner = SecurityContextUtil.getCurrentSeller();
        return post;
    }
}
