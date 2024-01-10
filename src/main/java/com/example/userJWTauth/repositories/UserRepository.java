package com.example.userJWTauth.repositories;

import com.example.userJWTauth.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//This interface the manager for the queries of the table users in the database
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByUsername(String username);
}
