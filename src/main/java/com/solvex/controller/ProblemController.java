package com.solvex.controller;
import com.solvex.dto.ProblemRequest;
import com.solvex.entity.Problem;
import com.solvex.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @PostMapping("/create")
    public ResponseEntity<?> createProblem(@RequestBody ProblemRequest request){
        Problem problem = problemService.createProblem(
                request.getProblemTitle(),
                request.getProblemDescription()
        );

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(Map.of(
                        "success","Problem created",
                        "problem", problem
                ));
    }

    @GetMapping("/all")
    public List<Problem> getProblems(){
        return problemService.getAllProblems();
    }
}
