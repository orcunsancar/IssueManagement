package com.orcunsancar.issuemanagement.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orcunsancar.issuemanagement.entity.IssueHistory;
import com.orcunsancar.issuemanagement.repository.IssueHistoryRepository;
import com.orcunsancar.issuemanagement.service.IssueHistoryService;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService{

	private final IssueHistoryRepository issueHistoryRepository;
	
	
	public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository) {
		this.issueHistoryRepository = issueHistoryRepository;
	}

	@Override
	public IssueHistory save(IssueHistory issueHistory) {
		// Business logic
		if(issueHistory.getDate() == null) {
			throw new IllegalArgumentException("Issue cannot be null");
		}
		
		issueHistory = issueHistoryRepository.save(issueHistory);
		return issueHistory;
	}

	@Override
	public IssueHistory getById(Long id) {
		return issueHistoryRepository.getOne(id);
	}

	@Override
	public Page<IssueHistory> getAllPageable(Pageable pageable) {
		return issueHistoryRepository.findAll(pageable);
	}

	@Override
	public Boolean delete(IssueHistory issueHistory) {
		issueHistoryRepository.delete(issueHistory);
		return Boolean.TRUE;
	}

	
}
