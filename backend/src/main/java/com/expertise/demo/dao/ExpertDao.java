package com.expertise.demo.dao;

import com.expertise.demo.entity.Expert;

import java.util.List;

public interface ExpertDao {
    List<Expert> findAll();

    Expert findById(String id);

    List<Expert> findByBlocked(String blocked);

    Expert insert(Expert expert);

    void deleteById(String id);
}
