package fr.sofina.application.hopital;

import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    int countLitsDisponibles(Long code);

    @Query(value = "SELECT "
            + "h.code_hopital, h.nom_hopital, h.latitude_hopital, "
            + "h.longitude_hopital, h.lits_disponibles "
            + "FROM   gestion_urgence.tbhopital h "
            + "INNER JOIN gestion_urgence.tbhopital_possede_specialite p ON p.code_hopital = h.code_hopital "
            + "INNER JOIN gestion_urgence.tbspecialite s ON s.code_specialite = p.code_specialite "
            + "WHERE s.code_specialite = ?1 AND h.lits_disponibles > 0 ORDER BY h.code_hopital", nativeQuery = true)
    Collection<Hopital> findHopitauxBySpecialite(Long code);

    @Query(value = "SELECT h.code_hopital, h.nom_hopital, "
            + "acos(sin(h.latitude_hopital) * sin(i.latitude_incident) + cos(h.latitude_hopital) "
            + "* cos(i.latitude_incident) * cos(i.longitude_incident - h.longitude_hopital)) * 6371 AS distance "
            + "FROM gestion_urgence.tbhopital h "
            + "INNER JOIN gestion_urgence.tbhopital_possede_specialite p ON h.code_hopital = p.code_hopital "
            + "INNER JOIN gestion_urgence.tbspecialite s ON p.code_specialite = s.code_specialite "
            + "INNER JOIN gestion_urgence.tbevenement e ON e.code_specialite = s.code_specialite "
            + "INNER JOIN gestion_urgence.tbincident i ON e.code_evenement = i.code_evenement "
            + "WHERE i.code_incident = ?1 AND h.lits_disponibles > 0 "
            + "ORDER BY distance ASC FETCH FIRST 1 ROW ONLY ", nativeQuery = true)
    String[] findHopital(Long code); // retourne le nom de l'hôpital le plus proche en passant le code incident

    @Modifying(clearAutomatically = true) // @Modifying obligatoire
    @Query(value = "UPDATE gestion_urgence.tbhopital SET lits_disponibles = lits_disponibles - 1 "
            + "WHERE code_hopital = ?1", nativeQuery = true)
    void reserverLitHopital(Long code); // code hopital

    @Query(value = "SELECT p.code_incident "
            + "FROM gestion_urgence.tbhopital_possede_incident p "
            + "WHERE p.code_hopital = ?1", nativeQuery = true)
    Long findByCodeIncident(Long code); // code hopital
    
    @Query(value = "SELECT p.code_hopital "
            + "FROM gestion_urgence.tbhopital_possede_incident p "
            + "WHERE p.code_incident = CAST(CAST(?1 AS TEXT) AS BIGINT)", nativeQuery = true) // CAST obligatoire
    Long findByCodeHopital(Long code); // code incident
}
