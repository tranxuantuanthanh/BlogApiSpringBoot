package com.thanh.blog.payload.response;

public record CategoryDTO(
    Long id,
    CategoryDTO parent,
    String title,
    String content,
    String slug
) {
    
}
