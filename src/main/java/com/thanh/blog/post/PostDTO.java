package com.thanh.blog.post;

import java.util.Date;
import java.util.List;

import com.thanh.blog.model.Category;
import com.thanh.blog.model.Tag;
import com.thanh.blog.payload.response.PageResponse;
import com.thanh.blog.post_comment.PostCommentDTO;

public record PostDTO(
        Long id,
        String title,
        String metaTitle,
        String slug,
        String summary,
        Boolean published,
        Date createDate,
        String content,
        String username,
        PageResponse<PostCommentDTO> postComments,
        List<Category> categories,
        List<Tag> tags) {
}
