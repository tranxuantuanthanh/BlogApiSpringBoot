package com.thanh.blog.controller;

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

import com.thanh.blog.tag.CreateTagRequest;
import com.thanh.blog.tag.TagService;
import com.thanh.blog.tag.UpdateTagRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService service;
    @GetMapping(path = "/getAll")
    ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(service.getAllTags());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(path = "/create")
    ResponseEntity<?> comment(
            @Valid @RequestBody CreateTagRequest data) {

        return ResponseEntity.ok(service.createTag(data));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(path = "/update/{id}")
    ResponseEntity<?> update(
            @Valid @RequestBody UpdateTagRequest data,
            @PathVariable Long id) {
        data.setId(id);
        return ResponseEntity.ok(service.updateTag(data));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.deleteTag(id));
    }
}
