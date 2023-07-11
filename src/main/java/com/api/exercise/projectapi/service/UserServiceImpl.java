package com.api.exercise.projectapi.service;

import com.api.exercise.projectapi.entity.Project;
import com.api.exercise.projectapi.entity.User;
import com.api.exercise.projectapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProjectService projectService;
    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findById(Long userId) {
       Optional<User> optionalUser = repository.findById(userId);
       if (optionalUser.isPresent()) {
           return optionalUser.get();
       } else {
           throw new EntityNotFoundException("User not found");
       }
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long userId) {
        repository.deleteById(userId);
    }
    public boolean isInvalidEmailFormat(String email) {
        return !EmailValidator.getInstance().isValid(email);
    }

    @Override
    public void assignUserToProject(Long userId, Long projectId){
        Project project = projectService.findById(projectId);
        User user = this.findById(userId);
        user.getProjects().add(project);
        this.save(user);
    }
}
