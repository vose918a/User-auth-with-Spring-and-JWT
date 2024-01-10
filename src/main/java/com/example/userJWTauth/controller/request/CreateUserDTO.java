package com.example.userJWTauth.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
// This class defines the structure of the user request
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotBlank
    private String username;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles;
}
