package fr.sofina.application.hopital;

import java.util.Collection;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface HopitalRepositoryCustom extends JpaRepository<Hopital, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(h.nom_hopital) > 0 THEN TRUE ELSE FALSE END "
            + "FROM gestion_urgence.tbhopital h WHERE h.nom_hopital = ?1",
            nativeQuery = true)
    boolean existsByNom(String nom);

    @Query(value = "SELECT lits_disponibles FROM gestion_urgence.tbhopital h WHERE h.code_hopital = ?1", nativeQuery = true)
    int countLitsDisponibles(int code);

    
    @Query(value = "SELECT "
            + "	h.code_hopital, h.nom_hopital, h.latitude_hopital, "
            + "	h.longitude_hopital, h.lits_disponibles "
            + "FROM   gestion_urgence.tbhopital h "
            + "INNER JOIN gestion_urgence.tbhopital_possede_specialite p ON p.code_hopital = h.code_hopital "
            + "INNER JOIN gestion_urgence.tbspecialite s ON s.code_specialite = p.code_specialite "
            + "WHERE s.code_specialite = ?1 AND h.lits_disponibles > 0", nativeQuery = true)
    Collection<Hopital> findHopitauxBySpecialiteWithLitDisponible(Long code);
    
    @Query(value = "" , nativeQuery = true)
    Optional<Hopital> findNearestHopitalWithLitDisponible(Long code, float distance);
}
