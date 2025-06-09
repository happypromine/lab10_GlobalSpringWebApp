package com.webapp.springwebapp.service;

import com.webapp.springwebapp.model.User;
import com.webapp.springwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByNameAndPassword(String name, String password){
        return userRepository.findByNameAndPassword(name, password);
    }

    public List<User> getAllActiveUsers() {
        return userRepository.findAllByIsDeletedFalse();
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.getUserById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByName(username);
    }
}
