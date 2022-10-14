package com.orcunsancar.issuemanagement.api;

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

import com.orcunsancar.issuemanagement.dto.ProjectDto;
import com.orcunsancar.issuemanagement.service.impl.ProjectServiceImpl;
import com.orcunsancar.issuemanagement.util.ApiPaths;
import com.orcunsancar.issuemanagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value = ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
@Slf4j
public class ProjectController {

    private final ProjectServiceImpl projectServiceImpl;

    public ProjectController(ProjectServiceImpl projectServiceImpl) {
        this.projectServiceImpl = projectServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = ProjectDto.class)
    public ResponseEntity<TPage<ProjectDto>> getAllByPagination(Pageable pageable) {
        TPage<ProjectDto> data = projectServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping()
    @ApiOperation(value = "Get All Operation", response = ProjectDto.class , responseContainer = "List")
    public ResponseEntity<List<ProjectDto>> getAll() {
        List<ProjectDto> data = projectServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> getById(@PathVariable(value = "id", required = true) Long id) {
        log.info("ProjectController-> GetByID ");
        log.debug("ProjectController-> GetByID -> PARAM:" + id);
        ProjectDto projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto project) {
        return ResponseEntity.ok(projectServiceImpl.save(project));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> updateProject(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody ProjectDto project) {
        return ResponseEntity.ok(projectServiceImpl.update(id, project));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(projectServiceImpl.delete(id));
    }
}
