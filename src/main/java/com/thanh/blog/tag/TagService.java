package com.thanh.blog.tag;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thanh.blog.model.Tag;
import com.thanh.blog.payload.response.ApiResponse;

import jakarta.validation.Valid;

@Service
public interface TagService {

    List<Tag> getAllTags();

    Tag createTag(@Valid CreateTagRequest data);

    Tag updateTag(@Valid UpdateTagRequest data);

    ApiResponse deleteTag(Long id);
    
}
