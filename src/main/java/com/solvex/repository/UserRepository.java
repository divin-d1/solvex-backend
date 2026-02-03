package com.solvex.repository;
import com.solvex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,UUID>{
    Optional<User> findById(UUID id);
    boolean existsByEmail(String email);

    User findByEmail(String email);
}