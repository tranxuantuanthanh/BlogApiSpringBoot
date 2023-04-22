package com.thanh.blog.post;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thanh.blog.model.User;
import com.thanh.blog.payload.response.ApiResponse;
import com.thanh.blog.payload.response.PageResponse;

@Service
public interface PostService {
    PageResponse<PostListDTO> findByCategory(String slug, Integer pageNumber, Integer pageSize);

    PageResponse<PostListDTO> findByTags(List<String> tags, Integer pageNumber, Integer pageSize);

    PageResponse<PostListDTO> findAllPost(Integer pageNumber, Integer pageSize);

    PostListDTO createPost(User user, CreatePostRequest data);

    PostListDTO updatePost(User user, UpdatePostRequest data);

    ApiResponse deletePost(User user, Long postId);

    PostListDTO updatePostPublished(User user, UpdatePublishedRequest updatePublishedRequest);

    PostDTO getPostById(Long postId);
}
