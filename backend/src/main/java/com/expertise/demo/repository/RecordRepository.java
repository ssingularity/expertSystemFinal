package com.expertise.demo.repository;

import com.expertise.demo.entity.Program;
import com.expertise.demo.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,String> {
    List<Record> findByExpertID(String eid);
    List<Record> findByProgramID(Integer pid);
    Record findById(Integer id);
    void deleteById(Integer id);
}
