package fr.sofina.application.incident;

import fr.sofina.application.hopital.Hopital;
import org.springframework.stereotype.Component;

@Component
public interface IncidentService {

    Hopital creerIncident(Incident incident); // retourne le code de l'incident

}
