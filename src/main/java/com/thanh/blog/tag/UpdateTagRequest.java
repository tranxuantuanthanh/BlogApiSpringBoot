package com.thanh.blog.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTagRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    private String content;
    @NotBlank
    private String slug;
}
