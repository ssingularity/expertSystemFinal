package com.expertise.demo.dao;

import com.alibaba.excel.EasyExcel;
import com.expertise.demo.entity.Record;
import com.expertise.demo.util.RecordListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecordDao {
    private RecordListener recordListener=new RecordListener();

    @Value("${dao.record}")
    private String localExcelPath;

    @PostConstruct
    public void init() {
        EasyExcel.read(this.localExcelPath, Record.class, this.recordListener).sheet().doRead();
    }


    public List<Record> findAll(){
        return this.recordListener.getRecordList();
    }

    public Record findById(Integer id){
//        List<Record> result=new ArrayList<>();
        for(Record r:this.recordListener.getRecordList()){
            if (r.getId().equals(id)){
//                result.add(r);
                    return r;
            }

        }
        return null;
    }

    public List<Record> findByExpertID(String id){
        List<Record> result=new ArrayList<>();
        for(Record r:this.recordListener.getRecordList()){
            if (r.getExpertID().equals(id)){
                result.add(r);
            }
        }
        return result;
    }

    public List<Record> findByProgramID(String Pid){
        List<Record> result=new ArrayList<>();
        for(Record r:this.recordListener.getRecordList()){
            if (r.getProgramID().equals(Pid)){
                result.add(r);
            }
        }
        return result;
    }

    public void deleteById(Integer id){
        List<Record> old=this.recordListener.getRecordList();
        for(Record r:this.recordListener.getRecordList()){
            if (r.getId().equals(id)){
                old.remove(r);
            }
        }
        EasyExcel.write(this.localExcelPath, Record.class).sheet().doWrite(old);
        EasyExcel.read(this.localExcelPath, Record.class, this.recordListener).sheet().doRead();
//        return
    }

    public Record insert(Record r){
        List<Record> old=this.recordListener.getRecordList();
        old.add(r);
        EasyExcel.write(this.localExcelPath, Record.class).sheet().doWrite(old);
//        EasyExcel.read(this.localExcelPath, Record.class, this.recordListener).sheet().doRead();
        return r;
    }
}
