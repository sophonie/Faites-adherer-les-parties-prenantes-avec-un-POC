package fr.sofina.application.hopital;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HopitalRepository extends JpaRepository<Hopital, Long>, HopitalRepositoryCustom {

    List<Hopital> findByNom(String nom);
    
    
    
}
