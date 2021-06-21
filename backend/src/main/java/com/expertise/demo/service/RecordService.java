package com.expertise.demo.service;

import com.expertise.demo.dao.RecordDao;
import com.expertise.demo.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordDao recorddao;

    public Record insert(Record record){
        //避免同一专家：先查找此项目是否有此专家
        String pid = record.getProgramID();
        List<Record> list=recorddao.findByExpertID(record.getExpertID());
        for(Record r:list){
            if(r.getProgramID().equals(pid)){
                return r;
            }
        }
        return recorddao.insert(record);
    }

    public Record update(Record record) {
        recorddao.deleteById(record.getId());
        return recorddao.insert(record);
    }

    public Record findById(Integer id) {
        return recorddao.findById(id);
    }

    public List<Record> findByExpert(String eid){
        return recorddao.findByExpertID(eid);
    }

    public List<Record> findByProgram(String pid){
        return recorddao.findByProgramID(pid);
    }

    public String delete(String id){
        recorddao.deleteById(id);
         return "success";
    }
}
