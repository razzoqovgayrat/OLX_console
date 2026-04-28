package com.company.service;

import com.company.dto.AddressDTO;
import com.company.dto.FilterDTO;
import com.company.dto.PostDTO;
import com.company.entity.Address;
import com.company.entity.Post;
import com.company.repository.PostRepository;
import com.company.utils.Utils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PostService {
    private static PostService postService;
    private PostService() {};
    public static PostService getInstance() {
        if (Objects.isNull(postService)) postService = new PostService();
        return postService;
    }

    private final PostRepository postRepository = PostRepository.getInstance();

    public void createPost(PostDTO postDTO) {
        postRepository.savePost(toEntity(postDTO));
    }

    public List<PostDTO> myPosts() {
        return postRepository.getList().stream().filter(post -> post.getUserId().equals(Utils.currentUserId)).map(this::toDTO).toList();
    }

    private Post toEntity(PostDTO dto) {
        Address address = new Address(dto.address().city(), dto.address().street(), dto.address().apartNumber());
        return new Post(UUID.randomUUID().toString(), Utils.currentUserId, dto.homeType(), address, dto.field(), dto.roomCount(), dto.price(), dto.postType(), dto.description());
    }

    private PostDTO toDTO(Post post) {
        AddressDTO addressDTO = new AddressDTO(post.getAddress().getCity(), post.getAddress().getStreet(), post.getAddress().getApartNumber());
        return new PostDTO(post.getHomeType(), addressDTO, post.getField(), post.getRoomCount(), post.getPrice(), post.getPostType(), post.getDescription());
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.getList().stream().map(this::toDTO).toList();
    }

    public List<PostDTO> filter(FilterDTO filterDTO) {
        return getAllPosts().stream().filter(dto ->
            (filterDTO.homeType() != null && filterDTO.homeType().equals(dto.homeType()))
                    || (filterDTO.Address() != null && filterDTO.Address().equalsIgnoreCase(dto.address().city()))
                    || (filterDTO.field() != 0 && filterDTO.field() >= dto.field())
                    || (filterDTO.roomCount() != 0 && filterDTO.roomCount() >= dto.roomCount())
                    || (filterDTO.price() != 0 && filterDTO.price() >= dto.price())
                    || (filterDTO.postType() != null && filterDTO.postType().equals(dto.postType()))
        ).toList();
    }

    public List<PostDTO> filterAll(FilterDTO filterDTO) {
        return getAllPosts().stream().filter(dto -> (filterDTO.homeType().equals(dto.homeType()) && filterDTO.Address().equalsIgnoreCase(dto.address().city())
                && (filterDTO.field() >= dto.field()) && filterDTO.roomCount() >= dto.roomCount()
                && (filterDTO.price() >= dto.price() && filterDTO.postType().equals(dto.postType())))).toList();
    }
}
