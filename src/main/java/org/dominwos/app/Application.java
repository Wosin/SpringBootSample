package org.dominwos.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Wosin on 08.10.2017.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.dominwos")
@EnableJpaRepositories("org.dominwos.repository")
@EntityScan("org.dominwos.model")
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
