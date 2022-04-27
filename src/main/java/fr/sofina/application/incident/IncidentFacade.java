package fr.sofina.application.incident;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("IncidentService")
public class IncidentFacade implements IncidentService {

    @Qualifier("incidentRepository")
    private final IncidentRepository incidentRepository;

    @Autowired
    public IncidentFacade(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    @Transactional
    public Incident creerIncident(final Incident incident) {

        return incidentRepository.save(
                new Incident(incident.getLatitude(), incident.getLongitude(),
                        incident.getEvenement(), incident.getPatient()));
    }
}
