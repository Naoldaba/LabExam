package com.site.enterprise.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// NAOL DABA MULLETA (UGR/4777/14)

@SpringBootApplication
@ServletComponentScan("com.site.enterprise")
@Configuration
@ComponentScan("com.site.enterprise")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}