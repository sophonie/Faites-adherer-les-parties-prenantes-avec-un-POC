package fr.sofina.application.evenement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends CrudRepository<Evenement, Object>{
    
    
}
