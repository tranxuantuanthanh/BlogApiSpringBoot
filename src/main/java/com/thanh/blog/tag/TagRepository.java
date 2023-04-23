package com.thanh.blog.tag;

import com.thanh.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Boolean existsBySlug(String slug);
}
