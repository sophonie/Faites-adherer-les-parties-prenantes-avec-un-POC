package fr.sofina.application.hopital;

import fr.sofina.application.SofinaApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.*;
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
public class HopitalRepositoryTest { // Tests intégration couche repository avec AssertJ

    @Autowired
    @Qualifier("hopitalRepository")
    private HopitalRepository hopitalRepository;

    @Test
    @DisplayName("Vérifier si un hopital existe par son nom")
    void testIfHopitalExistsByNom() {
        String nom = "Hôpital Public Saint-Augustin";
        // given
        Hopital hopital = new Hopital(nom);
        // when        
        boolean expected = hopitalRepository.existsByNom(hopital.getNom());
        // then
        assertThat(Boolean.TRUE).isEqualTo(expected);
    }

    @Test
    @DisplayName("Vérifier si un hôpital a une spécialité et des lits disponibles")
    void testFindAllBySpecialiteLitDisponible() {

        final Long code_specialite = 17L;
        // given
        List<Hopital> hopitaux = new ArrayList<>();
        hopitaux.add(new Hopital("Hôpital Haut-Lévêque")); // actual
        hopitaux.add(new Hopital("Hôpital Saint-André de Bordeaux"));
        hopitaux.add(new Hopital("Polyclinique Lormont"));
        // when
        final List<Hopital> list = hopitalRepository
                .findHopitauxBySpecialiteWithLitDisponible(code_specialite)
                .stream()
                .collect(Collectors.toList()); // expected        
        // then
        assertThat(hopitaux.get(0).getNom()).isEqualTo(list.get(0).getNom()); // actual, expected
        assertThat(hopitaux.get(1).getNom()).isEqualTo(list.get(1).getNom()); // actual, expected
        assertThat(hopitaux.get(2).getNom()).isEqualTo(list.get(2).getNom()); // actual, expected
    }
}
