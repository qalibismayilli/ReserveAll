package com.reserveall.reserveall.service;

import com.reserveall.reserveall.dto.request.ProjectRequestDto;
import com.reserveall.reserveall.dto.response.ProjectResponseDto;
import com.reserveall.reserveall.exception.GenericException;
import com.reserveall.reserveall.model.Project;
import com.reserveall.reserveall.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;


    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    private static ProjectResponseDto convertToProjectResponseDto(@NotNull Project project){
        return  new ProjectResponseDto(project.getId(),
                project.getName(),
                project.getDescription(),
                project.getImages().stream().map(i -> i.getUrl()).toList());
    }

    @Transactional
    public ProjectResponseDto createProject(@NotNull ProjectRequestDto request){
        Project project = new Project.Builder().
                name(request.getName()).
                description(request.getDescription())
                .build();

        final Project fromDb = projectRepository.save(project);

        return convertToProjectResponseDto(fromDb);
    }

    @Transactional
    public ProjectResponseDto removeProject(String projectId){
        Project fromDb = projectRepository.findById(projectId).orElseThrow();
        projectRepository.delete(fromDb);

        return convertToProjectResponseDto(fromDb);
    }

    public List<ProjectResponseDto> getALlProjects(){
        return projectRepository.findAll()
                .stream()
                .map(p -> convertToProjectResponseDto(p)).toList();
    }

    protected Project getOriginalProjectById(String projectId){
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new GenericException("project not found by id: " + projectId));
    }

    public List<ProjectResponseDto> getProjectsByName(String projectName){
        List<Project> fromDb = projectRepository.getProjectsByName(projectName).orElseThrow();
        if(fromDb.size() == 0){
            throw new GenericException("project not found by name: " + projectName);
        }
        return fromDb.stream().map(p -> convertToProjectResponseDto(p)).toList();
    }

}
