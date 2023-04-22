package com.thanh.blog.controller;

import com.thanh.blog.model.*;
import com.thanh.blog.post.CreatePostRequest;
import com.thanh.blog.post.PostService;
import com.thanh.blog.post.UpdatePostRequest;
import com.thanh.blog.post.UpdatePublishedRequest;
import com.thanh.blog.security.CurrentUser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping(path = "/posts")
    ResponseEntity<?> find(
            @RequestParam(required = false, name = "category", defaultValue = "") String category,
            @RequestParam(required = false, name = "tags", defaultValue = "") List<String> tags,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        if (!category.isBlank()) {
            return ResponseEntity.ok(postService.findByCategory(category, pageNumber, pageSize));
        }

        if (tags.size() != 0) {

            return ResponseEntity.ok(postService.findByTags(tags, pageNumber, pageSize));
        }
        return ResponseEntity.ok(postService.findAllPost(pageNumber, pageSize));
    }

    @GetMapping(path = "/{postId}")
    ResponseEntity<?> find(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PostMapping(path = "/")
    ResponseEntity<?> createPost(
            @CurrentUser User user,
            @Valid @RequestBody CreatePostRequest createPostRequest) {

        return ResponseEntity.ok(postService.createPost(user, createPostRequest));
    }

    @PutMapping(path = "/update/{id}")
    ResponseEntity<?> updatePost(
            @CurrentUser User user,
            @Valid @RequestBody UpdatePostRequest data,
            @PathVariable(name = "id") Long id) {
        data.setId(id);
        return ResponseEntity.ok(postService.updatePost(user, data));
    }

    @PutMapping(path = "/update-published/{id}")
    ResponseEntity<?> updatePostPublished(
            @CurrentUser User user,
            @Valid @RequestBody UpdatePublishedRequest data,
            @PathVariable(name = "id") Long id) {
        data.setId(id);
        return ResponseEntity.ok(postService.updatePostPublished(user, data));
    }

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<?> delete(
            @CurrentUser User user,
            @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.deletePost(user, id));
    }
}
