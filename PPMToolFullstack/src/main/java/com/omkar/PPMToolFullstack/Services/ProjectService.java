package com.omkar.PPMToolFullstack.Services;

import com.omkar.PPMToolFullstack.Domain.Project;

public interface ProjectService {

	Project saveOrUpdateProject(Project project);

	Project findProjectByprojectIdentifier(String projectIdentifier);

	Project findProjectByprojectId(Long projectId);

	Iterable<Project> findAllProjects();

	void deleteProjectByIdentifier(String projectIdentifier);

}
