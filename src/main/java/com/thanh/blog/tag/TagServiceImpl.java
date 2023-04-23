package com.thanh.blog.tag;

import java.util.List;

import org.springframework.stereotype.Component;

import com.thanh.blog.exception.BadRequestException;
import com.thanh.blog.model.Tag;
import com.thanh.blog.payload.response.ApiResponse;

import jakarta.validation.Valid;

@Component
public class TagServiceImpl implements TagService {
    TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag createTag(@Valid CreateTagRequest data) {
        if (Boolean.TRUE.equals(tagRepository.existsBySlug(data.getSlug()))) {
            throw new BadRequestException(String.format("The slug with name %s already taken.", data.getSlug()));
        }
        return tagRepository.save(Tag.builder()
                .content(data.getContent())
                .title(data.getTitle())
                .slug(data.getSlug())
                .build());
    }

    @Override
    public Tag updateTag(@Valid UpdateTagRequest data) {
        var tag = tagRepository.findById(data.getId());
        if (tag.isEmpty()) {
            throw new BadRequestException(
                    String.format("The tag with id %o not exists on the system.", data.getId()));
        }
        var tagGet = tag.get();
        if (Boolean.TRUE.equals(tagRepository.existsBySlug(data.getSlug()))&& !data.getSlug().equals(tagGet.getSlug())) {
            throw new BadRequestException(String.format("The slug with name %s already taken.", data.getSlug()));
        };
        tagGet.setContent(data.getContent());
        tagGet.setSlug(data.getSlug());
        tagGet.setTitle(data.getTitle());
        return tagRepository.save(tagGet);
    }

    @Override
    public ApiResponse deleteTag(Long id) {
        var tag = tagRepository.findById(id);
        if (tag.isEmpty()) {
            throw new BadRequestException(
                    String.format("The tag with id %o not exists on the system.", id));
        }
        tagRepository.delete(tag.get());
        return new ApiResponse(true, "Successfull");
    }

}
