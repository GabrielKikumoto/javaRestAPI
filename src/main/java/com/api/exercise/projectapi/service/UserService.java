package com.api.exercise.projectapi.service;

import com.api.exercise.projectapi.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findById(Long userId);

    List<User> findAll();
    void deleteById(Long userId);

    void assignUserToProject(Long userId, Long projectId);

    public boolean isInvalidEmailFormat(String email);
}
