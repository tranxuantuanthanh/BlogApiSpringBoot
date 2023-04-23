package com.thanh.blog.controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanh.blog.category.CategoryService;
import com.thanh.blog.category.CreateCategoryRequest;
import com.thanh.blog.category.UpdateCategoryRequest;
import com.thanh.blog.security.CurrentUser;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    @GetMapping(path = "/getAll")
    ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(path = "/create")
    ResponseEntity<?> comment(
            @Valid @RequestBody CreateCategoryRequest data) {

        return ResponseEntity.ok(service.createCategory(data));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(path = "/update/{id}")
    ResponseEntity<?> update(
            @Valid @RequestBody UpdateCategoryRequest data,
            @PathVariable Long id) {
        data.setId(id);
        return ResponseEntity.ok(service.updateCategory(data));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<?> delete(
            @CurrentUser User user,
            @PathVariable Long id) {
        return ResponseEntity.ok(service.deleteCategory(id));
    }
}
