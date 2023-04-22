package com.thanh.blog.post;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class UpdatePublishedRequest {
    @NotNull
    private Long id;
    @NotNull
    private Boolean currentPublished;
    @NotNull
    private Boolean newPublished;
}
