package com.expertise.demo.dao;

import com.alibaba.excel.EasyExcel;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.util.ExpertListener;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpertDao {

    private ExpertListener expertListener = new ExpertListener();
    final private String localExcelPath = "";

    ExpertDao(){
        EasyExcel.read(this.localExcelPath, Expert.class, this.expertListener).sheet().doRead();
    }

    public List<Expert >findAll(){
//        String fileName = "C:/Users/hzlan/Desktop/1/expert.xlsx";
//        ExpertListener expertListener = new ExpertListener();
//        EasyExcel.read(fileName, Expert.class, expertListener).sheet().doRead();

        return this.expertListener.getExpertList();
    }

    public List<Expert> findById(String id){
        List<Expert> result=new ArrayList<>();
        for(Expert e:this.expertListener.getExpertList()){
            if (e.getId().equals(id)){
                result.add(e);
            }
        }
        return result;
    }

    public Expert insert(Expert expert){
        List<Expert> oldExperts = this.expertListener.getExpertList();
        oldExperts.add(expert);
        EasyExcel.write(this.localExcelPath, Expert.class).sheet().doWrite(oldExperts);
        EasyExcel.read(this.localExcelPath, Expert.class, this.expertListener).sheet().doRead();
        return expert;
    }
}
