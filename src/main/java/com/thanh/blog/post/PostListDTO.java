package com.thanh.blog.post;

import java.util.Date;

import com.thanh.blog.model.Category;
import com.thanh.blog.model.Tag;

import java.util.List;

public record PostListDTO(
        Long id,
        String title,
        String metaTitle,
        String slug,
        String summary,
        Boolean published,
        Date createDate,
        String username,
        List<Category> categories,
        List<Tag> tags) {
}
