package com.example.demo27_11_22.service;

import com.example.demo27_11_22.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> finBbyId(long id);
    User saveUser(User user);
    void deleteById(long id);

}
