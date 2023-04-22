package com.thanh.blog.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.thanh.blog.model.User;
import com.thanh.blog.payload.response.PageResponse;
import com.thanh.blog.post.PostListDTO;
import com.thanh.blog.post.PostListDtoMapper;
import com.thanh.blog.post.PostRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final PostRepository postRepository;
    private final PostListDtoMapper postListDtoMapper;

    @Override
    public PageResponse<PostListDTO> getPostOfUser(User user, String username, Integer pageNumber, Integer pageSize) {
        if (user.getUser_name().equals(username)) {
            Long totalPosts = postRepository.countByUser_Username(username);
            Integer totalPages = Math.round((float) totalPosts / pageSize);
            if (totalPages < pageNumber)
                pageNumber = totalPages;
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            List<PostListDTO> posts = postRepository.findByUser_Username(username, pageable).getContent()
                    .stream()
                    .map(postListDtoMapper)
                    .collect(Collectors.toList());
            PageResponse<PostListDTO> pageResponse = new PageResponse<>(
                    pageNumber,
                    pageSize,
                    totalPages,
                    totalPosts,
                    posts);
            return pageResponse;
        }
        Long totalPosts = postRepository.countByUser_Username(username);
        Integer totalPages = Math.round((float) totalPosts / pageSize);
        if (totalPages < pageNumber)
            pageNumber = totalPages;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<PostListDTO> posts = postRepository.findByUser_UsernameAndPublishedIsTrue(username, pageable).getContent()
                .stream()
                .map(postListDtoMapper)
                .collect(Collectors.toList());
        PageResponse<PostListDTO> pageResponse = new PageResponse<>(
                pageNumber,
                pageSize,
                totalPages,
                totalPosts,
                posts);
        return pageResponse;
    }

}
