package com.orcunsancar.issuemanagement.service.impl;

import java.util.Arrays;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orcunsancar.issuemanagement.dto.IssueDto;
import com.orcunsancar.issuemanagement.entity.Issue;
import com.orcunsancar.issuemanagement.repository.IssueRepository;
import com.orcunsancar.issuemanagement.service.IssueService;
import com.orcunsancar.issuemanagement.util.TPage;

@Service
public class IssueServiceImpl implements IssueService{

	private final IssueRepository issueRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public IssueServiceImpl(IssueRepository issueRepository,ModelMapper modelMapper) {
		this.issueRepository = issueRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public IssueDto save(IssueDto issue) {

		if(issue.getDate() == null) {
			throw new IllegalArgumentException("Issue Date cannot be null");
		}
		
		Issue issueEntity = modelMapper.map(issue, Issue.class);
		issueEntity =  issueRepository.save(issueEntity);
		issue.setId(issueEntity.getId());
		return issue;
	}

	@Override
	public IssueDto getById(Long id) {
		Issue issue = issueRepository.getOne(id);
		return modelMapper.map(issue, IssueDto.class);
	}

	@Override
	public TPage<IssueDto> getAllPageable(Pageable pageable) {
		Page<Issue> data = issueRepository.findAll(pageable);
        TPage<IssueDto> response = new TPage<IssueDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IssueDto[].class)));
        return response;
	}

	@Override
	public Boolean delete(Long issueId) {
		issueRepository.deleteById(issueId);
		return true;
	}

	@Override
	public IssueDto update(Long id,IssueDto issue) {
		return null;
	}


}
