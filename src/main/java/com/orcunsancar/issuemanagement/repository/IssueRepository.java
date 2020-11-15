package com.orcunsancar.issuemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcunsancar.issuemanagement.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long>{

}
