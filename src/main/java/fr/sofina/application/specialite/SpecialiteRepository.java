package fr.sofina.application.specialite;

import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {

    @Transactional
    @Query(value = "SELECT code_specialite, nom_specialite, code_groupe_specialite "
            + "FROM gestion_urgence.tbspecialite s WHERE s.code_specialite = ?1", nativeQuery = true)
    Specialite findOneSpecialiteById(final Long code);

    // Rechercher toutes les spécialités présentes dans un hôpital
    @Transactional
    @Query(value = "SELECT s.code_specialite, s.nom_specialite, s.code_groupe_specialite "
            + "FROM gestion_urgence.tbhopital_possede_specialite p "
            + "INNER JOIN gestion_urgence.tbspecialite s ON s.code_specialite = p.code_specialite "
            + "INNER JOIN gestion_urgence.tbhopital h ON h.code_hopital = p.code_hopital "
            + "WHERE h.code_hopital = ?1", nativeQuery = true)    
    Collection<Specialite> findAllSpecialiteByCodeHopital(final Long code);
}
