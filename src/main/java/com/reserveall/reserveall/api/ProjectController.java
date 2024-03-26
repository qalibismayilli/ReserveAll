package com.reserveall.reserveall.api;

import com.reserveall.reserveall.dto.request.ProjectRequestDto;
import com.reserveall.reserveall.dto.response.ProjectResponseDto;
import com.reserveall.reserveall.service.ProjectService;
import jakarta.validation.Valid;
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

    @PostMapping("/admin/createProject")
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(request));
    }

    @PostMapping("/admin/removeProject")
    public ResponseEntity<ProjectResponseDto> removeProject(@RequestParam String projectId) {
        return ResponseEntity.ok(projectService.removeProject(projectId));
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects(){
        return ResponseEntity.ok(projectService.getALlProjects());
    }

    @GetMapping("/getProjectsByName")
    public ResponseEntity<List<ProjectResponseDto>> getProjectsByName(@RequestParam String projectName){
        return ResponseEntity.ok(projectService.getProjectsByName(projectName));
    }
}
