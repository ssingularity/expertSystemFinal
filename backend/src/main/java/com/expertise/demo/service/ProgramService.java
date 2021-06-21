package com.expertise.demo.service;

import com.expertise.demo.dao.ProgramDao;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.Program;
import com.expertise.demo.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProgramService {
    @Autowired
    private ProgramDao programdao;

    @Autowired
    private RecordService recordservice;

    @Autowired
    private ExpertService expertservice;

    public Program insert(Program program){
        return programdao.insert(program);
    }

    public List<Program> findAll(){
        return programdao.findAll();
    }

    public Program findById(String id) {
        return programdao.findById(id);
    }

    public String autoChoose(String id) {//是否秘密，排除拉黑，三类匹配
        Program p = programdao.findById(id);
        List<Expert> experts=expertservice.findAll();
        List<Record> alreadyChose=recordservice.findByProgram(id);//get the number of experts chosen before

        int alreadyA=0,alreadyT=0,alreadyM=0;
        for (Record record:alreadyChose) {
            experts.removeIf(e -> e.getId().equals(record.getExpertID()));
            experts.removeIf(e -> !e.getSecret().equals(record.getSecret()));
            experts.removeIf(e -> e.getIsBlocked().equals("是"));
            switch (record.getType()) {
                case "技术":
                    alreadyT++;
                    break;
                case "管理":
                    alreadyM++;
                    break;
                case "财务":
                    alreadyA++;
                    break;
            }
        }
        List<Expert> manageCandidate=new ArrayList<>(),techCandidate=new ArrayList<>(),accCandidate=new ArrayList<>();
        for (Expert e:experts) {
            if (e.getArea().contains(p.getArea())&&(!p.getCompany().contains(e.getCompany()))){//排除单位，已选择的，匹配专业
                switch (e.getType()) {
                    case "技术":
                        techCandidate.add(e);
                        break;
                    case "管理":
                        manageCandidate.add(e);
                        break;
                }
            }else if (e.getType().equals("财务")&&(!e.getCompany().contains(p.getCompany()))){
                accCandidate.add(e);
            }
        }
//        if (accCandidate.size()<2||manageCandidate.size()<2||techCandidate.size()<2){return "专家太少，自动选满缺少专家";}
        for (int i=alreadyA;i<p.getNumberAcc();i++){
            if (accCandidate.size()<2) return "专家太少，自动选满缺少专家";
            makeRecord(id, p, accCandidate);
        }
        for (int i=alreadyM;i<p.getNumberMng();i++){
            if (manageCandidate.size()<2) return "专家太少，自动选满缺少专家";
            makeRecord(id, p, manageCandidate);
        }
        for (int i=alreadyT;i<p.getNumberTech();i++){
            if (techCandidate.size()<2) return "专家太少，自动选满缺少专家";
            makeRecord(id, p, techCandidate);
        }
        return "success";
    }

    private void makeRecord(String id, Program p, List<Expert> Candidate) {
        Record record =new Record();
        record.setProgramID(id);
        Random rd=new Random(System.currentTimeMillis());
        Expert e=Candidate.get(rd.nextInt(Candidate.size()-1));
        record.setExpertID(e.getId());
        record.setExpertName(e.getName());
        record.setType(e.getType());
        record.setExpertArea(e.getArea());
        record.setPhone(e.getPhone());
        record.setCompany(e.getCompany());
        record.setSecret(e.getSecret());
        record.setSecretLevel(p.getSecretLevel());
        record.setEndTime(p.getEndTime());
        record.setStartTime(p.getStartTime());
        recordservice.insert(record);
        System.out.println(e.getName());
    }

    public void changeState(String id) {
        programdao.changeState(id);
    }
}
