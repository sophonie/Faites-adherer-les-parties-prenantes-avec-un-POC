package fr.sofina.application.incident;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IncidentRepository extends CrudRepository<Incident, Object> {

   
}
