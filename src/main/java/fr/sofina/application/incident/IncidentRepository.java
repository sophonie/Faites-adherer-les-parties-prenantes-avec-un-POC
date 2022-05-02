package fr.sofina.application.incident;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IncidentRepository extends CrudRepository<Incident, Object> {

    @Query(value = "SELECT MAX(code_incident) FROM gestion_urgence.tbincident ", nativeQuery = true)
    Long findLastId();
}
