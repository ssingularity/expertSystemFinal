package com.expertise.demo.dao;

import com.alibaba.excel.EasyExcel;
import com.expertise.demo.entity.Program;
import com.expertise.demo.entity.Record;
import com.expertise.demo.util.ProgramListener;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProgramDao {
    private ProgramListener programListener=new ProgramListener();

    final private static String LocalExcelPath="C:/Users/hzlan/Desktop/1/program.xlsx";

    ProgramDao(){
        EasyExcel.read(LocalExcelPath, Program.class,this.programListener).sheet().doRead();
    }

    public List<Program> findAll(){
        return this.programListener.getProgramlist();
    }

    public List<Program> findById(Integer id){
        List<Program> result=new ArrayList<>();
        for(Program p:this.programListener.getProgramlist()){
            if (p.getId().equals(id)){
                result.add(p);
            }
        }
        return result;
    }

    public Program insert(Program p){
        List<Program> old=this.programListener.getProgramlist();
        old.add(p);
        EasyExcel.write(this.LocalExcelPath, Record.class).sheet().doWrite(old);
//        EasyExcel.read(this.LocalExcelPath,Record.class,this.programListener).sheet().doRead();
        return p;
    }
}
