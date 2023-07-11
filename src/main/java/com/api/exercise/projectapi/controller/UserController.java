package com.api.exercise.projectapi.controller;

import com.api.exercise.projectapi.entity.User;
import com.api.exercise.projectapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping(value = "/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUser(@PathVariable Long userId){
       return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) throws InvalidPropertiesFormatException {
        if (userService.isInvalidEmailFormat(user.getEmail())){
            throw new InvalidPropertiesFormatException("Invalid e-mail format");
        }
       return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user){
        userService.save(user);
    }

    @DeleteMapping(value = "/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    @PostMapping(value = "/users/{userId}/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void addUsersToProject(@PathVariable Long userId, @PathVariable Long projectId) throws EntityNotFoundException {
        userService.assignUserToProject(userId, projectId);
    }
}
