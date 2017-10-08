package org.dominwos.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Wosin on 08.10.2017.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.dominwos")
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
