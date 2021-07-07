package com.expertise.demo.service;

import com.expertise.demo.common.ExpertException;
import com.expertise.demo.dao.ProgramDao;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.Program;
import com.expertise.demo.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    public List<Program> findByPageable(int offset) {
        return programdao.findByPageable(offset);
    }

    public Integer size() {
        return programdao.size();
    }

    public List<Program> findAll() {
        return programdao.findAll();
    }

    public Program findById(String id) {
        return programdao.findById(id);
    }

    public void autoChoose(String id) {
        Program p = programdao.findById(id);
        Map<String, List<Expert>> experts = expertservice.findAll()
            .stream()
            .filter(e -> match(e, p))
            .collect(Collectors.groupingBy(Expert::getType));
        List<Record> alreadyChose = recordservice.findByProgram(id);
        RandomChooseHandler handler = new RandomChooseHandler(p, alreadyChose, experts);
        List<Expert> choseExperts = handler.doChoose();
        makeRecords(p, choseExperts);
        if (handler.isExpertNotEnough()) {
            handler.generateExpertNotEnoughException();
        }
    }

    private boolean match(Expert expert, Program program) {
        // TODO: 密级匹配
        return expert.getIsBlocked().equals("否")
            && !program.getCompany().contains(expert.getCompany())
            && ("财务".equals(expert.getType()) || expert.getArea().contains(program.getArea()));
    }

    private void makeRecords(Program p, List<Expert> expertList) {
        for (Expert expert: expertList) {
            makeRecord(p, expert);
        }
    }

    private void makeRecord(Program p, Expert e) {
        Record record = new Record();
        record.setExpertID(e.getId());
        record.setExpertName(e.getName());
        record.setType(e.getType());
        record.setExpertArea(e.getArea());
        record.setPhone(e.getPhone());
        record.setCompany(e.getCompany());
        record.setSecret(e.getSecret());
        record.setProgramID(p.getId());
        record.setSecretLevel(p.getSecretLevel());
        record.setEndTime(p.getEndTime());
        record.setStartTime(p.getStartTime());
        recordservice.insert(record);
    }

    private static class RandomChooseHandler {
        final private Map<String, List<Expert>> experts;

        private Map<String, Integer> type2AlreadyChoseExpertNumberMap = new HashMap<>();

        private Map<String, Integer> type2targetChoseExpertNumberMap = new HashMap<>();

        private List<String> notEnoughExpertTypeList = new ArrayList<>();

        public RandomChooseHandler(Program program,
                                   List<Record> alreadyChose,
                                   Map<String, List<Expert>> experts) {
            this.experts = experts;
            for (Record record : alreadyChose) {
                this.experts.get(record.getType())
                    .removeIf(e -> e.getId().equals(record.getExpertID()));
                this.type2AlreadyChoseExpertNumberMap.putIfAbsent(record.getType(), 0);
                this.type2AlreadyChoseExpertNumberMap.compute(record.getType(), (k, v) -> v + 1);
            }
            this.type2targetChoseExpertNumberMap.put("技术", program.getNumberTech());
            this.type2targetChoseExpertNumberMap.put("管理", program.getNumberMng());
            this.type2targetChoseExpertNumberMap.put("财务", program.getNumberAcc());

        }

        public List<Expert> doChoose() {
            String[] types = new String[] {"技术", "管理", "财务"};
            List<Expert> res = new ArrayList<>();
            for (String type: types) {
                res.addAll(this.doChoose(experts.getOrDefault(type, new ArrayList<>()),
                    type2AlreadyChoseExpertNumberMap.getOrDefault(type, 0),
                    type2targetChoseExpertNumberMap.get(type),
                    type));
            }
            return res;
        }

        private List<Expert> doChoose(List<Expert> experts, int alreadyNumber, int targetNumber, String type) {
            List<Expert> res = new ArrayList<>();
            if (experts.size() < targetNumber - alreadyNumber) {
                this.notEnoughExpertTypeList.add(type);
                res.addAll(experts);
                return res;
            }
            else if (experts.size() == targetNumber - alreadyNumber) {
                res.addAll(experts);
                return res;
            }
            else {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                for (int i=0; i<targetNumber - alreadyNumber; ++i) {
                    int index = random.nextInt(experts.size());
                    res.add(experts.get(index));
                    experts.remove(index);
                }
            }
            return res;
        }

        public boolean isExpertNotEnough() {
            return !notEnoughExpertTypeList.isEmpty();
        }

        public void generateExpertNotEnoughException() {
            if (isExpertNotEnough()) {
                String notEnoughTypes = String.join(",", this.notEnoughExpertTypeList);
                throw new ExpertException(notEnoughTypes + "专家数量不够");
            }
        }
    }

    public void changeState(String id) {
        programdao.changeState(id);
    }
}
