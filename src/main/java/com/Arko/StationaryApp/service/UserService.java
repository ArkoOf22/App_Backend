package com.Arko.StationaryApp.service;

import com.Arko.StationaryApp.model.User;
import com.Arko.StationaryApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public Optional<User> findUserById(Long id){
        return userRepository.findUserById(id);
    }
}
