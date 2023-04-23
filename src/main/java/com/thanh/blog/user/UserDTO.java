package com.thanh.blog.user;

import java.util.Date;

public record UserDTO (
    Integer id,
    String firstname,
    String lastname,
    String username,
    String email,
    Date registerAt,
    Date lastLogin,
    String intro,
    String profile,
    com.thanh.blog.model.Role role
){
    
}
