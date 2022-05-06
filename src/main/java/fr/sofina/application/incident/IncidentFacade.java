package fr.sofina.application.incident;

import fr.sofina.application.evenement.Evenement;
import fr.sofina.application.evenement.EvenementRepository;
import fr.sofina.application.hopital.Hopital;
import fr.sofina.application.hopital.HopitalIntrouvableException;
import fr.sofina.application.hopital.HopitalRepository;
import fr.sofina.application.patient.Patient;
import fr.sofina.application.patient.PatientRepository;
import fr.sofina.application.possede.HopitalPossedeIncident;
import fr.sofina.application.possede.HopitalPossedeIncidentRepository;
import fr.sofina.application.specialite.Specialite;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
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

    @Qualifier("hopitalPossedeIncidentRepository")
    private final HopitalPossedeIncidentRepository hopitalPossedeIncidentRepository;

    @Qualifier("hopitalRepository")
    private final HopitalRepository hopitalRepository;

    @Autowired
    public IncidentFacade(IncidentRepository incidentRepository,
            PatientRepository patientRepository,
            EvenementRepository evenementRepository,
            HopitalRepository hopitalRepository,
            HopitalPossedeIncidentRepository hopitalPossedeIncidentRepository) {
        this.incidentRepository = incidentRepository;
        this.patientRepository = patientRepository;
        this.evenementRepository = evenementRepository;
        this.hopitalRepository = hopitalRepository;
        this.hopitalPossedeIncidentRepository = hopitalPossedeIncidentRepository;
    }

    @Override
    @Transactional
    public Hopital creerIncident(final Incident incident) {

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

        // rechercher l'hôpital le plus proche en lui passant le code de l'incident        
        final Hopital hopital = hopitalRepository.findHopital(incident.getCodeIncident());
        
        if (Objects.isNull(hopital)) {
            throw new HopitalIntrouvableException("Aucun hôpital n'a été trouvé correspondant à l'incident.");
        }
        
        final Long code_hopital = hopital.getCodeHopital();
        // mettre à jour le nombre de lits
        hopitalRepository.reserverLitHopital(code_hopital);
        // créer une ligne dans tbhopital_possede_incident
        final HopitalPossedeIncident hopitalPossedeIncident = new HopitalPossedeIncident();
        hopitalPossedeIncident.setHopital(hopital);
        hopitalPossedeIncident.setIncident(incident);
        
        hopitalPossedeIncidentRepository.save(hopitalPossedeIncident);
        
        return hopital; // retourne l'hôpital
    }
}
