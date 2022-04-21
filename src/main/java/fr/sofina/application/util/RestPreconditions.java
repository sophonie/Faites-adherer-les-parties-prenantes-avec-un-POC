package fr.sofina.util;

import fr.sofina.hopital.HopitalIntrouvableException;

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
