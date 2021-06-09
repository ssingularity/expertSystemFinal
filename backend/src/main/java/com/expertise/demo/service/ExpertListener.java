package com.expertise.demo.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.expertise.demo.entity.Expert;
import java.util.ArrayList;
import java.util.List;


public class ExpertListener extends AnalysisEventListener<Expert> {

    private List<Expert> expertList = new ArrayList<Expert>();

    public List<Expert> getExpertList() {
        return expertList;
    }

    public ExpertListener() {
        super();
        this.expertList.clear();
    }

    @Override
    public void invoke(Expert expert, AnalysisContext analysisContext) {
        this.expertList.add(expert);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        this.expertList.forEach(System.out::println);
    }
}
