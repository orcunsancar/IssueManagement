package com.orcunsancar.issuemanagement.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcunsancar.issuemanagement.dto.IssueDetailDto;
import com.orcunsancar.issuemanagement.dto.IssueDto;
import com.orcunsancar.issuemanagement.dto.IssueUpdateDto;
import com.orcunsancar.issuemanagement.entity.IssueStatus;
import com.orcunsancar.issuemanagement.service.impl.IssueServiceImpl;
import com.orcunsancar.issuemanagement.util.ApiPaths;
import com.orcunsancar.issuemanagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
public class IssueController {

    private final IssueServiceImpl issueServiceImpl;

    public IssueController(IssueServiceImpl issueServiceImpl) {
        this.issueServiceImpl = issueServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = IssueDto.class)
    public ResponseEntity<TPage<IssueDto>> getAllByPagination(Pageable pageable) {
        TPage<IssueDto> data = issueServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> getById(@PathVariable(value = "id", required = true) Long id) {
        IssueDto issue = issueServiceImpl.getById(id);
        return ResponseEntity.ok(issue);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
    public ResponseEntity<IssueDetailDto> getByIdWithDetails(@PathVariable(value = "id", required = true) Long id) {
        IssueDetailDto detailDto = issueServiceImpl.getByIdWithDetails(id);
        return ResponseEntity.ok(detailDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> createProject(@Valid @RequestBody IssueDto issue) {
        return ResponseEntity.ok(issueServiceImpl.save(issue));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = IssueDto.class)
    public ResponseEntity<IssueDetailDto> updateProject(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody IssueUpdateDto issue) {
        return ResponseEntity.ok(issueServiceImpl.update(id,issue));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(issueServiceImpl.delete(id));
    }

    @GetMapping("/statuses")
    @ApiOperation(value = "Get All Issue Statuses Operation", response = String.class, responseContainer = "List")
    public ResponseEntity<List<IssueStatus>> getAll() {
        return ResponseEntity.ok(Arrays.asList(IssueStatus.values()));
    }
}
