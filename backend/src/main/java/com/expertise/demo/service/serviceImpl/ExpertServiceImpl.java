package com.expertise.demo.service.serviceImpl;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.repository.ExpertRepository;
import com.expertise.demo.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private ExpertRepository expertrepository;

    @Autowired
    private ExpertDao expertdao;

    public Expert insert(String name, String gender, Date birth,String phone,String type, String area, String intro, String company,String secret){
        Expert expert=new Expert(name,gender,birth,phone,type,area,intro,company,secret);
        return expertrepository.save(expert);
    }

    public Expert insert(Expert expert){
        return expertrepository.save(expert);
    }

    public List <Expert> findAll(){
        return expertdao.findAll();
//        return expertrepository.findAll();
    }

    public Expert findByName(String name){
        return expertrepository.findByName(name);
    }

    @Override
    public Expert findById(String id) {

        return expertrepository.findById(id).orElse(null);
    }
}
