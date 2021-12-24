package ru.belov.webapplication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("ru.belov.webapplication")
@EnableWebMvc
@PropertySource("classpath:datasource.properties")
@PropertySource("classpath:action_type.properties")
public class SpringConfig implements WebMvcConfigurer {

    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${name}")
    private String userName;

    @Value("${password}")
    private String password;

    @Value("${CheckMail}")
    private int checkMail;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
