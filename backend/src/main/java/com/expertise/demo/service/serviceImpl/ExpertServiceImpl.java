package com.expertise.demo.service.serviceImpl;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
//        return expertrepository.findAll();
    }

    public List<Expert> findByName(String name){
        return expertdao.findByName(name);
    }

    @Override
    public Expert findById(String id) {

        return expertdao.findById(id);
    }
}
