package com.example.transactionalroutingtraining.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;

@Configuration
public class EntityManagerConfiguration {

    public EntityManagerConfiguration(final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        if (localContainerEntityManagerFactoryBean.getJpaDialect() instanceof HibernateJpaDialect ) {
            ((HibernateJpaDialect) localContainerEntityManagerFactoryBean.getJpaDialect()).setPrepareConnection(false);
        }
    }
}