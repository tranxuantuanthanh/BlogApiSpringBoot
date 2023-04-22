package com.thanh.blog.user;

import org.springframework.stereotype.Service;

import com.thanh.blog.model.User;
import com.thanh.blog.payload.response.PageResponse;
import com.thanh.blog.post.PostListDTO;

@Service
public interface UserService {

    PageResponse<PostListDTO> getPostOfUser(User user, String username, Integer pageNumber, Integer pageSize);
    
}
