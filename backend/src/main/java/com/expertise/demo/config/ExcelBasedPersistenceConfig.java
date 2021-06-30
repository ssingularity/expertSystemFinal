package com.expertise.demo.config;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.dao.ProgramDao;
import com.expertise.demo.dao.RecordDao;
import com.expertise.demo.dao.excel.ExpertDaoExcelImpl;
import com.expertise.demo.dao.excel.ProgramDaoExcelImpl;
import com.expertise.demo.dao.excel.RecordDaoExcelImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.expertise.demo.dao.mapper")
@ConditionalOnProperty(name = "persistence.excel", havingValue = "true")
public class ExcelBasedPersistenceConfig {
    @Bean
    public ExpertDao expertDao(@Value("${dao.expert}") String excelPath) {
        return new ExpertDaoExcelImpl(excelPath);
    }

    @Bean
    public ProgramDao programDao(@Value("${dao.program}") String excelPath) {
        return new ProgramDaoExcelImpl(excelPath);
    }

    @Bean
    public RecordDao recordDao(@Value("${dao.record}") String excelPath) {
        return new RecordDaoExcelImpl(excelPath);
    }
}
