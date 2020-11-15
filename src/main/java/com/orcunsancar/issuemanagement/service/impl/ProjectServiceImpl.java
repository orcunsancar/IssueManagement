package com.orcunsancar.issuemanagement.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orcunsancar.issuemanagement.entity.Project;
import com.orcunsancar.issuemanagement.repository.ProjectRepository;
import com.orcunsancar.issuemanagement.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;
		
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public Project save(Project project) {
			
		if(project.getProjectCode() == null) {
			throw new IllegalArgumentException("Project code cannot be null");
		}
		project = projectRepository.save(project);
		return project;
	}

	@Override
	public Project getById(Long id) {
		return projectRepository.getOne(id);
	}

	@Override
	public List<Project> getByProjectCode(String projectCode) {
		return null;
	}

	@Override
	public List<Project> getByProjectCodeContains(String projectCode) {
		return null;
	}

	@Override
	public Page<Project> getAllPageable(Pageable pageable) {
		return projectRepository.findAll(pageable);
	}

	@Override
	public Boolean delete(Project project) {
		return null;
	}

}
