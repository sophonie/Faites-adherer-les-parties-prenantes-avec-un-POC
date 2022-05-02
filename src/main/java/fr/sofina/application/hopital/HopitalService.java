package fr.sofina.application.hopital;

import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public interface HopitalService {

    boolean existsByNom(String nom);
    int countLitsDisponibles(Long code);
    List<Hopital> findByNom(String nom);        
    Collection<Hopital> findHopitauxBySpecialite(Long code);
    String findHopital(@NotNull Long code); // code incident    
}
