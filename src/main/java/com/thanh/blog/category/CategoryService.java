package com.thanh.blog.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thanh.blog.model.Category;
import com.thanh.blog.payload.response.ApiResponse;

import jakarta.validation.Valid;

@Service
public interface CategoryService {

    List<Category> getAllCategories();

    Category createCategory(@Valid CreateCategoryRequest data);

    Category updateCategory(@Valid UpdateCategoryRequest data);

    ApiResponse deleteCategory(Long id);

}
