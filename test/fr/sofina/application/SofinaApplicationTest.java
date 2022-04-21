package fr.sofina.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SofinaApplicationTest {

    @BeforeEach
    void beforeEach() {
        System.out.println("Initialisation des données avant le passage de tous les tests");
    }

    @Test
    public void contextLoads() {

    }

}
