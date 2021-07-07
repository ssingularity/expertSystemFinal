package com.expertise.demo.dao;

import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.PageableExperts;

import java.util.List;

public interface ExpertDao {
    List<Expert> findAll();

    PageableExperts findByPageable(String type, String search, int offset);

    Expert findById(String id);

    List<Expert> findByBlocked(String blocked);

    Expert insert(Expert expert);

    void deleteById(String id);
}
