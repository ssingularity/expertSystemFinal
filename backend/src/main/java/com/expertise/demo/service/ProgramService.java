package com.expertise.demo.service;

import com.expertise.demo.common.ExpertException;
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

    public Program insert(Program program) {
        Program res = programdao.insert(program);
        this.autoChoose(res.getId());
        return res;
    }

    public List<Program> findAll() {
        return programdao.findAll();
    }

    public Program findById(String id) {
        return programdao.findById(id);
    }

    //FIXME 逻辑需要稍微改动一下
    public String autoChoose(String id) {
        //是否秘密，排除拉黑，三类匹配
        Program p = programdao.findById(id);
        List<Expert> experts = expertservice.findAll();
        List<Record> alreadyChose = recordservice.findByProgram(id);

        int alreadyA = 0, alreadyT = 0, alreadyM = 0;
        experts.removeIf(e -> e.getIsBlocked().equals("是"));
        for (Record record : alreadyChose) {
            experts.removeIf(e -> e.getId().equals(record.getExpertID()));
            experts.removeIf(e -> !e.getSecret().equals(record.getSecret()));
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
        List<Expert> manageCandidate = new ArrayList<>(), techCandidate = new ArrayList<>(), accCandidate = new ArrayList<>();
        for (Expert e : experts) {
            if (e.getArea().contains(p.getArea())
                && (!p.getCompany().contains(e.getCompany()))) {
                switch (e.getType()) {
                    case "技术":
                        techCandidate.add(e);
                        break;
                    case "管理":
                        manageCandidate.add(e);
                        break;
                    case "财务":
                        accCandidate.add(e);
                        break;
                    default:
                }
            }
        }
        for (int i = alreadyA; i < p.getNumberAcc(); i++) {
            if (accCandidate.size() < p.getNumberAcc() - alreadyA) {
                throw new ExpertException("财务专家过少请手动选择");
            }
            makeRecord(id, p, accCandidate);
        }
        for (int i = alreadyM; i < p.getNumberMng(); i++) {
            if (manageCandidate.size() < p.getNumberMng() - alreadyM) {
                throw new ExpertException("管理专家过少请手动选择");
            }
            makeRecord(id, p, manageCandidate);
        }
        for (int i = alreadyT; i < p.getNumberTech(); i++) {
            if (techCandidate.size() < p.getNumberTech() - alreadyT) {
                throw new ExpertException("技术专家过少请手动选择");
            }
            makeRecord(id, p, techCandidate);
        }
        return "success";
    }

    private void makeRecord(String id, Program p, List<Expert> Candidate) {
        Record record = new Record();
        record.setProgramID(id);
        Random rd = new Random(System.currentTimeMillis());
        Expert e = Candidate.get(rd.nextInt(Candidate.size()));
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
