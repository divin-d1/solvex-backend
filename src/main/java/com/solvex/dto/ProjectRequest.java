package com.solvex.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvex.entity.Problem;
import com.solvex.entity.Project.Visibility;
import com.solvex.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {
    String projectName;
    String projectDescription;
    Visibility visibility;
    JsonNode usefulLinks;
    Problem problem;
}
