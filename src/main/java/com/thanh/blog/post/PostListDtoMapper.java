package com.thanh.blog.post;

import java.util.function.Function;
import org.springframework.stereotype.Service;

import com.thanh.blog.model.Post;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostListDtoMapper implements Function<Post, PostListDTO> {
    
    @Override
    public PostListDTO apply(Post post) {
        return new PostListDTO(
                post.getId(),
                post.getTitle(),
                post.getMetaTitle(),
                post.getSlug(),
                post.getSummary(),
                post.getPublished(),
                post.getCreateDate(),
                post.getUser().getUser_name(),
                post.getCategories(),
                post.getTags());
    }

}
