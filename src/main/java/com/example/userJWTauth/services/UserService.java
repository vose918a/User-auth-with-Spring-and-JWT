package com.example.userJWTauth.services;

import com.example.userJWTauth.entities.UserEntity;
import com.example.userJWTauth.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//This service class is responsible for the management of the user repositories
// and connect the controller and repository
@Service
public class UserService {
    private final UserRepository repository;

    public List<UserEntity> getAllUsers() {return repository.findAll();}

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity saveUser(UserEntity user){ return repository.save(user); }

    public boolean deleteUser(Long id){
        return repository.findById(id).map(Person -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
