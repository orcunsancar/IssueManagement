package com.orcunsancar.issuemanagement.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orcunsancar.issuemanagement.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

	Project getByProjectCode(String projectCode);
	
	List<Project> getByProjectCodeContains(String projectCode);
	
	Page<Project> findAll(Pageable pageable);
	
	List<Project> findAll(Sort sort);
	Project getByProjectCodeAndIdNot(String projectCode, Long id);
}
