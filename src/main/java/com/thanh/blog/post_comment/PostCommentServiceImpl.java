package com.thanh.blog.post_comment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.thanh.blog.exception.AccessDeniedException;
import com.thanh.blog.exception.BadRequestException;
import com.thanh.blog.exception.ResourceNotFoundException;
import com.thanh.blog.model.Post;
import com.thanh.blog.model.PostComment;
import com.thanh.blog.model.Role;
import com.thanh.blog.model.User;
import com.thanh.blog.payload.response.ApiResponse;
import com.thanh.blog.payload.response.PageResponse;
import com.thanh.blog.post.PostRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final PostCommentDtoMapper postCommentDtoMapper;

    @Override
    public PageResponse<PostCommentDTO> getCommentsByPostId(Long postId, Integer pageNumber, Integer pageSize) {
        Long totalComments = postCommentRepository.countByPost_Id(postId);
        if (totalComments == 0) {
            throw new ResourceNotFoundException("PostComment", "post_id", totalComments);
        }
        Integer totalPages = Math.round((float) totalComments / pageSize);
        if (totalPages < pageNumber)
            pageNumber = totalPages;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "commentAt");
        List<PostCommentDTO> postComments = postCommentRepository.findByPost_Id(postId, pageable)
                .getContent()
                .stream()
                .map(postCommentDtoMapper)
                .collect(Collectors.toList());
        PageResponse<PostCommentDTO> pageResponse = new PageResponse<>(
                pageNumber,
                pageSize,
                totalPages,
                totalComments,
                postComments);
        return pageResponse;
    }

    @Override
    public PostCommentDTO createComment(User user, CreateCommentRequest data) {
        Optional<Post> post = postRepository.findById(data.getPostId());
        if (post.isEmpty()) {
            throw new BadRequestException("The post not exists on the system.");
        };
        PostComment postComment = PostComment.builder()
        .content(data.getContent())
        .post(post.get())
        .user(user)
        .build();
        return postCommentDtoMapper.apply(postCommentRepository.save(postComment));
    }

    @Override
    public PostCommentDTO updateComment(User user, UpdateCommentRequest data) {
        Optional<Post> post = postRepository.findById(data.getPostId());
        if (post.isEmpty()) {
            throw new BadRequestException("The post not exists on the system.");
        }
        Optional<PostComment> postComment = postCommentRepository.findById(data.getId());
        if (postComment.isEmpty()) {
            throw new BadRequestException("The comment not exists on the system.");
        }
        postComment.get().setContent(data.getContent());
        return postCommentDtoMapper.apply(postCommentRepository.save(postComment.get()));
    }

    @Override
    public ApiResponse deleteComment(User user, Long commentId) {
        Optional<PostComment> postComment = postCommentRepository.findById(commentId);
        if (postComment.isEmpty()) {
            throw new BadRequestException("The post not exists on the system.");
        }
        if (postComment.get().getUser().getId() != user.getId() && !user.getRole().equals(Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("You do not have access to this method.");
        }
        postCommentRepository.delete(postComment.get());
        return new ApiResponse(true, "Delete successfully");
    }
    
}
