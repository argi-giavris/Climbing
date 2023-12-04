package com.example.climbing.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
//import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HikariDatabaseConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(HikariDataSource.class);
    private static HikariDataSource dataSource;
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/climbing");
        config.setUsername("postgres");
        config.setPassword("2108135592Ar");

        dataSource = new HikariDataSource(config);
//        dataSource = ProxyDataSourceBuilder
//                .create(dataSource)
//                .name("MyDataSource")
//                .logQueryBySlf4j()
//                .build();
    }

    public  static HikariDataSource getDataSource(){
        return dataSource;
    }
}
