package com.expertise.demo.config;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.dao.ProgramDao;
import com.expertise.demo.dao.RecordDao;
import com.expertise.demo.dao.adapter.ExpertDaoMapperAdapter;
import com.expertise.demo.dao.adapter.ProgramDaoMapperAdapter;
import com.expertise.demo.dao.adapter.RecordDaoMapperAdapter;
import com.expertise.demo.dao.mapper.ExpertMapper;
import com.expertise.demo.dao.mapper.ProgramMapper;
import com.expertise.demo.dao.mapper.RecordMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "persistence.excel", havingValue = "false")
@MapperScan("com.expertise.demo.dao.mapper")
public class H2BasesPersistenceConfig {
    @Bean
    public ExpertDao expertDao(ExpertMapper expertMapper) {
        return new ExpertDaoMapperAdapter(expertMapper);
    }

    @Bean
    public RecordDao recordDao(RecordMapper recordMapper) {
        return new RecordDaoMapperAdapter(recordMapper);
    }

    @Bean
    public ProgramDao programDao(ProgramMapper programMapper) {
        return new ProgramDaoMapperAdapter(programMapper);
    }
}
