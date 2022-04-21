package fr.sofina.application.specialite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("SpecialiteService")
public class SpecialiteFacade implements SpecialiteService {

    @Qualifier("specialiteRepository")
    private final SpecialiteRepository specialiteRepository;

    @Autowired
    public SpecialiteFacade(SpecialiteRepository specialiteRepository) {
        this.specialiteRepository = specialiteRepository;
    }

    @Override
    public List<Specialite> findAllSpecialiteByCodeHopital(Long code) { // code hopital        
        final List<Specialite> hopitaux = new ArrayList<>();
        Objects.requireNonNull(specialiteRepository);
        if (Objects.isNull(code)) {
            throw new IllegalArgumentException("Le nom passé en paramètre doit être un objet référencé.");
        } else {
            Collection<Specialite> collection = specialiteRepository.findAllSpecialiteByCodeHopital(code);
            for (Specialite specialite : collection) {
                final Specialite s = new Specialite();
                s.setCodespecialite(specialite.getCodespecialite());
                s.setNom(specialite.getNom());
                s.setGroupeSpecialite(specialite.getGroupeSpecialite());

                hopitaux.add(s);
            }
        }
        return hopitaux;
    }

    @Override
    public Specialite findOneSpecialiteById(Long code) { // code hopital
        Objects.requireNonNull(specialiteRepository);
        if (Objects.isNull(code)) {
            throw new IllegalArgumentException("Le nom passé en paramètre doit être un objet référencé.");
        }
        Optional<Specialite> specialiteOptional = specialiteRepository.findById(code);
        if (specialiteOptional.isPresent()) {
            return specialiteOptional.get();
        } else {
            throw new SpecialiteIntrouvableException("La spécialité recherchée n'existe pas dans la base de données.");
        }
    }
}
