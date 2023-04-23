package com.thanh.blog.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCategoryRequest {
    @NotNull
    private Long id;
    private Long parentId = -1L;
    @NotBlank
    private String title;
    private String content;
    @NotBlank
    private String slug;
}
