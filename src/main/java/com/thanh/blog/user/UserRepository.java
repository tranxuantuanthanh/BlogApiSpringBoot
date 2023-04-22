package com.thanh.blog.user;

import java.util.Optional;

import com.thanh.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  Boolean existsByEmail(String email);

  Boolean existsByUsername(String username);
}
