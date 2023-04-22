package com.thanh.blog.repository;

import com.thanh.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    java.util.List<Tag> findByPostList_Id(Long id);
}
