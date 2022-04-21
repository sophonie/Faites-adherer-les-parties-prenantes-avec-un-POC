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
    
}
