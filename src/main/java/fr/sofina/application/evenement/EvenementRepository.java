package fr.sofina.application.evenement;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {

    /*
    @Modifying // obligatoire pour éviter could not extract ResultSet
    @Transactional
    @Query(value = "INSERT INTO gestion_urgence.tbevenement (date_evenement, code_specialite) "
            + "VALUES (?1, ?2)", nativeQuery = true)
     */
    Evenement creerEvenement(@NotNull LocalDate date, @NotNull Long code);
}
