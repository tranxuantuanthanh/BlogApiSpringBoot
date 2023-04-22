package com.thanh.blog.post;

import com.thanh.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUser_Username(String username, Pageable pageable);

    Page<Post> findByUser_UsernameAndPublishedIsTrue(String username, Pageable pageable);

    Long countByUser_Username(String username);

    Boolean existsBySlug(String slug);

    Page<Post> findByCategories_Slug(String slug, Pageable pageable) ;

    Long countByCategories_Slug(String slug);

    Page<Post> findByTags_SlugIn(String[] slugs, Pageable pageable);
    List<Post> findByTags_SlugIn(String[] slugs);
}
