package com.expertise.demo.service;

import com.expertise.demo.entity.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {
    Program insert(Program program);
    List<Program> findAll();
//    Optional<Program> findById(Integer id);
    Program findById(Integer id);
    void autoChoose(Integer id);
}
