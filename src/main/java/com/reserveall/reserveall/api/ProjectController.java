package com.reserveall.reserveall.api;

import com.reserveall.reserveall.dto.ProjectRequestDto;
import com.reserveall.reserveall.dto.ProjectResponseDto;
import com.reserveall.reserveall.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/createProject")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(request));
    }

    @PostMapping("/removeProject")
    public ResponseEntity<ProjectResponseDto> removeProject(@RequestParam String projectId) {
        return ResponseEntity.ok(projectService.removeProject(projectId));
    }

    @GetMapping("getProjectById")
    public ResponseEntity<ProjectResponseDto> getProjectById(@RequestParam String projectId){
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @GetMapping("getProjectsByName")
    public ResponseEntity<List<ProjectResponseDto>> getProjectsByName(@RequestParam String projectId){
        return ResponseEntity.ok(projectService.getProjectsByName(projectId));
    }


}
