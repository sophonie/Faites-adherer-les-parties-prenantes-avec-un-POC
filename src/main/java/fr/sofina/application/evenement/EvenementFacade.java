package fr.sofina.application.evenement;

import fr.sofina.application.hopital.HopitalRepository;
import fr.sofina.application.incident.Incident;
import fr.sofina.application.incident.IncidentRepository;
import fr.sofina.application.journal.JournalIncident;
import fr.sofina.application.journal.JournalIncidentRepository;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("EvenementService")
@Transactional
public class EvenementFacade implements EvenementService {

    private final Logger logger = LoggerFactory.getLogger(EvenementFacade.class);

    @Qualifier("persistenceUnit")
    private final EntityManager entityManager;

    @Qualifier("journalRepository")
    private final JournalIncidentRepository journalRepository;

    @Qualifier("hopitalRepository")
    private final HopitalRepository hopitalRepository;

    @Qualifier("incidentRepository")
    private final IncidentRepository incidentRepository;

    @Autowired
    public EvenementFacade(EntityManager entityManager,
            JournalIncidentRepository journalIncidentRepository,
            HopitalRepository hopitalRepository,
            IncidentRepository incidentRepository) {
        this.entityManager = entityManager;
        this.journalRepository = journalIncidentRepository;
        this.hopitalRepository = hopitalRepository;
        this.incidentRepository = incidentRepository;
    }

    @Override
    public Evenement creerEvenement(@NotNull Evenement evenement) {
        Objects.requireNonNull(entityManager);
        if (Objects.isNull(evenement)) {
            throw new IllegalArgumentException("L'événement passé en paramètre doit être un objet référencé.");
        }

        entityManager.persist(evenement);
        return evenement;
    }

    Long creerLigneJournalIncident(Long code_hopital) {
        Objects.requireNonNull(code_hopital);
        final JournalIncident journal = new JournalIncident();
        journal.setCodeIncident(hopitalRepository.findByCodeIncident(code_hopital));
        journal.setNom("Macias");
        journal.setPrenom("Dorothée");
        journal.setDatenaissance(LocalDate.of(2021, Month.NOVEMBER, 07));
        journal.setGenre("Agender");
        journal.setNO_SS(9091433491L);
        journal.setCodeHopital(code_hopital);

        journalRepository.save(journal);

        return journal.getCode_incident(); // retourne le code de l'incident inséré
    }

    @Override
    public String publierEvenement() {
        final Incident incident = new Incident();
        incident.setCodeIncident(incidentRepository.findLastId()); // code du dernier incident 
        final Long code_hopital = hopitalRepository.findByCodeHopital(incident.getCodeIncident());
        logger.info("tbhopital [code_hopital] : " + code_hopital);
        final Long callback = creerLigneJournalIncident(code_hopital);
        logger.info("tbjournal_incident [code_incident] : " + callback);
        hopitalRepository.reserverLitHopital(code_hopital);
        logger.info("Réservation lit [code_hopital, code_incident] : [" + code_hopital + ", " + incident.getCodeIncident() + "]");

        final String message = "Réservation lit [code_hopital, code_incident] : [" + code_hopital + ", " + incident.getCodeIncident() + "]";
        return message;
    }
}
