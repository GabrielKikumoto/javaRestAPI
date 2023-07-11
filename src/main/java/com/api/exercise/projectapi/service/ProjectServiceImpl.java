package com.api.exercise.projectapi.service;

import com.api.exercise.projectapi.entity.Project;
import com.api.exercise.projectapi.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository repository;


    @Override
    public Project save(Project project) {
        return repository.save(project);
    }
    @Override
    public void delete(Project project) {
        repository.delete(project);
    }

    @Override
    public void deleteById(Long projectId) {
        repository.deleteById(projectId);
    }



    @Override
    public Project findById(Long projectId) {
        Optional<Project> projectOptional = repository.findById(projectId);
        if (projectOptional.isPresent()) {
            return projectOptional.get();
        } else {
            throw new EntityNotFoundException("Project not found");
        }
    }

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }
}
