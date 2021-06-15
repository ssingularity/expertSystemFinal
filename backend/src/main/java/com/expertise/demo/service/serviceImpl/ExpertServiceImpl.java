package com.expertise.demo.service.serviceImpl;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private ExpertDao expertdao;


    public Expert insert(Expert expert){
        return expertdao.insert(expert);
    }

    public List <Expert> findAll(){
        return expertdao.findAll();
    }

    public List<Expert> findByName(String name){
        return expertdao.findByName(name);
    }
    public List<Expert> findByBlocked(String blocked){
        return expertdao.findByBlocked(blocked);
    }

    @Override
    public Expert findById(String id) {
        return expertdao.findById(id);
    }

    @Override
    public void blockById(String id) {
        expertdao.blockById(id);
    }

    @Override
    public void unblockById(String id) {
        expertdao.unblockById(id);
    }

    @Override
    public void deleteById(String id) {
        expertdao.deleteById(id);
    }
}
