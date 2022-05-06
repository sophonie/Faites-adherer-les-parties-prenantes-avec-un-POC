package fr.sofina.application.possede;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HopitalPossedeIncidentRepository extends CrudRepository<HopitalPossedeIncident, Long>{
    
}
