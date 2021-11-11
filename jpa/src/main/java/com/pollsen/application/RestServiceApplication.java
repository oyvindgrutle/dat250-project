package com.pollsen.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EntityScan("com.pollsen.domain")
@EnableJpaRepositories("com.pollsen.repository")
@ComponentScan(basePackages = {"com.pollsen.*"})
public class RestServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }



    /*
    @Bean
    public CommandLineRunner demo(PollUserRepository repository) {
        return (args) -> {
            // save a few customers
            repository.saveAll(PollUserDAO.getAll());

            // fetch all customers
            log.info("PollUsers found with findAll():");
            log.info("-------------------------------");
            for (PollUser pollUser : repository.findAll()) {
                log.info(pollUser.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            PollUser pollUser = repository.findById(1L).get();
            log.info("PollUser found with findById(1L):");
            log.info("--------------------------------");
            log.info(pollUser.toString());
            log.info("");

            // fetch customers by last name
            log.info("PollUser found with findByUsername('bo'):");
            log.info("--------------------------------------------");
            repository.findByUsername("bo").forEach(bo -> {
                log.info(bo.toString());
            });
            // for (PollUser bo : repository.findByUsername("bo")) {
            //  log.info(bo.toString());
            // }
            log.info("");
        };
    }
    */


}