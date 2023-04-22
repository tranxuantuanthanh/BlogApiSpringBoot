package com.thanh.blog.controller;

import com.thanh.blog.model.User;
import com.thanh.blog.post_comment.CreateCommentRequest;
import com.thanh.blog.post_comment.PostCommentService;
import com.thanh.blog.post_comment.UpdateCommentRequest;
import com.thanh.blog.security.CurrentUser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/comment")
@RequiredArgsConstructor
public class PostCommentController {
    private final PostCommentService service;

    @GetMapping(path = "/{postId}")
    ResponseEntity<?> getCommentsOfPost(
            @PathVariable(required = true) Long postId,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(service.getCommentsByPostId(postId, pageNumber, pageSize));
    }

    @PostMapping(path = "/create")
    ResponseEntity<?> comment(
            @CurrentUser User user,
            @Valid @RequestBody CreateCommentRequest data) {

        return ResponseEntity.ok(service.createComment(user, data));
    }

    @PutMapping(path = "/update/{id}")
    ResponseEntity<?> update(
            @CurrentUser User user,
            @Valid @RequestBody UpdateCommentRequest data,
            @PathVariable Long id) {
        data.setId(id);

        return ResponseEntity.ok(service.updateComment(user, data));
    }

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<?> delete(
            @CurrentUser User user,
            @PathVariable Long id) {
        return ResponseEntity.ok(service.deleteComment(user, id));
    }
}
