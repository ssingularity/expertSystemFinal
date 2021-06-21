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

    public List<Expert> findAll() {
        return expertdao.findAll();
    }

    public List<Expert> findByBlocked() {
        return expertdao.findByBlocked("是");
    }

    public Expert findById(String id) {
        return expertdao.findById(id);
    }

    public void blockById(String id) {
        expertdao.blockById(id);
    }

    public void unblockById(String id) {
        expertdao.unblockById(id);
    }

    public void deleteById(String id) {
        expertdao.deleteById(id);
    }
}
