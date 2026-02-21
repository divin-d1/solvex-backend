package com.solvex.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.solvex.Exception.NotFoundException;
import com.solvex.entity.Problem;
import com.solvex.entity.Project;
import com.solvex.entity.Project.Visibility;
import com.solvex.entity.User;
import com.solvex.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project createProject(String projectName, String projectDescription, JsonNode usefulLinks, Visibility visibility, User ownerId, Problem problemId){
        Project newProject = new Project();
        newProject.setProjectName(projectName);
        newProject.setProjectDescription(projectDescription);
        newProject.setUsefulLinks(usefulLinks);
        newProject.setVisibility(visibility);
        newProject.setOwner(ownerId);
        newProject.setProblem(problemId);

        return projectRepository.save(newProject);
    }

    // for public displays
    public List<Project> getPublicProjects(){
        return projectRepository.findByVisibility(Visibility.PUBLIC);
    }

    // for dashboards of innovator
    public List<Project> getInnovatorProjects(UUID ownerId){
        return projectRepository.findByOwnerId(ownerId);
    }

    // for public displays
    public List<Project> getProjectsByProblem(UUID problemId){
        return projectRepository.findByProblemIdAndVisibility(problemId,Visibility.PUBLIC);
    }

    // Get a project
    public Project getProject(UUID projectId){
        return projectRepository.findById(projectId).orElseThrow(()-> new NotFoundException("Project not found"));
    }

    // to safely delete the project
    public void deleteProject(UUID ownerId, UUID projectId){
       Project project =  projectRepository.findByIdAndOwnerId(projectId,ownerId)
               .orElseThrow(()-> new NotFoundException("Project not found"));
       projectRepository.delete(project);
    }
}
