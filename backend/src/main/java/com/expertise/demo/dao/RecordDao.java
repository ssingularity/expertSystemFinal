package com.expertise.demo.dao;

import com.expertise.demo.entity.Record;

import java.util.List;

public interface RecordDao {
    List<Record> findByExpertID(String id);

    List<Record> findByProgramID(String id);

    void deleteById(String id);

    Record insert(Record r);
}
