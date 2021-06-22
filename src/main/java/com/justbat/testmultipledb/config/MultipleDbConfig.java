package com.justbat.testmultipledb.config;

import com.justbat.testmultipledb.repository.SimpleRepository;
import com.justbat.testmultipledb.service.SimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class MultipleDbConfig {

    @Bean("postgreDb")
    @ConfigurationProperties(prefix = "spring.datasource.postgre")
    public DataSource postgreDatasource() {
        DataSource datasource = DataSourceBuilder.create().build();
        return datasource;
    }

    @Bean("jdbcTemplatePostgre")
    public JdbcTemplate jdbcTemplatePostgre(@Qualifier("postgreDb") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    @Bean("mysqlDb")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDatasource() {
        DataSource datasource = DataSourceBuilder.create().build();
        return datasource;
    }

    @Primary
    @Bean("jdbcTemplateMysql")
    public JdbcTemplate jdbcTemplateMysql(@Qualifier("mysqlDb") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    @Qualifier("simpleRepoMysql")
    @Bean
    public SimpleRepository simpleRepositoryMysql(@Qualifier("jdbcTemplateMysql") JdbcTemplate jdbcTemplate) {
        return new SimpleRepository(jdbcTemplate);
    }

    @Qualifier("simpleRepoPostgre")
    @Bean
    public SimpleRepository simpleRepositoryPostgre(@Qualifier("jdbcTemplatePostgre") JdbcTemplate jdbcTemplate) {
        return new SimpleRepository(jdbcTemplate);
    }

    @Qualifier("simpleServiceMysql")
    @Bean
    public SimpleService simpleServiceMysql(@Qualifier("simpleRepoMysql") SimpleRepository simpleRepository) {
        return new SimpleService(simpleRepository);
    }

    @Qualifier("simpleServicePostgre")
    @Bean
    public SimpleService simpleServicePostgre(@Qualifier("simpleRepoPostgre") SimpleRepository simpleRepository) {
        return new SimpleService(simpleRepository);
    }

}
