package com.expertise.demo.service;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.entity.Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertService {

    @Autowired
    private ExpertDao expertdao;


    public Expert insert(Expert expert) {
        return expertdao.insert(expert);
    }

    public void update(Expert expert) {
        expertdao.deleteById(expert.getId());
        expertdao.insert(expert);
    }

    public List<Expert> findAll() {
        return expertdao.findAll();
    }

    public List<Expert> findByBlocked() {
        return expertdao.findByBlocked("是");
    }

    public Expert findById(String id) {
        return expertdao.findById(id);
    }
}
