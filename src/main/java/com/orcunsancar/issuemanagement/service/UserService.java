package com.orcunsancar.issuemanagement.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.orcunsancar.issuemanagement.entity.Issue;
import com.orcunsancar.issuemanagement.entity.IssueHistory;
import com.orcunsancar.issuemanagement.entity.Project;
import com.orcunsancar.issuemanagement.entity.User;

public interface UserService {

	User save(User user);
	User getById(Long id);
	Page<User> getAllPageable(Pageable pageable);
	User getByUsername(String userName);
}
