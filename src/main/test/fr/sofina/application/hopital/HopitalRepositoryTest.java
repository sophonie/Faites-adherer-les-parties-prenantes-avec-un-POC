package fr.sofina.application.hopital;

import fr.sofina.application.SofinaApplication;
import fr.sofina.application.incident.Incident;
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

        // given
        final Long code_specialite = 21L;
        List<Hopital> hopitaux = new ArrayList<>();
        hopitaux.add(new Hopital("Hôpital Haut-Lévêque")); // actual
        hopitaux.add(new Hopital("Polyclinique Lormont"));
        hopitaux.add(new Hopital("Hôpital Saint-André de Bordeaux"));
        // when
        final List<Hopital> list = hopitalRepository
                .findHopitauxBySpecialite(code_specialite)
                .stream()
                .collect(Collectors.toList()); // expected        
        // then
        assertThat(hopitaux.get(0).getNom()).isEqualTo(list.get(0).getNom()); // actual, expected
        assertThat(hopitaux.get(1).getNom()).isEqualTo(list.get(1).getNom()); // actual, expected
        assertThat(hopitaux.get(2).getNom()).isEqualTo(list.get(2).getNom()); // actual, expected
    }

    @Test
    @DisplayName("Vérifier que l'on puisse trouver un hôpital à partir d'un code incident")
    void testFindHopital() {
        // given
        final Long code_incident = 1L; // code incident
        final Long code_hopital = 9L;
        final String nom_hopital = "Hôpital Saint-André de Bordeaux";
        final float latitude_hopital = 44.568653f;
        final float longitude_hopital = -0.57982f;
        final int lits_disponibles = 3;
        final String tel_hopital = "05 56 79 56 79";
        Incident incident = new Incident();
        incident.setCodeIncident(code_incident);
        // when
        Hopital hopital = new Hopital();
        hopital.setCodeHopital(code_hopital);
        hopital.setNom(nom_hopital);
        hopital.setLatitude(latitude_hopital);
        hopital.setLongitude(longitude_hopital);
        hopital.setLitsDisponibles(lits_disponibles);
        hopital.setTelephone(tel_hopital);
        hopitalRepository.findHopital(incident.getCodeIncident());
        // then
        assertThat(hopital.getCodeHopital()).isEqualTo(code_hopital);
        assertThat(hopital.getNom()).isEqualTo(nom_hopital);
        assertThat(hopital.getLatitude()).isEqualTo(latitude_hopital);
        assertThat(hopital.getLongitude()).isEqualTo(longitude_hopital);
        assertThat(hopital.getLitsDisponibles()).isEqualTo(lits_disponibles);
        assertThat(hopital.getTelephone()).isEqualTo(tel_hopital);
    }

}
