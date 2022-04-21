package fr.sofina.application.specialite;

import java.util.Collection;
import org.springframework.stereotype.Component;

@Component
public interface SpecialiteService {
    Specialite findOneSpecialiteById(final Long code); // trouver une spécialité à partir de son code
    Collection<Specialite> findAllSpecialiteByCodeHopital(final Long code); // Trouver toutes les spécialités présentes dans un hôpital
}
