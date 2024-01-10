package com.example.userJWTauth.repositories;

import com.example.userJWTauth.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//In this class is created the management of the queries for roles table on the database
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
