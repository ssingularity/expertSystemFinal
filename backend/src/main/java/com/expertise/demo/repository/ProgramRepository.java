package com.expertise.demo.repository;

import com.expertise.demo.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program,String> {
//    Optional<Program> findById(Integer id);
    Program findById(Integer id);
}
