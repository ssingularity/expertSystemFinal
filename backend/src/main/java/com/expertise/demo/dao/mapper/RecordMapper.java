package com.expertise.demo.dao.mapper;

import com.expertise.demo.entity.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    List<Record> findByExpertID(String id);

    List<Record> findByProgramID(String id);

    void deleteById(String id);

    void insert(Record r);
}
