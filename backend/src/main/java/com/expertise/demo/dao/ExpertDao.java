package com.expertise.demo.dao;

import com.alibaba.excel.EasyExcel;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.util.ExpertListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpertDao {

    private ExpertListener expertListener = new ExpertListener();

    @Value("${dao.expert}")
    private String localExcelPath;

//    final private String LocalExcelPath="C:/Users/hzlan/Desktop/1/expert.xlsx";

    @PostConstruct
    public void init() {
        EasyExcel.read(this.localExcelPath, Expert.class, this.expertListener).sheet().doRead();
    }

    public List<Expert >findAll(){
//        String fileName = "C:/Users/hzlan/Desktop/1/expert.xlsx";
//        ExpertListener expertListener = new ExpertListener();
//        EasyExcel.read(fileName, Expert.class, expertListener).sheet().doRead();

        return this.expertListener.getExpertList();
    }

    public Expert findById(String id){
        for(Expert e:this.expertListener.getExpertList()){
            if (e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }

    public List<Expert> findByName(String name){
        List<Expert> result=new ArrayList<>();
        for(Expert e:this.expertListener.getExpertList()){
            if (e.getName().equals(name)){
                result.add(e);
            }
        }
        return result;
    }

    public Expert insert(Expert expert){
        List<Expert> oldExperts = this.expertListener.getExpertList();
        oldExperts.add(expert);
        EasyExcel.write(this.localExcelPath, Expert.class).sheet().doWrite(oldExperts);
//        EasyExcel.read(this.localExcelPath, Expert.class, this.expertListener).sheet().doRead();
        return expert;
    }

    public void deleteById(Integer id){
        List<Expert> old=this.expertListener.getExpertList();
        for(Expert e:this.expertListener.getExpertList()){
            if (e.getId().equals(id)){
                old.remove(e);
            }
        }
        EasyExcel.write(this.localExcelPath, Expert.class).sheet().doWrite(old);
//        EasyExcel.read(this.localExcelPath, Record.class, this.recordListener).sheet().doRead();
//        return
    }
}
