package fr.sofina.hopital;

import fr.sofina.specialite.Specialite;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HopitalRestController {

    private final Logger logger = LoggerFactory.getLogger(HopitalRestController.class);
    @Qualifier("hopitalFacade")
    private final HopitalFacade hopitalFacade;

    @Autowired
    public HopitalRestController(final HopitalFacade hopitalFacade) {
        this.hopitalFacade = hopitalFacade;
    }

    // -------------------- Compter le nombre de lits disponibles dans un [hôpital] --------------------    
    @RequestMapping(value = "/api/medhead/lits/{valeur}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public int countLitsDisponibles(@PathVariable("valeur") int valeur) {
        logger.info("Compter le nombre de lits disponibles dans un hôpital.");
        return hopitalFacade.countLitsDisponibles(valeur);
    }

    // -------------- Rechercher toutes les spécialités présentes dans un [hôpital] --------------------
    @RequestMapping(value = "/api/medhead/{code}/specialite", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE) // toutes les spécialités d'un hôpital pour un code spécifique
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public List<Specialite> findAllSpecialiteByCodeHopital(@PathVariable("code") int code) {
        logger.info("Trouver toutes les spécialités présentes dans un hôpital.");
        return hopitalFacade.findAllSpecialiteByCodeHopital(code);
    }

    // ----- Rechercher tous les hopitaux ayant un domaine de [spécialité] ET ayant au moins un lit de disponible -----
    @RequestMapping("/api/medhead/{code}/hopital")
    @ResponseStatus(HttpStatus.OK) // 200 OK    
    public List<Hopital> findAllHopitalById(@PathVariable("code") int code) {
        logger.info("Trouver tous les hôpitaux ayant un domaine de spécialité.");
        return null;
    }

    // Rechercher l'hôpital le plus proche pour un patient ayant un incident d'un type précis (cardiologie)
    // ET que l'urgence soit localisée près d'un hôpital disposant de ce soin
    // ET qu'un lit soit disponible pour être réservé     
    @RequestMapping(value = "/api/medhead/specialite/{code}/hopital")
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public Optional findOne(@PathVariable("code") int code) {
        logger.info("Recherche en cours de l'hôpital le plus proche...");
        return Optional.empty();
    }
}
