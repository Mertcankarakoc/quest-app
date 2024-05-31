package com.project.questapp.service;

import com.project.questapp.model.User;
import com.project.questapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> getOneUserById(Long userId){
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User newUser){
        return userRepository.save(newUser);
    }

    public User UpdateUser(Long userId, User updatedUser){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(updatedUser.getUserName());
            foundUser.setPassword(updatedUser.getPassword());
            userRepository.save(foundUser);
        }
        return null;
    }

    public void deleteById(Long userId){
        userRepository.deleteById(userId);
    }
}
