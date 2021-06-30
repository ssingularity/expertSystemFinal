package com.expertise.demo.dao.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.expertise.demo.dao.RecordDao;
import com.expertise.demo.entity.Record;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecordDaoExcelImpl implements RecordDao {
    final private RecordListener recordListener = new RecordListener();

    private String localExcelPath;

    public RecordDaoExcelImpl(String localExcelPath) {
        this.localExcelPath = localExcelPath;
    }

    @PostConstruct
    public void init() {
        EasyExcel.read(this.localExcelPath, Record.class, this.recordListener).sheet().doRead();
    }

    @Override
    public List<Record> findByExpertID(String id) {
        List<Record> result = new ArrayList<>();
        for (Record r : this.recordListener.getRecordList()) {
            if (r.getExpertID().equals(id)) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public List<Record> findByProgramID(String id) {
        List<Record> result = new ArrayList<>();
        for (Record r : this.recordListener.getRecordList()) {
            if (r.getProgramID().equals(id)) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public void deleteById(String id) {
        List<Record> old = this.recordListener.getRecordList();
        old.removeIf(r -> r.getId().equals(id));
        EasyExcel.write(this.localExcelPath, Record.class).sheet().doWrite(old);
    }

    @Override
    public Record insert(Record r) {
        r.setId(UUID.randomUUID().toString());
        List<Record> old = this.recordListener.getRecordList();
        old.add(r);
        EasyExcel.write(this.localExcelPath, Record.class).sheet().doWrite(old);
        return r;
    }

    public static class RecordListener extends AnalysisEventListener<Record> {

        private List<Record> recordList = new ArrayList<>();

        public RecordListener() {
            super();
            this.recordList.clear();
        }

        public List<Record> getRecordList() {
            return this.recordList;
        }

        @Override
        public void invoke(Record record, AnalysisContext analysisContext) {
            this.recordList.add(record);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        }
    }
}
