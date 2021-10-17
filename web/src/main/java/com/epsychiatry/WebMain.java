package com.epsychiatry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.epsychiatry"})
@EnableJpaRepositories(basePackages = {"com.epsychiatry"})
@EntityScan(basePackages = {"com.epsychiatry"})
//@ComponentScan(basePackages = {"com.epsychiatry"})
public class WebMain extends  SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }
}
