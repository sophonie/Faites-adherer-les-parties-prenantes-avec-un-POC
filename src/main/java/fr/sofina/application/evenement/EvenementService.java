package fr.sofina.application.evenement;

import org.springframework.stereotype.Component;

@Component
public interface EvenementService {

    Evenement creerEvenement(Evenement evenement);

    String publierEvenement();
}
