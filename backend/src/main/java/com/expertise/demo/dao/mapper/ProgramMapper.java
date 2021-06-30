package com.expertise.demo.dao.mapper;

import com.expertise.demo.entity.Program;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProgramMapper {
    List<Program> findAll();

    Program findById(String id);

    void insert(Program p);

    void changeState(String id);
}
