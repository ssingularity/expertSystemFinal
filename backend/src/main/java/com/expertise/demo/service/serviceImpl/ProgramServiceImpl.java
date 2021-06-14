package com.expertise.demo.service.serviceImpl;

import com.expertise.demo.dao.ProgramDao;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.Program;
import com.expertise.demo.entity.Record;
import com.expertise.demo.service.ExpertService;
import com.expertise.demo.service.ProgramService;
import com.expertise.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    private ProgramDao programdao;

    @Autowired
    private RecordService recordservice;

    @Autowired
    private ExpertService expertservice;

    public Program insert(Program program){
        return programdao.insert(program);
    }

//    public Program
    public List<Program> findAll(){
        return programdao.findAll();
    }

    @Override
    public Program findById(String id) {
        return programdao.findById(id);
    }

    @Override
    public String autoChoose(String id) {
        Program p = programdao.findById(id);
        List<Expert> experts=expertservice.findAll();
        List<Record> alreadyChose=recordservice.findByProgram(id);//get the number of experts chosen before

        //是否有财务了？
        boolean hasAccount=false;
        for (Record record:alreadyChose) {
            if (expertservice.findById(record.getExpertID()).getType().equals("财务")){
                hasAccount=true;
            }
            experts.removeIf(e -> e.getId().equals(record.getExpertID()));
        }
//        int neednum=hasAccount?p.getNumber():p.getNumber()-1;//没有财务的少找一个专家
        int neednum=p.getNumberAcc();
        //找合适的专家 insert record
        List<Expert> candidates=new ArrayList<>();
        for (Expert e:experts) {
            if (e.getArea().contains(p.getArea())&&(!e.getCompany().contains(p.getCompany()))){//排除单位，已选择的，匹配专业
                candidates.add(e);
            }
        }
        if (candidates.size()<2){return "专家太少，自动选满缺少专家";}
        for (int i=alreadyChose.size();i<neednum;i++){
            Record record =new Record();
            record.setProgramID(id);
            Random rd=new Random(System.currentTimeMillis());
            String EId=candidates.get(rd.nextInt(candidates.size()-1)).getId();
//            String EId=experts.get(i).getId();
            record.setExpertID(EId);
            record.setEndTime(p.getEndTime());
            record.setStartTime(p.getStartTime());
            recordservice.insert(record);
        }
        if (!hasAccount){
            Record record =new Record();
            record.setProgramID(id);
            List<Expert> accounts=new ArrayList<>();
            for (Expert e:experts) {
                if (e.getType().equals("财务")){
                    accounts.add(e);
                }
            }
            //random account
            if (accounts.size()<2){return "专家太少，自动选满缺少专家";}
            Random rd=new Random(System.currentTimeMillis());
            String EId=accounts.get(rd.nextInt(accounts.size()-1)).getId();
            record.setExpertID(EId);
            record.setEndTime(p.getEndTime());
            record.setStartTime(p.getStartTime());
            recordservice.insert(record);
        }
//        if (recordservice.findByProgram(id).size()<p.getNumber()){
//            //没选满
//            return "专家太少，自动选满缺少专家";
//        }else return "success";
        return "success";
    }


}
