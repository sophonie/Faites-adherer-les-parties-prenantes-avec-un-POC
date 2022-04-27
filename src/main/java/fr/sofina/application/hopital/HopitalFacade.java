package fr.sofina.application.hopital;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("HopitalService")
@Transactional
public class HopitalFacade implements HopitalService {
    
    @Qualifier("hopitalRepository")
    private final HopitalRepository hopitalRepository;
    
    @Autowired
    public HopitalFacade(HopitalRepository hopitalRepository) {
        this.hopitalRepository = hopitalRepository;
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
    public int countLitsDisponibles(int code) {
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
    public Collection<Hopital> findAllBySpecialiteLitDisponible(Long code) {
        Objects.requireNonNull(hopitalRepository);
        if(Objects.isNull(code)) {
            throw new HopitalIntrouvableException("Enregistrement introuvable dans la base de données");
        }
        return hopitalRepository.findHopitauxBySpecialiteWithLitDisponible(code);
    }    
}
