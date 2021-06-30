package com.expertise.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.expertise.demo.dao.mapper")
@ConditionalOnProperty(name = "persistence.excel", havingValue = "false")
public class H2BasesPersistenceConfig {
}
