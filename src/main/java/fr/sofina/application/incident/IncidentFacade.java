package fr.sofina.application.incident;

import fr.sofina.application.evenement.Evenement;
import fr.sofina.application.evenement.EvenementRepository;
import fr.sofina.application.patient.Patient;
import fr.sofina.application.patient.PatientRepository;
import fr.sofina.application.specialite.Specialite;
import java.time.LocalDate;
import java.time.Month;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("IncidentService")
public class IncidentFacade implements IncidentService {

    @Qualifier("incidentRepository")
    private final IncidentRepository incidentRepository;

    @Qualifier("evenementRepository")
    private final EvenementRepository evenementRepository;

    @Qualifier("patientRepository")
    private final PatientRepository patientRepository;

    @Autowired
    public IncidentFacade(IncidentRepository incidentRepository,
            PatientRepository patientRepository,
            EvenementRepository evenementRepository) {
        this.incidentRepository = incidentRepository;
        this.patientRepository = patientRepository;
        this.evenementRepository = evenementRepository;
    }

    @Override
    @Transactional
    public Long creerIncident(final Incident incident) {

        final Specialite specialite = new Specialite();
        specialite.setCodeSpecialite(21L); // ce qui va être envoyé en POST

        final Evenement evenement = new Evenement();
        evenement.getCodeEvenement();
        evenement.setDateEvenement(LocalDate.now());
        evenement.setCodeSpecialite(specialite);

        evenementRepository.save(evenement);

        final Patient patient = new Patient();
        patient.setNom("Macias");
        patient.setPrenom("Dorothée");
        patient.setDatenaissance(LocalDate.of(2021, Month.NOVEMBER, 07));
        patient.setGenre("Agender");
        patient.setNO_SS(9091433491L);
        patientRepository.save(patient);

        incident.getLatitude();
        incident.getLongitude();
        incident.setEvenement(evenement);
        incident.setPatient(patient);
        incidentRepository.save(incident);

        return incident.getCodeIncident(); // retourne le code de l'incident
    }
}
