package com.orcunsancar.issuemanagement.service;

import com.orcunsancar.issuemanagement.dto.IssueDto;
import com.orcunsancar.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

public interface IssueService {

    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(Long issue);

    IssueDto update(Long id, IssueDto project);
}
