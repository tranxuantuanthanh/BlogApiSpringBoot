package com.thanh.blog.post;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.thanh.blog.model.Post;
import com.thanh.blog.post_comment.PostCommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostDtoMapper implements Function<Post, PostDTO> {
    private final PostCommentService service;
    private final Integer PAGE_NUMBER = 0;
    private final Integer PAGE_SIZE= 10;

    @Override
    public PostDTO apply(Post post) {
        return new PostDTO(post.getId(),
                post.getTitle(),
                post.getMetaTitle(),
                post.getSlug(),
                post.getSummary(),
                post.getPublished(),
                post.getCreateDate(),
                post.getContent(),
                post.getUser().getUser_name(),
                service.getCommentsByPostId(post.getId(), PAGE_NUMBER, PAGE_SIZE),
                post.getCategories(),
                post.getTags());
    }

}
