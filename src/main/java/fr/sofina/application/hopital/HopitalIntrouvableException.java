package fr.sofina.application.hopital;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Enregistrement introuvable dans la base de données") // 404 Not Found
public class HopitalIntrouvableException extends RuntimeException {

    public HopitalIntrouvableException() {
    }

    public HopitalIntrouvableException(String message) {
        super(message);
    }

    public HopitalIntrouvableException(String message, Throwable cause) {
        super(message, cause);
    }

    public HopitalIntrouvableException(Throwable cause) {
        super(cause);
    }

    public HopitalIntrouvableException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
