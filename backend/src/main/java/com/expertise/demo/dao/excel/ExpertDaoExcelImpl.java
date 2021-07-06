package com.expertise.demo.dao.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.entity.Expert;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ExpertDaoExcelImpl implements ExpertDao {

    final private ExpertListener expertListener = new ExpertListener();

    private String localExcelPath;

    public ExpertDaoExcelImpl(String localExcelPath) {
        this.localExcelPath = localExcelPath;
    }

    @PostConstruct
    public void init() {
        EasyExcel.read(this.localExcelPath, Expert.class, this.expertListener).sheet().doRead();
    }

    @Override
    public List<Expert> findAll() {
        return this.expertListener.getExpertList();
    }

    @Override
    public Integer size() {
        return findAll().size();
    }

    @Override
    public List<Expert> findByPageable(int offset) {
        List<Expert> res = findAll();
        return res.subList(offset * 20, Math.min((offset + 1) * 20, res.size()));
    }

    @Override
    public Expert findById(String id) {
        for (Expert e : this.expertListener.getExpertList()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Expert> findByBlocked(String blocked) {
        List<Expert> result = new ArrayList<>();
        for (Expert e : this.expertListener.getExpertList()) {
            if (e.getIsBlocked().equals(blocked)) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public Expert insert(Expert expert) {
        List<Expert> oldExperts = this.expertListener.getExpertList();
        oldExperts.add(expert);
        EasyExcel.write(this.localExcelPath, Expert.class).sheet().doWrite(oldExperts);
        return expert;
    }

    @Override
    public void deleteById(String id) {
        List<Expert> old = this.expertListener.getExpertList();
        old.removeIf(e -> e.getId().equals(id));
        EasyExcel.write(this.localExcelPath, Expert.class).sheet().doWrite(old);
    }

    public static class ExpertListener extends AnalysisEventListener<Expert> {

        private List<Expert> expertList = new ArrayList<Expert>();

        public ExpertListener() {
            super();
            this.expertList.clear();
        }

        public List<Expert> getExpertList() {
            return expertList;
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
}
