package com.example.transactionalroutingtraining.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.datasources")
public class DataSourceListProperties {
    private final DataSourceProperties write;
    private final DataSourceProperties read;

    @Getter
    @ConstructorBinding
    @RequiredArgsConstructor
    public static class DataSourceProperties {
        private final String url;
        private final String username;
        private final String password;
        private final String driverClassName;
    }
}