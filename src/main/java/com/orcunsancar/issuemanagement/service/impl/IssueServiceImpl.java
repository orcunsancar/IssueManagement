package com.orcunsancar.issuemanagement.service.impl;

import com.orcunsancar.issuemanagement.dto.IssueDetailDto;
import com.orcunsancar.issuemanagement.dto.IssueDto;
import com.orcunsancar.issuemanagement.dto.IssueHistoryDto;
import com.orcunsancar.issuemanagement.dto.IssueUpdateDto;
import com.orcunsancar.issuemanagement.entity.Issue;
import com.orcunsancar.issuemanagement.entity.IssueStatus;
import com.orcunsancar.issuemanagement.entity.User;
import com.orcunsancar.issuemanagement.repository.IssueRepository;
import com.orcunsancar.issuemanagement.repository.ProjectRepository;
import com.orcunsancar.issuemanagement.repository.UserRepository;
import com.orcunsancar.issuemanagement.service.IssueHistoryService;
import com.orcunsancar.issuemanagement.service.IssueService;
import com.orcunsancar.issuemanagement.service.ProjectService;
import com.orcunsancar.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final IssueHistoryService issueHistoryService;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository,ProjectRepository projectRepository, UserRepository userRepository, IssueHistoryService issueHistoryService, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.issueHistoryService = issueHistoryService;
        this.userRepository =userRepository;
        this.projectRepository=projectRepository;
    }

    @Override
    public IssueDto save(IssueDto issue) {
        // Bussiness Logic
        issue.setDate(new Date());
        issue.setIssueStatus(IssueStatus.OPEN);


        Issue issueEntity = modelMapper.map(issue, Issue.class);

        issueEntity.setProject(projectRepository.getOne(issue.getProjectId()));
        issueEntity = issueRepository.save(issueEntity);

        issue.setId(issueEntity.getId());
        return issue;
    }

    @Transactional
    public IssueDetailDto update(Long id, IssueUpdateDto issue) {
        Issue issueDb = issueRepository.getOne(id);
        User user = userRepository.getOne(issue.getAssignee_id());
        issueHistoryService.addHistory(id,issueDb);

        issueDb.setAssignee(user);
        issueDb.setDate(issue.getDate());
        issueDb.setDescription(issue.getDescription());
        issueDb.setDetails(issue.getDetails());
        issueDb.setIssueStatus(issue.getIssueStatus());
        issueDb.setProject(projectRepository.getOne(issue.getProject_id()));
        issueRepository.save(issueDb);
        return getByIdWithDetails(id);
    }

    @Override
    public IssueDto getById(Long id) {
        Issue issue = issueRepository.getOne(id);
        return modelMapper.map(issue, IssueDto.class);
    }

    public IssueDetailDto getByIdWithDetails(Long id) {
        Issue issue = issueRepository.getOne(id);
        IssueDetailDto detailDto = modelMapper.map(issue, IssueDetailDto.class);
        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueId(issue.getId());
        detailDto.setIssueHistories(issueHistoryDtos);
        return detailDto;
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data = issueRepository.findAll(pageable);
        TPage<IssueDto> respnose = new TPage<IssueDto>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IssueDto[].class)));
        return respnose;
    }

    public List<IssueDto> getAll() {
        List<Issue> data = issueRepository.findAll();
        return Arrays.asList(modelMapper.map(data, IssueDto[].class));
    }

    @Override
    public Boolean delete(Long issueId) {
        issueRepository.deleteById(issueId);
        return true;
    }

    @Override
    public IssueDto update(Long id, IssueDto project) {
        return null;
    }

}
