package com.thanh.blog.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTagRequest {
    @NotBlank
    private String title;
    private String content;
    @NotBlank
    private String slug;
}
