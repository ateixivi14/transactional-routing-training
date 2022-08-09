package com.example.transactionalroutingtraining.configuration;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfiguration {
    private final DataSourceListProperties dataSourceListProperties;
    
    private DataSource readDataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(dataSourceListProperties.getRead().getDriverClassName())
                .username(dataSourceListProperties.getRead().getUsername())
                .password(dataSourceListProperties.getRead().getPassword())
                .url(dataSourceListProperties.getRead().getUrl())
                .build();
    }
    
    private HikariDataSource connectionPoolDataSource(boolean isReadOnly, DataSource dataSource) {
        return new HikariDataSource(hikariConfig(isReadOnly, dataSource));
    }
    
    private HikariConfig hikariConfig(boolean readOnly, DataSource dataSource) {
        HikariConfig hikariConfig =  new HikariConfig();
        hikariConfig.setDataSource(dataSource);
        hikariConfig.setAutoCommit(false);
        hikariConfig.setReadOnly(readOnly);
        return hikariConfig;
    }
    
    private DataSource writeDataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(dataSourceListProperties.getWrite().getDriverClassName())
                .username(dataSourceListProperties.getWrite().getUsername())
                .password(dataSourceListProperties.getWrite().getPassword())
                .url(dataSourceListProperties.getWrite().getUrl())
                .build();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        TransactionalRoutingDataSource transactionalRoutingDataSource = new TransactionalRoutingDataSource();
        targetDataSources.put(DataSourceType.READ, connectionPoolDataSource(true, readDataSource()));
        targetDataSources.put(DataSourceType.WRITE, connectionPoolDataSource(false, writeDataSource()));
        transactionalRoutingDataSource.setDefaultTargetDataSource(connectionPoolDataSource(true, readDataSource()));
        transactionalRoutingDataSource.setTargetDataSources(targetDataSources);
        
        transactionalRoutingDataSource.afterPropertiesSet();
        return transactionalRoutingDataSource;
    }
    

    private Map<String, Object> getHibernateProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        return properties;
    }
    
}
