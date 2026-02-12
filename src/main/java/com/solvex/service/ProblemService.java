package com.solvex.service;
import com.solvex.entity.Problem;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.solvex.repository.ProblemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;

    public Problem createProblem(String problemTitle, String problemDescription){
        Problem problem = new Problem();
        problem.setProblemTitle(problemTitle);
        problem.setProblemDescription(problemDescription);
        return problemRepository.save(problem);
    }

    public List<Problem> getAllProblems(){
        return problemRepository.findAll();
    }
}