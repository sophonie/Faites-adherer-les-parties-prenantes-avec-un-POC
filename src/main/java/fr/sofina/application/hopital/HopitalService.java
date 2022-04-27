package fr.sofina.application.hopital;

import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface HopitalService {

    boolean existsByNom(String nom);
    int countLitsDisponibles(int code);
    List<Hopital> findByNom(String nom);        
    // implémenter toutes les méthodes pour les tests
    Collection<Hopital> findAllBySpecialiteLitDisponible(Long code);
    //Optional<Hopital> findHopital(@NotNull Long code); // code spécialite
    // si l'Optional n'est pas vide alors c'est qu'un hopital a été trouvé, et il faut donc :
    // - décrémenter un lit de cet hôpital
    
}
