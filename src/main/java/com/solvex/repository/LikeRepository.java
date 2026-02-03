package com.solvex.repository;
import com.solvex.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Likes,UUID>{

}
