package fr.sofina.application.specialite;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Enregistrement introuvable dans la base de données") // 404 Not Found
public class SpecialiteIntrouvableException extends RuntimeException {

    public SpecialiteIntrouvableException() {
    }

    public SpecialiteIntrouvableException(String message) {
        super(message);
    }

    public SpecialiteIntrouvableException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpecialiteIntrouvableException(Throwable cause) {
        super(cause);
    }

    public SpecialiteIntrouvableException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
