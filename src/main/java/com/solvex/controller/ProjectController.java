package com.solvex.controller;
import com.solvex.dto.ProjectRequest;
import com.solvex.dto.ProjectResponse;
import com.solvex.entity.Project;
import com.solvex.entity.User;
import com.solvex.service.ProjectService;
import com.solvex.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor

public class ProjectController {
    private final UserService userService;
    private final ProjectService projectService;
    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest request, HttpSession session){
        UUID userId = (UUID) session.getAttribute("SOLVEX_SESSION");
        if(userId == null){
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(403))
                    .body(Map.of(
                            "error", "Login please"
                    ));
        }

        User user = userService.getUser(userId);
        if(user.getRole() != User.Role.INNOVATOR){
            ResponseEntity
                    .status(HttpStatusCode.valueOf(403))
                    .body(Map.of(
                            "error", "Please create account as innovator"
                    ));
        }

        projectService.createProject(
                request.getProjectName(),
                request.getProjectDescription(),
                request.getUsefulLinks(),
                request.getVisibility(),
                user,
                request.getProblem());

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(Map.of(
                        "success", "Project created successfully"
                ));
    }

    @GetMapping("/public")
    ResponseEntity<?> viewPublicProjects(){
        List<Project> projectsList = projectService.getPublicProjects();
        List<ProjectResponse> responses = new ArrayList<>();
        for (Project project : projectsList){
            ProjectResponse projectResponse = new ProjectResponse(
                    project.getProjectName(),
                    project.getProjectDescription(),
                    project.getUsefulLinks(),
                    project.getOwner().getFullName(),
                    project.getProblem()
            );

            responses.add(projectResponse);
        }

        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(Map.of(
                        "projects",responses
                ));
    }

}
