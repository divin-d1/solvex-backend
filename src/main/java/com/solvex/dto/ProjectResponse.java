package com.solvex.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvex.entity.Problem;
import com.solvex.entity.User;

public record ProjectResponse (
        String projectName,
        String projectDescription,
        JsonNode usefulLinks,
        String ownerName,
        Problem problem
) {}
