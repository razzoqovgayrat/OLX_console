package com.company.service;

import com.company.dto.PostDTO;
import com.company.entity.Address;
import com.company.entity.Post;
import com.company.utils.Utils;

import java.util.Objects;
import java.util.UUID;

public class PostService {
    private static PostService postService;
    private PostService() {};
    public static PostService getInstance() {
        if (Objects.isNull(postService)) postService = new PostService();
        return postService;
    }

    public void createPost(PostDTO postDTO) {

    }

    private Post toEntity(PostDTO dto) {
        Address address = new Address(dto.address().city(), dto.address().street(), dto.address().apartNumber());
        return new Post(UUID.randomUUID().toString(), Utils.currentUserId, dto.homeType(), address, dto.field(), dto.roomCount(), dto.price(), dto.postType(), dto.description());
    }
}
