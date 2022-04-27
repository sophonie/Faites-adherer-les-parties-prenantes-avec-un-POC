package fr.sofina.application.specialite;

import fr.sofina.application.SofinaApplication;
import fr.sofina.application.groupespecialite.GroupeSpecialite;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootConfiguration
@Configurable
@RunWith(Runner.class)
@SpringBootTest(classes = SofinaApplication.class)
public class SpecialiteServiceTest { // Tests unitaires couche service avec Mockito et AssertJ

    @Autowired
    private SpecialiteService specialiteFacade;

    @Test
    public void testFindAllSpecialiteByCodeHopital() {
        System.out.println("findAllSpecialiteByCodeHopital");
        Long code = 1L;
        final List<Specialite> specialites = new ArrayList<>(); // expected

        final Long codespecialite1 = 1L;
        final String nomspecialite1 = "Anesthésie";
        final GroupeSpecialite codegroupespecialite1 = new GroupeSpecialite();
        codegroupespecialite1.setCodeGroupeSpecialite(1L);

        final Long codespecialite2 = 1L;
        final String nomspecialite2 = "Soins intensifs";
        final GroupeSpecialite codegroupespecialite2 = new GroupeSpecialite();
        codegroupespecialite2.setCodeGroupeSpecialite(1L);

        final Long codespecialite3 = 17L;

        final String nomspecialite3 = "Médecine d'urgence";
        final GroupeSpecialite codegroupespecialite3 = new GroupeSpecialite();
        codegroupespecialite3.setCodeGroupeSpecialite(4L);

        specialites.add(new Specialite(codespecialite1, nomspecialite1, codegroupespecialite1));
        specialites.add(new Specialite(codespecialite2, nomspecialite2, codegroupespecialite2));
        specialites.add(new Specialite(codespecialite3, nomspecialite3, codegroupespecialite3)); // actual

        final List<Specialite> list = specialiteFacade
                .findAllSpecialiteByCodeHopital(code) // findAllSpecialiteByCodeHopital
                .stream()
                .collect(Collectors.toList()); // expected        

        assertEquals(list.get(0).getNom(), specialites.get(0).getNom()); // expected, actual
        assertEquals(list.get(1).getNom(), specialites.get(1).getNom());
        assertEquals(list.get(2).getNom(), specialites.get(2).getNom());
    }

    @Test
    public void testFindOneSpecialiteById() {
        final Long codespecialite = 1L;
        final String nom_specialite = "Anesthésie";
        final GroupeSpecialite groupe_specialite = new GroupeSpecialite();
        groupe_specialite.setCodeGroupeSpecialite(1L);
        groupe_specialite.setNom("Anesthésie");
        Specialite expected = specialiteFacade.findOneSpecialiteById(codespecialite);
        expected.setGroupeSpecialite(new GroupeSpecialite(groupe_specialite.getCodeGroupeSpecialite(), groupe_specialite.getNom()));

        assertEquals(expected.getCode_specialite(), codespecialite); // expected, actual
        assertEquals(expected.getNom(), nom_specialite);
        assertEquals(expected.getGroupeSpecialite().getCodeGroupeSpecialite(), groupe_specialite.getCodeGroupeSpecialite());
    }
}
