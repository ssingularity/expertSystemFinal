package com.expertise.demo.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.expertise.demo.entity.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordListener extends AnalysisEventListener<Record> {

    private List<Record> recordList=new ArrayList<>();

    public List<Record> getRecordList(){
        return this.recordList;
    }

    public RecordListener(){
        super();
        this.recordList.clear();
    }

    @Override
    public void invoke(Record record, AnalysisContext analysisContext) {
        this.recordList.add(record);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
