package com.solvex.repository;
import com.solvex.entity.Project;
import com.solvex.entity.Project.Visibility;
import  org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository <Project,UUID>{

    // find project by id
    Optional<Project> findById(UUID id);

    // find project by problem id
    List<Project> findByProblemIdAndVisibility(UUID problemId,Visibility visibility);

    // find project by owner id
    List<Project> findByOwnerId(UUID ownerId);

    Optional<Project> findByIdAndOwnerId(UUID id,UUID ownerId);

    //find project by its visibility
    List <Project> findByVisibility(Visibility visibility);

    // count all users project
    long countByOwnerId(UUID id);

    // delete one project
    void deleteProjectById(UUID id);

    // delete a project when a user deletes the account
    void deleteProjectByOwnerId(UUID id);
}
