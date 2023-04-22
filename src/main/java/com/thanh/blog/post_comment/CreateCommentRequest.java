package com.thanh.blog.post_comment;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class CreateCommentRequest {
    @NotNull
    Long postId;
    @NotBlank
    String content;
}
