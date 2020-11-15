package com.orcunsancar.issuemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcunsancar.issuemanagement.entity.IssueHistory;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long>{

}
