package com.orcunsancar.issuemanagement.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.orcunsancar.issuemanagement.dto.ProjectDto;
import com.orcunsancar.issuemanagement.entity.Project;

public interface ProjectService {

	ProjectDto save(ProjectDto project);
	ProjectDto getById(Long id);
	ProjectDto getByProjectCode(String projectCode);
	List<Project> getByProjectCodeContains(String projectCode);
	Page<Project> getAllPageable(Pageable pageable);
	Boolean delete(Project project);
	ProjectDto update(Long id, ProjectDto project);
	Boolean delete(Long id);
}
