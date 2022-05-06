package fr.sofina.application.hopital;

import fr.sofina.application.SofinaApplication;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootConfiguration
@Configurable
@RunWith(Runner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = SofinaApplication.class)
public class HopitalServiceTest { // Tests unitaires couche service avec Mockito et AssertJ

    @Mock
    private HopitalService hopitalFacade;

    @BeforeEach
    void setUp() {
        hopitalFacade = mock(HopitalService.class);
    }

    @Test
    @DisplayName("Compter le nombre de lits disponibles dans un hôpital")
    void testLitsDisponibles() {
        final Long code = 7L;

        // when ... then
        when(hopitalFacade.countLitsDisponibles(code)).thenReturn(5);
        // when(mock.getArticles()).thenReturn(articles);
        Assertions.assertEquals(5, hopitalFacade.countLitsDisponibles(code));
        // verify
        verify(hopitalFacade).countLitsDisponibles(code);
    }

    @Test
    @DisplayName("Vérifier si un hôpital a une spécialité et des lits disponibles")
    void testFindAllHopitalBySpecialiteWithLitDisponible() {
        final Long codespecialite = 21L;
        final List<Hopital> hopitaux = new ArrayList<>();
        final Long code1 = 1L;
        final Long code2 = 4L;
        final Long code3 = 9L;
        
        hopitaux.add(new Hopital(code1, "Hôpital Haut-Lévêque", 44.7844478f, -0.6635059f, 9, "05 56 79 56 79"));
        hopitaux.add(new Hopital(code2, "Polyclinique Lormont", 47.6398521f, -0.6159895f, 3, "05 57 80 84 84"));
        hopitaux.add(new Hopital(code3, "Hôpital Saint-André de Bordeaux", 52.5686517f, -0.5798204f, 7, "05 56 79 56 79"));
        // when ... then
        when(hopitalFacade.findHopitauxBySpecialite(codespecialite)).thenReturn(hopitaux);
        Assertions.assertEquals(3, hopitalFacade.findHopitauxBySpecialite(codespecialite).size());        
        // verify
        verify(hopitalFacade).findHopitauxBySpecialite(codespecialite);
    }
}
