package com.example.climbing.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class HikariDatabaseConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(HikariDataSource.class);
    private static DataSource dataSource;

    static {
        initializeDatabase();
    }

    private static void initializeDatabase() {
        String dbUrl = "jdbc:postgresql://localhost:5432/";
        String dbName = "climbing";
        String dbUsername = "postgres";
        String dbPassword = "2108135592Ar";

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl + dbName);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        config.setMaximumPoolSize(5);
        // Set the driver class name explicitly for PostgreSQL
        config.setDriverClassName("org.postgresql.Driver");

        dataSource = new HikariDataSource(config);
//        dataSource = ProxyDataSourceBuilder
//                .create(dataSource)
//                .name("MyDataSource")
//                .logQueryBySlf4j()
//                .build();

        LOGGER.info("HikariCP initialized");
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
