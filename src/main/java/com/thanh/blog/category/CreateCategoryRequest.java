package com.thanh.blog.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    private Long parentId = -1L;
    @NotBlank
    private String title;
    private String content;
    @NotBlank
    private String slug;
}
