package com.orcunsancar.issuemanagement.dto;

import java.util.Date;

import com.orcunsancar.issuemanagement.entity.IssueStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {

	private Long id;
	private String description;
	private String details;
	private Date date;
	private IssueStatus issueStatus;
	private UserDto assignee;
	private ProjectDto project;
	
}
