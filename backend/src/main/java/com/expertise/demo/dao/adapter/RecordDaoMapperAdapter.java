package com.expertise.demo.dao.adapter;

import com.expertise.demo.dao.RecordDao;
import com.expertise.demo.dao.mapper.RecordMapper;
import com.expertise.demo.entity.Record;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class RecordDaoMapperAdapter implements RecordDao {
    private final RecordMapper mapper;

    @Override
    public List<Record> findByExpertID(String id) {
        return mapper.findByExpertID(id);
    }

    @Override
    public List<Record> findByProgramID(String id) {
        return mapper.findByProgramID(id);
    }

    @Override
    public void deleteById(String id) {
        mapper.deleteById(id);
    }

    @Override
    public Record insert(Record r) {
        r.setId(UUID.randomUUID().toString());
        mapper.insert(r);
        return r;
    }
}
