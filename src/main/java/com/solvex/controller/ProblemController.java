package com.solvex.controller;
import com.solvex.dto.ProblemRequest;
import com.solvex.entity.Problem;
import com.solvex.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @PostMapping
    public ResponseEntity<?> createProblem(@RequestBody ProblemRequest request){
        Problem problem = problemService.createProblem(
                request.getProblemTitle(),
                request.getProblemDescription()
        );

        return ResponseEntity.ok(problem);
    }

    @GetMapping
    public List<Problem> getProblems(){
        return problemService.getAllProblems();
    }
}
