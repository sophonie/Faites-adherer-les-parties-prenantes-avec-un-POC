package fr.sofina.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SofinaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SofinaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
