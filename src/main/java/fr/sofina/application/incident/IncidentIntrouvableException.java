package fr.sofina.application.incident;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Enregistrement introuvable dans la base de données") // 404 Not Found
public class IncidentIntrouvableException extends Exception {

    public IncidentIntrouvableException(String message) {
        super(message);
    }

    public IncidentIntrouvableException() {
    }

    public IncidentIntrouvableException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncidentIntrouvableException(Throwable cause) {
        super(cause);
    }

    public IncidentIntrouvableException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
