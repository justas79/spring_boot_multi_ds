package com.justbat.testmultipledb.config;

import com.justbat.testmultipledb.service.LuceneIndexServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class StartupLuceneConfiguration {
    @Bean
    public LuceneIndexServiceBean luceneIndexServiceBean(EntityManagerFactory em) {
        LuceneIndexServiceBean luceneIndexServiceBean = new LuceneIndexServiceBean(em);
        luceneIndexServiceBean.triggerAll();
        return luceneIndexServiceBean;
    }
}
