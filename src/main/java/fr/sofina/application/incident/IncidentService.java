package fr.sofina.application.incident;

import org.springframework.stereotype.Component;

@Component
public interface IncidentService {

    Long creerIncident(Incident incident); // retourne le code de l'incident

}
