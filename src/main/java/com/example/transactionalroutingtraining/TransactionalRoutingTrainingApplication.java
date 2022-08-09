package com.example.transactionalroutingtraining;

import com.example.transactionalroutingtraining.configuration.DataSourceListProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties(DataSourceListProperties.class)
@EnableTransactionManagement
public class TransactionalRoutingTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalRoutingTrainingApplication.class, args);
    }

}
