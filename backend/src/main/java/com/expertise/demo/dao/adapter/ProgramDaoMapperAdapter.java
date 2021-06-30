package com.expertise.demo.dao.adapter;

import com.expertise.demo.dao.ProgramDao;
import com.expertise.demo.dao.mapper.ProgramMapper;
import com.expertise.demo.entity.Program;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProgramDaoMapperAdapter implements ProgramDao {
    private final ProgramMapper mapper;

    @Override
    public List<Program> findAll() {
        return mapper.findAll();
    }

    @Override
    public Program findById(String id) {
        return mapper.findById(id);
    }

    @Override
    public Program insert(Program p) {
        p.setId(UUID.randomUUID().toString());
        mapper.insert(p);
        return p;
    }

    @Override
    public void changeState(String id) {
        mapper.changeState(id);
    }
}
