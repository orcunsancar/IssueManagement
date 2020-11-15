package com.orcunsancar.issuemanagement.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orcunsancar.issuemanagement.dto.ProjectDto;
import com.orcunsancar.issuemanagement.entity.Project;
import com.orcunsancar.issuemanagement.repository.ProjectRepository;
import com.orcunsancar.issuemanagement.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;
	private final ModelMapper modelMapper;
	
	public ProjectServiceImpl(ProjectRepository projectRepository,ModelMapper modelMapper){
		this.projectRepository = projectRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ProjectDto save(ProjectDto project) {
		
		Project projectCheck = projectRepository.getByProjectCode(project.getProjectCode());
		
		if(projectCheck != null) {
			throw new IllegalArgumentException("Project Code Already Exist");
		}
		
		Project p = modelMapper.map(project, Project.class);
		p = projectRepository.save(p);
		project.setId(p.getId());
		return project;
	}

	@Override
	public ProjectDto getById(Long id) {
		Project p = projectRepository.getOne(id);
		return modelMapper.map(p, ProjectDto.class);
		
	}

	@Override
	public ProjectDto getByProjectCode(String projectCode) {
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
	
	@Override
	public Boolean delete(Long id) {
		projectRepository.deleteById(id);
		return true;
	}

	@Override
	public ProjectDto update(Long id, ProjectDto project) {
		
		Project projectDb = projectRepository.getOne(id);
		if(projectDb == null) {
			throw new IllegalArgumentException("Project Does Not Exist ID: " + id);
		}
		
		Project projectCheck = projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(),id);
		
		if(projectCheck != null && projectCheck.getId() != projectDb.getId()) {
			throw new IllegalArgumentException("Project Code Already Exist");
		}
		
		projectDb.setProjectCode(project.getProjectCode());
		projectDb.setProjectName(project.getProjectName());
		
		projectRepository.save(projectDb);
		return modelMapper.map(projectDb, ProjectDto.class);
	}

}
