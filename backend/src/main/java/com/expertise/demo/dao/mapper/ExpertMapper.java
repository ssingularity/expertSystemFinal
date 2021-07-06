package com.expertise.demo.dao.mapper;

import com.expertise.demo.entity.Expert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpertMapper {
    void insert(Expert expert);

    List<Expert> findAll();

    List<Expert> findByPageable(int offset);

    Expert findById(String id);

    List<Expert> findByBlocked(String blocked);

    void deleteById(String id);

    int size();
}
