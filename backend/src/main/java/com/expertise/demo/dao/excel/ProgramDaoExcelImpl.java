package com.expertise.demo.dao.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.expertise.demo.dao.ProgramDao;
import com.expertise.demo.entity.Program;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProgramDaoExcelImpl implements ProgramDao {
    final private ProgramListener programListener = new ProgramListener();

    private String localExcelPath;

    public ProgramDaoExcelImpl(String localExcelPath) {
        this.localExcelPath = localExcelPath;
    }

    @PostConstruct
    public void init() {
        EasyExcel.read(this.localExcelPath, Program.class, this.programListener).sheet().doRead();
    }

    @Override
    public List<Program> findAll() {
        return this.programListener.getProgramlist();
    }

    @Override
    public List<Program> findByPageable(int offset) {
        List<Program> res = findAll();
        return res.subList(offset * 20, Math.min((offset + 1) * 20, res.size()));
    }

    @Override
    public Integer size() {
        return findAll().size();
    }

    @Override
    public Program findById(String id) {
        for (Program p : this.programListener.getProgramlist()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Program insert(Program p) {
        p.setId(UUID.randomUUID().toString());
        List<Program> old = this.programListener.getProgramlist();
        old.add(p);
        EasyExcel.write(this.localExcelPath, Program.class).sheet().doWrite(old);
        return p;
    }

    @Override
    public void changeState(String id) {
        List<Program> old = this.programListener.getProgramlist();
        for (Program p : old) {
            if (p.getId().equals(id)) {
                p.setState(1);
                break;
            }
        }
        EasyExcel.write(this.localExcelPath, Program.class).sheet().doWrite(old);
    }

    public static class ProgramListener extends AnalysisEventListener<Program> {

        private List<Program> programlist = new ArrayList<>();

        public ProgramListener() {
            super();
            this.programlist.clear();
        }

        public List<Program> getProgramlist() {
            return this.programlist;
        }

        @Override
        public void invoke(Program program, AnalysisContext analysisContext) {
            this.programlist.add(program);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        }
    }
}
