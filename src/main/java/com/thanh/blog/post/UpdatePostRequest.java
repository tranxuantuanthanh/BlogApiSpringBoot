package com.thanh.blog.post;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdatePostRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    private String metaTitle;
    @NotBlank
    private String slug;
    private String summary;
    @NotBlank
    private String content;
    @NotNull
    private List<Long> categories;
    private List<Long> tags;
}
