package fr.sofina.application.incident;

import org.springframework.stereotype.Component;

@Component
public interface IncidentService {

    Incident creerIncident(Incident incident);    

}
