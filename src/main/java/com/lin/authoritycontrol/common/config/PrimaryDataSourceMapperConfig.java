package com.lin.authoritycontrol.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 主配置依赖
 *
 * @author 林维家
 * @since 2024/12/25 下午8:55
 */
@Configuration
@MapperScan(basePackages = "com.lin.authoritycontrol.mapper.primary", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataSourceMapperConfig {
}
