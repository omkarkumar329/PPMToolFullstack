package com.omkar.PPMToolFullstack.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.PPMToolFullstack.Domain.Project;
import com.omkar.PPMToolFullstack.Services.MapValidationErrorService;
import com.omkar.PPMToolFullstack.Services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

    	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }
    
    @GetMapping("/identifier/{projectIdentifier}")
    public ResponseEntity<?> getProjectByprojectIdentifier(@PathVariable String projectIdentifier){

        Project project = projectService.findProjectByprojectIdentifier(projectIdentifier);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
    @GetMapping("/id/{projectId}")
    public ResponseEntity<?> getProjectByprojectId(@PathVariable Long projectId){

        Project project = projectService.findProjectByprojectId(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
    
    
    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
    	return projectService.findAllProjects();
    	}
    
    @DeleteMapping("/deleteByIdentifier/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier){
        projectService.deleteProjectByIdentifier(projectIdentifier);

        return new ResponseEntity<String>("Project with ID: '"+projectIdentifier+"' was deleted", HttpStatus.OK);
    }

}