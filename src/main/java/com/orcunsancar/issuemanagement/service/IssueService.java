package com.orcunsancar.issuemanagement.service;



import org.springframework.data.domain.Pageable;

import com.orcunsancar.issuemanagement.dto.IssueDto;
import com.orcunsancar.issuemanagement.util.TPage;

public interface IssueService {

	IssueDto save(IssueDto issue);
	IssueDto getById(Long id);
	TPage<IssueDto> getAllPageable(Pageable pageable);
	Boolean delete(IssueDto issue);
}
