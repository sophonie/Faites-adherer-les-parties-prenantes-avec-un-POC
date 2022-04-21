package fr.sofina.application.specialite;

import fr.sofina.application.SofinaApplication;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootConfiguration
@Configurable
@RunWith(Runner.class)
@SpringBootTest(classes = SofinaApplication.class)
public class SpecialiteRepositoryTest {

    @Autowired
    @Qualifier("specialiteRepository")
    private SpecialiteRepository specialiteRepository;

    @Test
    @DisplayName("Trouver une spécialité par son code")
    void testFindOneSpecialiteById() {
        final Long code = 24L;        
        final Short code_groupe_specialite = 5;

        final Specialite actual = new Specialite();        
        actual.setCodespecialite(code);
        actual.setNom("Pharmacologie clinique et thérapeutique");
        actual.setGroupeSpecialite(code_groupe_specialite);

        final Specialite expected = specialiteRepository.findOneSpecialiteById(actual.getCodespecialite());

        assertThat(expected.getCodespecialite()).isEqualTo(actual.getCodespecialite());
        assertThat(expected.getNom()).isEqualTo(actual.getNom());
        assertThat(expected.getGroupeSpecialite()).isEqualTo(actual.getGroupeSpecialite());
    }

}
