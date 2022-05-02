package fr.sofina.application.hopital;

import fr.sofina.application.incident.IncidentIntrouvableException;
import fr.sofina.application.incident.IncidentRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("HopitalService")
@Transactional
public class HopitalFacade implements HopitalService {

    @Qualifier("hopitalRepository")
    private final HopitalRepository hopitalRepository;

    @Qualifier("incidentRepository")
    private final IncidentRepository incidentRepository;

    @Autowired
    public HopitalFacade(HopitalRepository hopitalRepository, IncidentRepository incidentRepository) {
        this.hopitalRepository = hopitalRepository;
        this.incidentRepository = incidentRepository;
    }

    @Override
    public boolean existsByNom(String nom) {
        Objects.requireNonNull(hopitalRepository);
        if (Objects.isNull(nom)) {
            throw new IllegalArgumentException("Le nom passé en paramètre doit être un objet référencé.");
        }
        final List<Hopital> hopitaux = hopitalRepository.findByNom(nom);
        boolean estPresent = Boolean.FALSE;

        final Hopital h = hopitaux.get(0);

        if (h.getNom().equals(nom)) {
            estPresent = Boolean.TRUE;
        } else {
            throw new HopitalIntrouvableException("Enregistrement introuvable dans la base de données");
        }

        return estPresent;
    }

    @Override
    public int countLitsDisponibles(Long code) {
        Objects.requireNonNull(hopitalRepository);
        if (Objects.isNull(code)) {
            throw new IllegalArgumentException("Le code passé en paramètre doit être un objet référencé.");
        }

        return hopitalRepository.countLitsDisponibles(code);
    }

    @Override
    public List<Hopital> findByNom(String nom) {
        Objects.requireNonNull(hopitalRepository);
        if (Objects.isNull(nom)) {
            throw new IllegalArgumentException("Le nom passé en paramètre doit être un objet référencé.");
        }
        return hopitalRepository.findByNom(nom);
    }

    @Override
    public Collection<Hopital> findHopitauxBySpecialite(Long code) {
        Objects.requireNonNull(hopitalRepository);
        if (Objects.isNull(code)) {
            throw new HopitalIntrouvableException("Enregistrement introuvable dans la base de données");
        }
        return hopitalRepository.findHopitauxBySpecialite(code);
    }

    @Override
    public String findHopital(Long code) { // code incident
        Objects.nonNull(hopitalRepository);
        if (Objects.isNull(code)) {
            throw new IllegalArgumentException("Les valeurs passées en paramètre doit être des objets référencés.");
        }

        // si le code passé en paramètre n'est pas équivalent au dernier code incident présent en DB
        if (!Objects.equals(incidentRepository.findLastId(), code)) {
            try {
                throw new IncidentIntrouvableException("Code incident introuvable dans la base de données.");
            } catch (IncidentIntrouvableException ex) {
                Logger.getLogger(HopitalFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return Arrays.toString(hopitalRepository.findHopital(code));
    }
    
    
}
