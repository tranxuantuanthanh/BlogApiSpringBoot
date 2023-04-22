package com.thanh.blog.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  @NotBlank
  private String firstname;
  @NotBlank
  private String lastname;
  @NotBlank
  private String username;
  @Email
  private String email;
  @NotBlank
  private String password;
}
