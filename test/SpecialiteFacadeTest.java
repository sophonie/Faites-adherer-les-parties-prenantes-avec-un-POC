package fr.sofina.application.specialite;

import fr.sofina.application.SofinaApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class SpecialiteFacadeTest {

    @Autowired
    @Qualifier("specialiteRepository")
    private SpecialiteRepository specialiteRepository;

    @Test
    public void testFindAllSpecialiteByCodeHopital() {
        System.out.println("findAllSpecialiteByCodeHopital");
        Long code = 1L;
        SpecialiteService specialiteFacade = new SpecialiteFacade(specialiteRepository);
        final List<Specialite> specialites = new ArrayList<>(); // expected

        final Long codespecialite1 = 1L;        
        final String nomspecialite1 = "Anesthésie";
        final Short codegroupespecialite1 = 1;

        final Long codespecialite2 = 1L;        
        final String nomspecialite2 = "Soins intensifs";
        final Short codegroupespecialite2 = 1;

        final Long codespecialite3 = 17L;
        
        final String nomspecialite3 = "Médecine d'urgence";
        final Short codegroupespecialite3 = 4;

        specialites.add(new Specialite(codespecialite1, nomspecialite1, codegroupespecialite1));
        specialites.add(new Specialite(codespecialite2, nomspecialite2, codegroupespecialite2));
        specialites.add(new Specialite(codespecialite3, nomspecialite3, codegroupespecialite3)); // actual

        final List<Specialite> list = specialiteFacade
                .findAllSpecialiteByCodeHopital(code) // findAllSpecialiteByCodeHopital
                .stream()
                .collect(Collectors.toList()); // expected        

        assertEquals(specialites.get(0).getNom(), list.get(0).getNom());
        assertEquals(specialites.get(1).getNom(), list.get(1).getNom());
        assertEquals(specialites.get(2).getNom(), list.get(2).getNom());

    }

    @Test
    public void testFindOneSpecialiteById() {
        final Long codespecialite = 1L;        
        final String nom_specialite = "Anesthésie";
        final Short code_groupe_specialite = 1;
        Specialite expected = specialiteRepository.findOneSpecialiteById(codespecialite);

        assertEquals(codespecialite, expected.getCodespecialite());
        assertEquals(nom_specialite, expected.getNom());
        assertEquals(code_groupe_specialite, expected.getGroupeSpecialite()); // actual, expected
    }

}
