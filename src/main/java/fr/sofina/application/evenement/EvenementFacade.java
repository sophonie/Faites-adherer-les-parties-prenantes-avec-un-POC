package fr.sofina.application.evenement;

import fr.sofina.application.hopital.HopitalRepository;
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

    @Qualifier("hopitalRepository")
    private final HopitalRepository hopitalRepository;

    @Autowired
    public EvenementFacade(EntityManager entityManager, HopitalRepository hopitalRepository) {
        this.entityManager = entityManager;
        this.hopitalRepository = hopitalRepository;
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

    @Override
    public String publierEvenement(Long code) { // code incident

        final String message = "Réserver un lit " + hopitalRepository.findHopital(code).getNom() + ".";

        return message;
    }
}
