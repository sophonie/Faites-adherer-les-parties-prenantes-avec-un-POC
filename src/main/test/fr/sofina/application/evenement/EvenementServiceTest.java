package fr.sofina.application.evenement;

import fr.sofina.application.SofinaApplication;
import fr.sofina.application.specialite.Specialite;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootConfiguration
@Configurable
@RunWith(Runner.class)
@SpringBootTest(classes = SofinaApplication.class)
public class EvenementServiceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EvenementService evenementFacade;

    @Test
    @Disabled
    @DisplayName("Vérifier que l'on puisse créer un événement")
    void testCreerEvenement() {

        final Logger logger = LoggerFactory.getLogger(EvenementServiceTest.class);

        final ZoneId zonedId = ZoneId.of("Europe/Paris");
        final LocalDate date = LocalDate.now(zonedId);
        final Specialite code_specialite = new Specialite();
        code_specialite.setCode_specialite(21L);

        final Evenement evenement = new Evenement();
        evenement.setDateEvenement(date);
        evenement.setCodeSpecialite(code_specialite);

        //evenementFacade.creerEvenement(evenement).getCodeEvenement();
        logger.info("Test de l'insertion de l'enregistrement dans la table GESTION_URGENCE.TBEVENEMENT");
        // mettre le nom de la classe de l'entité après la clause from
        assertEquals("Test persist() evenement ",
                evenementFacade.creerEvenement(evenement).getCodeEvenement().intValue(),
                entityManager.createQuery("select e from Evenement e").getResultList().size());
    }
}
