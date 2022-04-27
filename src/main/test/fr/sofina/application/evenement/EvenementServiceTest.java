package fr.sofina.application.evenement;

import fr.sofina.application.SofinaApplication;
import fr.sofina.application.specialite.Specialite;
import java.time.LocalDate;
import java.time.ZoneId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootConfiguration
@Configurable
@RunWith(Runner.class)
@SpringBootTest(classes = SofinaApplication.class)
public class EvenementRepositoryTest {

    @Autowired
    @Qualifier("evenementRepository")
    private EvenementRepository evenementRepository;

    @Test
    @DisplayName("Vérifier que l'on puisse créer un événement")
    void testCreerEvenement() {

        final Logger logger = LoggerFactory.getLogger(EvenementRepositoryTest.class);

        final ZoneId zonedId = ZoneId.of("Europe/Paris");
        final LocalDate date = LocalDate.now(zonedId);
        final Specialite code_specialite = new Specialite();
        code_specialite.setCodespecialite(21L);

        final Evenement expectedEvt = evenementRepository.creerEvenement(date, code_specialite.getCodespecialite()); // expected

        final Evenement actualEvt = new Evenement(); // actual  
        actualEvt.setDate_evenement(date);
        actualEvt.setCode_Specialite(code_specialite);
        logger.info("Création de l'événement " + actualEvt.getCode_evenement());
        Assertions.assertEquals(expectedEvt, actualEvt);
    }
}
