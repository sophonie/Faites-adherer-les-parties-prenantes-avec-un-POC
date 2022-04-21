package fr.sofina.hopital;

import fr.sofina.specialite.Specialite;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
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
        Objects.requireNonNull(nom);
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
        Objects.requireNonNull(code);
        return hopitalRepository.countLitsDisponibles(code);
    }

    @Override
    public List<Hopital> findByNom(String nom) {
        Objects.requireNonNull(nom);
        return hopitalRepository.findByNom(nom);
    }

    @Override
    public String findOneSpecialiteByNom(String nom) {
        Objects.requireNonNull(nom);
        return hopitalRepository.findOneSpecialiteByNom(nom);
    }

    @Override
    public List<Specialite> findAllSpecialiteByCodeHopital(int code) {
        return hopitalRepository.findAllSpecialiteByCodeHopital(code);
    }
}
