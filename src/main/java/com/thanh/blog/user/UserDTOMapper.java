package com.thanh.blog.user;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.thanh.blog.model.User;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
            user.getId(),
            user.getFirstname(),
            user.getLastname(),
            user.getUser_name(),
            user.getEmail(),
            user.getRegisterAt(),
            user.getLastLogin(),
            user.getIntro(),
            user.getProfile(),
            user.getRole());
    }
}
