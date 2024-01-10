package com.example.userJWTauth.controller;

import com.example.userJWTauth.controller.request.CreateUserDTO;
import com.example.userJWTauth.entities.ERole;
import com.example.userJWTauth.entities.RoleEntity;
import com.example.userJWTauth.entities.UserEntity;
import com.example.userJWTauth.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class principalController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    public principalController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder; 
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserEntity> getAllUsers(){ return userService.getAllUsers();}

    @PostMapping("/createUser")
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDTO createUserDTO) {
        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());
        UserEntity user = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .email(createUserDTO.getEmail())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .roles(roles)
                .build();

        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserById(@RequestParam String id) {
        return userService.deleteUser(Long.parseLong(id)) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
