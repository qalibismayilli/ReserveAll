package com.reserveall.reserveall.service;

import com.reserveall.reserveall.dto.ProjectRequestDto;
import com.reserveall.reserveall.dto.ProjectResponseDto;
import com.reserveall.reserveall.model.Project;
import com.reserveall.reserveall.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;


    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    private static ProjectResponseDto convertToProjectResponseDto(Project project){
        return  new ProjectResponseDto(project.getId(),
                project.getName(),
                project.getDescription(),
                project.getImages().stream().map(i -> i.getUrl()).toList());
    }

    @Transactional
    public ProjectResponseDto createProject(ProjectRequestDto request){
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

    @Transactional
    public ProjectRequestDto updateProject(){
        //TODO: ADD UPDATE METHOD BODY
        return null;
    }

    public ProjectResponseDto getProjectById(String projectId){
        Project fromDb = projectRepository.findById(projectId).orElseThrow();
        return convertToProjectResponseDto(fromDb);
    }

    public ProjectResponseDto getProjectByName(String projectName){
        Project fromDb = projectRepository.getProjectsByName(projectName).orElseThrow();
        return convertToProjectResponseDto(fromDb);
    }

}
