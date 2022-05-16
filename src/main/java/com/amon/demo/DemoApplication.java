package com.amon.demo;

import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(DemoApplication.class);
        //declare the properties including the caching property for thymeleaf
        Properties properties = new Properties();
        properties.setProperty("spring.cache.type", "NONE");
        application.setDefaultProperties(properties);
        application.run(args);
    }

}
