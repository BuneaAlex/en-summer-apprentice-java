package org.restservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories("org.persistence") // Specify the package(s) where your JPA repositories are located
@ComponentScan({"org.persistence", "org.model","org.business"})
@EntityScan("org.model")
@ComponentScan(basePackageClasses = AppController.class)
public class StartRestServices {
    public static void main(String[] args) {
        SpringApplication.run(StartRestServices.class, args);
    }
}



