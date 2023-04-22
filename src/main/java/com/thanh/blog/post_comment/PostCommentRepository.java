package com.thanh.blog.post_comment;

import com.thanh.blog.model.PostComment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    Page<PostComment> findByPost_Id(Long id, Pageable pageable);

    Long countByPost_Id(Long postId);
}

