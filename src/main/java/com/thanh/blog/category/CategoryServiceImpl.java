package com.thanh.blog.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.thanh.blog.exception.BadRequestException;
import com.thanh.blog.model.Category;
import com.thanh.blog.payload.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllByCategory_IdIsNull();
    }

    @Override
    public Category createCategory(@Valid CreateCategoryRequest data) {
        if (Boolean.TRUE.equals(categoryRepository.existsBySlug(data.getSlug()))) {
            throw new BadRequestException(String.format("The slug with name %s already taken.", data.getSlug()));
        }
        if (data.getParentId() != -1) {
            var categoryParent = categoryRepository.findById(data.getParentId());
            if (categoryParent.isEmpty()) {
                throw new BadRequestException(
                        String.format("Category with parentId %o is not found.", data.getParentId()));
            }
            return categoryRepository.save(Category.builder()
                    .category(categoryParent.get())
                    .content(data.getContent())
                    .title(data.getTitle())
                    .slug(data.getSlug())
                    .build());
        }
        return categoryRepository.save(Category.builder()
                .content(data.getContent())
                .title(data.getTitle())
                .slug(data.getSlug())
                .build());
    }

    @Override
    public Category updateCategory(@Valid UpdateCategoryRequest data) {
        var category = categoryRepository.findById(data.getId());
        if (category.isEmpty()) {
            throw new BadRequestException(
                    String.format("The category with id %o not exists on the system.", data.getId()));
        }
        var categoryGet = category.get();
        if (Boolean.TRUE.equals(categoryRepository.existsBySlug(data.getSlug()))&& !data.getSlug().equals(categoryGet.getSlug())) {
            throw new BadRequestException(String.format("The slug with name %s already taken.", data.getSlug()));
        }
        Optional<Category> categoryParent;
        if (data.getParentId() != -1) {
            categoryParent = categoryRepository.findById(data.getParentId());
            if (categoryParent.isEmpty()) {
                throw new BadRequestException(
                        String.format("Category with parentId %o is not found.", data.getParentId()));
            }
            categoryGet.setCategory(categoryParent.get());
        }
        categoryGet.setContent(data.getContent());
        categoryGet.setSlug(data.getSlug());
        categoryGet.setTitle(data.getTitle());
        return categoryRepository.save(categoryGet);
    }

    @Override
    public ApiResponse deleteCategory(Long id) {
        var category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new BadRequestException(String.format("The category with id %o not exists on the system.", id));
        }
        categoryRepository.delete(category.get());
        return new ApiResponse(true, "Delete successfully");
    }

}
