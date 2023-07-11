package com.api.exercise.projectapi.controller;

import com.api.exercise.projectapi.entity.Project;
import com.api.exercise.projectapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/projects")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getAllProjects(){
        return projectService.findAll();
    }

    @GetMapping(value = "/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId){
        return new ResponseEntity<>(projectService.findById(projectId), HttpStatus.OK);
    }

    @PostMapping(value = "/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody Project project) {
        projectService.save(project);
    }

    @PutMapping(value = "/projects")
    @ResponseStatus(HttpStatus.OK)
    public void updateProject(@RequestBody Project project){
        projectService.save(project);
    }

    @DeleteMapping(value = "/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteById(projectId);
    }

}
