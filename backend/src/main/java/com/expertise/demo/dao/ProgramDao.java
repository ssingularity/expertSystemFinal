package com.expertise.demo.dao;

import com.alibaba.excel.EasyExcel;
import com.expertise.demo.entity.Program;
import com.expertise.demo.util.ProgramListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Repository
public class ProgramDao {
    private ProgramListener programListener=new ProgramListener();

    @Value("${dao.program}")
    private String localExcelPath;

    @PostConstruct
    public void init() {
        EasyExcel.read(this.localExcelPath, Program.class, this.programListener).sheet().doRead();
    }

    public List<Program> findAll(){
        return this.programListener.getProgramlist();
    }

    public Program findById(String id){
//        Program result=new ArrayList<>();
        for(Program p:this.programListener.getProgramlist()){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public Program insert(Program p){
        p.setId(UUID.randomUUID().toString());
        List<Program> old=this.programListener.getProgramlist();
        old.add(p);
        EasyExcel.write(this.localExcelPath, Program.class).sheet().doWrite(old);
        return p;
    }

    public void changeState(String id){
        for(Program p:this.programListener.getProgramlist()){
            if (p.getId().equals(id)){
                p.setState(1);
            }
        }
    }
}
