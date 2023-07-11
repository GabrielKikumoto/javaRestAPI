package com.api.exercise.projectapi.service;

import com.api.exercise.projectapi.entity.Project;

import java.util.List;

public interface ProjectService {

    // CRUD actions
    Project save(Project project);
    Project findById(Long projectId);
    List<Project> findAll();

    void delete(Project project);

    void deleteById(Long projectId);


}
