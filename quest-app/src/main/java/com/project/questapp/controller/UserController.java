package com.project.questapp.controller;

import com.project.questapp.model.User;
import com.project.questapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        User createdUser = userService.createUser(newUser);
        log.info("Created Succesfully");
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public Optional<User> getById(@PathVariable Long userId){
        //custom exception
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
        User updatedUser = userService.UpdateUser(userId,newUser);
        if (updatedUser != null) {
            log.info("User with ID" + userId + "updated succesfully.");
        }else {
            log.warn("user with ID" + userId + "not found.");
        }
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
        log.info("User with ID " + userId + " was deleted successfully.");
    }
}
