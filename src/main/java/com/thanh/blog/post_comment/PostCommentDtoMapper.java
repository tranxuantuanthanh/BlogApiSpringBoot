package com.thanh.blog.post_comment;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.thanh.blog.model.PostComment;

@Service
public class PostCommentDtoMapper implements Function<PostComment, PostCommentDTO> {

    @Override
    public PostCommentDTO apply(PostComment postComment) {
        return new PostCommentDTO(
            postComment.getId(),
            postComment.getPost().getId(),
            postComment.getUser().getUser_name(),
            postComment.getCommentAt(),
            postComment.getContent());
    }
    
}
