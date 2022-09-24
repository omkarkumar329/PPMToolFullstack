package com.omkar.PPMToolFullstack.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkar.PPMToolFullstack.Domain.Project;
import com.omkar.PPMToolFullstack.Exception.ProjectIdException;
import com.omkar.PPMToolFullstack.Repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
    private ProjectRepository projectRepository;

	@Override
    public Project saveOrUpdateProject(Project project){

        //Logic
    	try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }

    }

	

	@Override
	public Project findProjectByprojectIdentifier(String projectIdentifier) {
		// TODO Auto-generated method stub
		 Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
      System.out.println(project);
	        if(project == null){
	            throw new ProjectIdException("Project Identifier '"+projectIdentifier+"' does not exist");

	        }
	        return project;
	    }



	@Override
	public Project findProjectByprojectId(Long projectId) {
		 Project project = projectRepository.findById(projectId).get();

	        if(project == null){
	            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");

	        }
	        return project;
	}



	@Override
	public Iterable<Project> findAllProjects() {
		
		return projectRepository.findAll();
	}



	@Override
	public void deleteProjectByIdentifier(String projectIdentifier) {
		// TODO Auto-generated method stub
		Project project =projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if(project == null){
            throw new ProjectIdException("Project ID '"+projectIdentifier+"' does not exist");

        }
		projectRepository.delete(project);
		
	}
		
	}


