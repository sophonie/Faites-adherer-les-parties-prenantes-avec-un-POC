package fr.sofina.hopital;

import fr.sofina.specialite.Specialite;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HopitalRepository extends JpaRepository<Hopital, Long> {

    @Transactional
    @Query(value = "SELECT CASE WHEN COUNT(h.nom_hopital) > 0 THEN TRUE ELSE FALSE END "
            + "FROM gestion_urgence.tbhopital h WHERE h.nom_hopital = ?1",
            nativeQuery = true)
    boolean existsByNom(final String nom);

    List<Hopital> findByNom(String nom);

    @Transactional
    @Query(value = "SELECT lits_disponibles FROM gestion_urgence.tbhopital h WHERE h.code_hopital = ?1", nativeQuery = true)
    int countLitsDisponibles(final int code);

    @Transactional
    @Query(value = "SELECT DISTINCT(nom_specialite) FROM gestion_urgence.tbspecialite s WHERE s.nom_specialite = ?1", nativeQuery = true)
    String findOneSpecialiteByNom(final String nom);

    @Transactional
    @Query(value = "SELECT s.nom_specialite FROM gestion_urgence.tbhopital_possede_specialite p "
            + "INNER JOIN gestion_urgence.tbspecialite s ON s.code_specialite = p.code_specialite "
            + "INNER JOIN gestion_urgence.tbhopital h ON h.code_hopital = p.code_hopital"
            + "WHERE h.code_hopital = ?1", nativeQuery = true)
    List<Specialite> findAllSpecialiteByCodeHopital(int code);

}
