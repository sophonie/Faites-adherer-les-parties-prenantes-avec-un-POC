package fr.sofina.application.util;

import fr.sofina.application.hopital.HopitalIntrouvableException;

public class RestPreconditions {

    // proctection de la classe utilitaire
    private RestPreconditions() {
    }

    public static <T> T verifierDisponibiliteLit(T resource) {
        if (resource == null) {
            throw new HopitalIntrouvableException();
        }
        return resource;
    }
}
