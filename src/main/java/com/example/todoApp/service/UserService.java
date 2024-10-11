package com.example.todoApp.service;

import com.example.todoApp.model.entities.TodoEntity;
import com.example.todoApp.model.entities.UserProfile;
import com.example.todoApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<UserProfile> findAllUsers(){
        return userRepository.findAll();
    }

    public UserProfile findAllUsersById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public UserProfile insertUser (UserProfile user) {
        return userRepository.save(user);

    }

    public UserProfile UpdateUser(UserProfile user) {

        UserProfile userProfile = userRepository.findById(user.getUserId()).orElseThrow();
        userProfile.setUsername(userProfile.getUsername());
        userProfile.setEmail(userProfile.getEmail());
        return userRepository.save(userProfile);

    }
}
