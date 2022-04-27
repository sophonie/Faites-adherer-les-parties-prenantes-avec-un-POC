package fr.sofina.application.patient;

import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PatientService")
public class PatientFacade implements PatientService {

    @PersistenceContext // déclarer l'entityManager et mettre son annotation
    private final EntityManager entityManager;

    @Autowired
    public PatientFacade(EntityManager entityManager) {    
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Patient ajouterPatient(Patient patient) {        
        if (Objects.isNull(patient)) {
            throw new IllegalArgumentException("Le patient passé en paramètre doit être un objet référencé.");
        }

        entityManager.persist(patient);
        return patient; // on retourne le produit pour l'affichage dans l'API REST
    }
}
