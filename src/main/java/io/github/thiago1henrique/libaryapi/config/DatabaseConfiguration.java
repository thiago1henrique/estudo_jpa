package io.github.thiago1henrique.libaryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String Password;
    @Value("${spring.datasource.driver-class-name}")
    String drive;

    //@Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(Password);
        ds.setDriverClassName(drive);
        return ds;
    }

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(Password);
        config.setDriverClassName(drive);

        config.setMaximumPoolSize(10); //maximo de conexões liberadas
        config.setMinimumIdle(1); //minimo liberado de inicio
        config.setPoolName("Libary-db-pool"); //nome do pool
        config.setMaxLifetime(600000); // 600 mil ms (10 minutos)
        config.setConnectionTimeout(100000); // timeout para conseguir uma conexão

        config.setConnectionTestQuery("select 1"); //testa conexão com banco

        return new HikariDataSource(config);
    }
}
