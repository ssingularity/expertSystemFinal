package com.expertise.demo.service.serviceImpl;

import com.expertise.demo.entity.Record;
import com.expertise.demo.repository.RecordRepository;
import com.expertise.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public Record insert(Record record){
        //避免同一专家：先查找此项目是否有此专家
        Integer pid=record.getProgramID();
        List<Record> list=recordRepository.findByExpertID(record.getExpertID());
        for(Record r:list){
            if(r.getProgramID().equals(pid)){
                return r;
            }
        }
        return recordRepository.save(record);
    }

    @Override
    public Record update(Record record) {

        return recordRepository.save(record);
    }

    @Override
    public Record findById(Integer id) {
        return recordRepository.findById(id);
    }

    public List<Record> findByExpert(String eid){
        return recordRepository.findByExpertID(eid);
    }
    public List<Record> findByProgram(Integer pid){
        return recordRepository.findByProgramID(pid);
    }

    public String delete(Integer id){
         recordRepository.deleteById(id);
         return "success";
    }

}
