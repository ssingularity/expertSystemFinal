package com.expertise.demo.dao;

import com.expertise.demo.entity.Program;

import java.util.List;

public interface ProgramDao {
    List<Program> findAll();

    Program findById(String id);

    Program insert(Program p);

    void changeState(String id);
}
