package fr.sofina.application.evenement;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
interface EvenementService {
    Evenement creerEvenement(@NotNull LocalDate date, @NotNull Long code);
}
