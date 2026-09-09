package com.prod_ready_features.service;

import com.prod_ready_features.dto.PostDTO;
import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);

    void deletePost(Long postId);
}