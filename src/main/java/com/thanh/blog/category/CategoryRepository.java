package com.thanh.blog.category;

import com.thanh.blog.model.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsBySlug(String slug);
    List<Category> findAllByCategory_IdIsNull();
}
