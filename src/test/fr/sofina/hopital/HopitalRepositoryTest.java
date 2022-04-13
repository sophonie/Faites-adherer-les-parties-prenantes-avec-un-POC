package fr.sofina.hopital;

import fr.sofina.application.SofinaApplication;
import fr.sofina.specialite.Specialite;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@Configurable
@SpringBootTest(classes = SofinaApplication.class)
@RunWith(JUnitPlatform.class)
public class HopitalRepositoryTest { // Tests unitaires avec AssertJ

    @Autowired
    @Qualifier("hopitalRepository")
    private HopitalRepository hopitalRepository;    
    
    @Test
    @DisplayName("Vérifier si un hopital existe par son nom")
    void itShouldCheckIfHopitalExistsByNom() {
        String nom = "Hôpital Public Saint-Augustin";
        // given
        Hopital hopital = new Hopital(nom);
        // when        
        boolean expected = hopitalRepository.existsByNom(hopital.getNom());        
        // then
        assertThat(Boolean.TRUE).isEqualTo(expected);                
    }
        
    @Test    
    @DisplayName("Trouver une spécialité par son nom")
    void canGetOneSpecialiteByNom() {
        final String nom = "Santé publique";
        
        final Specialite specialite = new Specialite(nom);
        
        String expected = hopitalRepository.findOneSpecialiteByNom(specialite.getNom());
        
        assertThat(nom).isEqualTo(expected);
    }
}
