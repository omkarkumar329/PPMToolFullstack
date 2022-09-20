package com.omkar.PPMToolFullstack.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkar.PPMToolFullstack.Domain.Project;
import com.omkar.PPMToolFullstack.Repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        //Logic

        return projectRepository.save(project);
    }

}
