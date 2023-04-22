package com.thanh.blog.controller;

import com.thanh.blog.model.User;
import com.thanh.blog.security.CurrentUser;
import com.thanh.blog.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping(path = "{username}/posts/")
    ResponseEntity<?> getPostByUserName(
            @CurrentUser User user,
            @PathVariable String username,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(service.getPostOfUser(user, username, pageNumber, pageSize));
    }
}
