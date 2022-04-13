package fr.sofina.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("fr.sofina")
@EnableJpaRepositories("fr.sofina")
@SpringBootApplication(scanBasePackages = {"fr.sofina"})
public class SofinaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SofinaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
    }
}
