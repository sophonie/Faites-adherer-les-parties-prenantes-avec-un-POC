package fr.sofina.hopital;

import fr.sofina.specialite.Specialite;
import java.util.List;

public interface HopitalService {

    boolean existsByNom(String nom);
    int countLitsDisponibles(int code);
    List<Hopital> findByNom(String nom);
    // implémenter toutes les méthodes pour les tests
    String findOneSpecialiteByNom(String nom); // trouver une spécialité parmi toutes les spécialités disponibles
    List<Specialite> findAllSpecialiteByCodeHopital(int code); // Trouver toutes les spécialités présentes dans un hôpital
    
}
