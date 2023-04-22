package com.thanh.blog.post_comment;

import java.util.Date;

public record PostCommentDTO(
    Long id,

    Long postId,

    String username,

    Date commentAt,

    String content
) {
   
}
