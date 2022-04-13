package fr.sofina.hopital;

import fr.sofina.specialite.Specialite;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBootConfiguration
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class HopitalFacadeTest { // Tests intégration couche service avec Mockito et AssertJ

    @Mock
    private HopitalRepository hopitalRepository;
    private HopitalService hopitalFacade;

    @BeforeEach
    void setUp() {
        hopitalFacade = new HopitalFacade(hopitalRepository);
    }

    @Test
    @DisplayName("Compter le nombre de lits disponibles dans un hôpital")
    void canCountLitsDisponibles() {
        final int code = 7;

        // when ... then
        when(hopitalFacade.countLitsDisponibles(code)).thenReturn(5);
        Assertions.assertEquals(5, hopitalFacade.countLitsDisponibles(code));
        // verify        
        verify(hopitalRepository).countLitsDisponibles(code);

    }

    @Test
    @DisplayName("Trouver toutes les spécialités présentes dans un hôpital")
    void canFindAllSpecialiteByCodeHopital() {
        final int code = 1; 

        List<Specialite> specialites = hopitalRepository.findAllSpecialiteByCodeHopital(code);
        for (Specialite specialite : specialites) {
            Assertions.assertTrue(specialite.getNom().equals(code));
        }
    }
}
