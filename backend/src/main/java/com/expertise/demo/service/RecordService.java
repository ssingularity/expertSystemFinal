package com.expertise.demo.service;

import com.expertise.demo.entity.Record;

import java.util.List;

public interface RecordService {
    Record insert(Record record);
    Record update(Record record);
    Record findById(Integer id);
    List<Record> findByExpert(String eid);
    List<Record> findByProgram(Integer pid);
    String delete(Integer id);
}
