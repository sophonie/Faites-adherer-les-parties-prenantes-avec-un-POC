package fr.sofina.application.specialite;

import fr.sofina.application.SofinaApplication;
import fr.sofina.application.groupespecialite.GroupeSpecialite;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class SpecialiteRepositoryTest { // test intégration
    
    private final Logger logger = LoggerFactory.getLogger(SpecialiteRepositoryTest.class);
    
    @Autowired
    @Qualifier("specialiteRepository")
    private SpecialiteRepository specialiteRepository;

    @Test
    @DisplayName("Trouver une spécialité par son code")
    void testFindOneSpecialiteById() {
        final Long code = 24L;        
        final GroupeSpecialite code_groupe_specialite = new GroupeSpecialite();
        code_groupe_specialite.setCodeGroupeSpecialite(5L);

        final Specialite actual = new Specialite();
        actual.setCodeSpecialite(code);
        actual.setNom("Pharmacologie clinique et thérapeutique");        

        final Specialite expected = specialiteRepository.findOneSpecialiteById(actual.getCodeSpecialite());

        assertThat(expected.getCodeSpecialite()).isEqualTo(actual.getCodeSpecialite()); // expected, actual
        logger.info("Code de la spécialité  [" + expected.getCodeSpecialite() + "]");        
        assertThat(expected.getNom()).isEqualTo(actual.getNom());
        logger.info("Nom de la spécialité  [" + expected.getNom() + "]");
        assertEquals(expected.getGroupeSpecialite().getCodeGroupeSpecialite(), code_groupe_specialite.getCodeGroupeSpecialite());
        logger.info("Code groupe de la spécialité  [" + expected.getGroupeSpecialite().getCodeGroupeSpecialite() + "]");
    }
}
