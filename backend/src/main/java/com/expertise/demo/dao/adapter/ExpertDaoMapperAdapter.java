package com.expertise.demo.dao.adapter;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.dao.mapper.ExpertMapper;
import com.expertise.demo.entity.Expert;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.List;

@RequiredArgsConstructor
public class ExpertDaoMapperAdapter implements ExpertDao {
    private final ExpertMapper mapper;

    @Override
    public List<Expert> findAll() {
        return mapper.findAll();
    }

    @Override
    public List<Expert> findByPageable(int offset) {
        return mapper.findByPageable(offset * 20);
    }

    @Override
    public Expert findById(String id) {
        return mapper.findById(id);
    }

    @Override
    public List<Expert> findByBlocked(String blocked) {
        return mapper.findByBlocked(blocked);
    }

    @Override
    public Expert insert(Expert expert) {
        Assert.isTrue(expert.getId() != null, "专家ID不可为空");
        mapper.insert(expert);
        return expert;
    }

    @Override
    public void deleteById(String id) {
        mapper.deleteById(id);
    }

    @Override
    public Integer size() {
        return mapper.size();
    }
}
