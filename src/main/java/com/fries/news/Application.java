package com.fries.news;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }

        };
    }

    //TODO: can adjust application.properties on startup. Can set database userid and password here with spring args when
    //I need create/delete permissions
//    public class DatabasePropertiesListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
//        public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
//            ConfigurableEnvironment environment = event.getEnvironment();
//            Properties props = new Properties();
//            props.put("spring.datasource.url", "<my value>");
//            environment.getPropertySources().addFirst(new PropertiesPropertySource("myProps", props));
//            System.out.println("HELLO");
//        }
//    }
}
