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
    public String autoChoose(String id) {//是否秘密，排除拉黑，三类匹配
        Program p = programdao.findById(id);
        List<Expert> experts=expertservice.findAll();
        List<Record> alreadyChose=recordservice.findByProgram(id);//get the number of experts chosen before

        int alreadyA=0,alreadyT=0,alreadyM=0;
        for (Record record:alreadyChose) {
            experts.removeIf(e -> e.getId().equals(record.getExpertID()));
            experts.removeIf(e -> !e.getSecret().equals(record.getSecret()));
            experts.removeIf(Expert::getIsBlocked);
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
        if (accCandidate.size()<2||manageCandidate.size()<2||techCandidate.size()<2){return "专家太少，自动选满缺少专家";}
        for (int i=alreadyA;i<p.getNumberAcc();i++){
            makeRecord(id, p, accCandidate);
        }
        for (int i=alreadyM;i<p.getNumberMng();i++){
            makeRecord(id, p, manageCandidate);
        }
        for (int i=alreadyT;i<p.getNumberTech();i++){
            makeRecord(id, p, techCandidate);
        }

//        //是否有财务了？
//        boolean hasAccount=false;
//        for (Record record:alreadyChose) {
//            if (expertservice.findById(record.getExpertID()).getType().equals("财务")){
//                hasAccount=true;
//            }
//            experts.removeIf(e -> e.getId().equals(record.getExpertID()));
//        }
////        int neednum=hasAccount?p.getNumber():p.getNumber()-1;//没有财务的少找一个专家
//        int neednum=p.getNumberAcc();
//        //找合适的专家 insert record
//        List<Expert> candidates=new ArrayList<>();
//        for (Expert e:experts) {
//            if (e.getArea().contains(p.getArea())&&(!e.getCompany().contains(p.getCompany()))){//排除单位，已选择的，匹配专业
//                candidates.add(e);
//            }
//        }
//        if (candidates.size()<2){return "专家太少，自动选满缺少专家";}
//        for (int i=alreadyChose.size();i<neednum;i++){
//            Record record =new Record();
//            record.setProgramID(id);
//            Random rd=new Random(System.currentTimeMillis());
//            String EId=candidates.get(rd.nextInt(candidates.size()-1)).getId();
////            String EId=experts.get(i).getId();
//            record.setExpertID(EId);
//            record.setEndTime(p.getEndTime());
//            record.setStartTime(p.getStartTime());
//            recordservice.insert(record);
//        }
//        if (!hasAccount){
//            Record record =new Record();
//            record.setProgramID(id);
//            List<Expert> accounts=new ArrayList<>();
//            for (Expert e:experts) {
//                if (e.getType().equals("财务")){
//                    accounts.add(e);
//                }
//            }
//            //random account
//            if (accounts.size()<2){return "专家太少，自动选满缺少专家";}
//            Random rd=new Random(System.currentTimeMillis());
//            String EId=accounts.get(rd.nextInt(accounts.size()-1)).getId();
//            record.setExpertID(EId);
//            record.setEndTime(p.getEndTime());
//            record.setStartTime(p.getStartTime());
//            recordservice.insert(record);
//        }
//        if (recordservice.findByProgram(id).size()<p.getNumber()){
//            //没选满
//            return "专家太少，自动选满缺少专家";
//        }else return "success";
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

    @Override
    public void changeState(String id) {
        programdao.changeState(id);
    }


}
