package com.expertise.demo.dao.adapter;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.dao.mapper.ExpertMapper;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.PageableExperts;
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
    public PageableExperts findByPageable(String type, String search, int offset) {
        ExpertMapper.ExpertCondition condition = new ExpertMapper.ExpertCondition();
        condition.setOffset(offset * 20);
        search = "%" + search + "%";
        switch (type) {
            case "":
                break;
            case "1":
                condition.setName(search);
                break;
            case "2":
                condition.setType(search);
                break;
            case "3":
                condition.setArea(search);
                break;
            default:
                throw new RuntimeException("类型不存在");
        }
        List<Expert> experts = mapper.findByPageable(condition);
        int size = mapper.size(condition);
        return new PageableExperts(size, experts);
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
}
