package com.example.demo.nfcReader;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBManager {
    // TYRING TO IMPLEMENT:'https:// www.youtube.com/watch?v=sJwLxKu_lHc' FOR
    // MULTIPLE DB

    @Bean("eventStore")
    @Primary
    @ConfigurationProperties(prefix = "spring.events-datasource")
    public DataSource primaryDS() {
        return DataSourceBuilder.create().build();
    }

    @Bean("bankCards")
    @ConfigurationProperties(prefix = "spring.bankcard-datasource")
    public DataSource secondaryDS() {
        return DataSourceBuilder.create().build();
    }
}
