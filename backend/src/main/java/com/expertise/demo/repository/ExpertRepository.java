package com.expertise.demo.repository;

import com.expertise.demo.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert,String> {
    Expert findByName(String name);
//    Expert findById(String id);
    Optional<Expert> findById(String id);

}
