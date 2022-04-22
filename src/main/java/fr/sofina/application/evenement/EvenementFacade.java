package fr.sofina.application.evenement;

import java.time.LocalDate;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("EvenementService")
@Transactional
public class EvenementFacade implements EvenementService {

    @Qualifier("evenementRepository")
    private final EvenementRepository evenementRepository;

    @Autowired
    public EvenementFacade(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    @Override
    public Evenement creerEvenement(LocalDate date, Long code) {
        Objects.requireNonNull(evenementRepository);
        if (Objects.isNull(date) || Objects.isNull(code)) {
            throw new IllegalArgumentException("Les valeurs passées en paramètre doivent être des objets référencés.");
        }
        return evenementRepository.creerEvenement(date, code);
    }

}
