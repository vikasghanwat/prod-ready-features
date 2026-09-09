package com.prod_ready_features.service.impl;

import com.prod_ready_features.dto.PostDTO;
import com.prod_ready_features.entity.PostEntity;
import com.prod_ready_features.exception.ResourceNotFoundException;
import com.prod_ready_features.repository.PostRepository;
import com.prod_ready_features.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    // GET ALL
    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(postEntity -> modelMapper.map(postEntity, PostDTO.class)).collect(Collectors.toList());
    }

    // CREATE
    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        PostEntity savedPost = postRepository.save(postEntity);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    // GET BY ID
    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    // UPDATE
    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity existingPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
        inputPost.setId(postId);
        modelMapper.map(inputPost, existingPost);
        PostEntity savedPost = postRepository.save(existingPost);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    // DELETE
    @Override
    public void deletePost(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
        postRepository.delete(postEntity);
    }
}
