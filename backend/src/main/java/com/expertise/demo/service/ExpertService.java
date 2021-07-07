package com.expertise.demo.service;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.PageableExperts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertService {

    @Autowired
    private ExpertDao expertdao;


    public Expert insert(Expert expert) {
        Expert oldExpert = expertdao.findById(expert.getId());
        if (oldExpert != null) {
            throw new RuntimeException("ID为" + expert.getId() + "的专家已经在专家库中，不可重复添加");
        }
        return expertdao.insert(expert);
    }

    public void update(Expert expert) {
        expertdao.deleteById(expert.getId());
        expertdao.insert(expert);
    }

    public List<Expert> findAll() {
        return expertdao.findAll();
    }

    public PageableExperts findByPageable(String type, String search, int offset) {
        return expertdao.findByPageable(type, search, offset);
    }

    public List<Expert> findByBlocked() {
        return expertdao.findByBlocked("是");
    }

    public Expert findById(String id) {
        return expertdao.findById(id);
    }
}
