package fr.sofina.application.patient;

import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public interface PatientService {
    Patient ajouterPatient(@NotNull Patient patient);
}
