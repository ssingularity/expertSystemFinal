package com.expertise.demo.service;

import com.expertise.demo.entity.Program;

import java.util.List;

public interface ProgramService {
    Program insert(Program program);
    List<Program> findAll();
    Program findById(String id);
    String autoChoose(String id);
    void changeState(String id);
}
