package com.expertise.demo.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.expertise.demo.entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramListener extends AnalysisEventListener<Program> {

    private List<Program> programlist = new ArrayList<>();

    public List<Program> getProgramlist(){return this.programlist;}

    public ProgramListener(){
        super();
        this.programlist.clear();
    }

    @Override
    public void invoke(Program program, AnalysisContext analysisContext) {
        this.programlist.add(program);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
