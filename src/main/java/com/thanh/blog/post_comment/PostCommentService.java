package com.thanh.blog.post_comment;

import org.springframework.stereotype.Service;

import com.thanh.blog.model.User;
import com.thanh.blog.payload.response.ApiResponse;
import com.thanh.blog.payload.response.PageResponse;

@Service
public interface PostCommentService {
    PageResponse<PostCommentDTO> getCommentsByPostId(Long postId, Integer pageNumber, Integer pageSize);

    PostCommentDTO createComment(User user, CreateCommentRequest data);

    PostCommentDTO updateComment(User user, UpdateCommentRequest data);

    ApiResponse deleteComment(User user, Long commentId);
}
