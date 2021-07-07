package com.expertise.demo.dao.mapper;

import com.expertise.demo.entity.Expert;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpertMapper {
    void insert(Expert expert);

    List<Expert> findAll();

    List<Expert> findByPageable(ExpertCondition condition);

    Expert findById(String id);

    List<Expert> findByBlocked(String blocked);

    void deleteById(String id);

    int size(ExpertCondition condition);

    @Data
    public static class ExpertCondition {
        int offset;

        String name;

        String type;

        String area;
    }
}
