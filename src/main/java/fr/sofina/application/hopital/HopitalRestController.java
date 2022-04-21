package fr.sofina.application.hopital;

import fr.sofina.application.specialite.Specialite;
import fr.sofina.application.specialite.SpecialiteFacade;
import java.util.Collection;
import java.util.List;
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

@Slf4j
@RestController
public class HopitalRestController {

    private final Logger logger = LoggerFactory.getLogger(HopitalRestController.class);

    @Qualifier("hopitalFacade")
    private final HopitalFacade hopitalFacade;

    @Qualifier("specialiteFacade")
    private final SpecialiteFacade specialiteFacade;

    @Autowired
    public HopitalRestController(final HopitalFacade hopitalFacade, SpecialiteFacade specialiteFacade) {
        this.hopitalFacade = hopitalFacade;
        this.specialiteFacade = specialiteFacade;
    }

    // -------------------- Compter le nombre de lits disponibles dans un [hôpital] --------------------
    @RequestMapping(value = "/api/medhead/lits/{valeur}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200 OK    
    public int countLitsDisponibles(@PathVariable("valeur") int valeur) {
        logger.info("Compter le nombre de lits disponibles dans un hôpital.");
        return hopitalFacade.countLitsDisponibles(valeur);
    }

    // ------------------------------ Trouver une spécialité à partir de son code ------------------------------
    @RequestMapping(value = "/api/medhead/specialite/{code}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200 OK    
    public Specialite findOneSpecialiteById(@PathVariable("code") Long code) { // code specialité
        logger.info("Trouver une spécialité à partir de son code.");
        return specialiteFacade.findOneSpecialiteById(code);
    }

    // -------------- Rechercher toutes les spécialités présentes dans un [hôpital] --------------------
    @RequestMapping(value = "/api/medhead/hopital/{code}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE) // toutes les spécialités d'un hôpital pour un code spécifique
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public List<Specialite> findAllSpecialiteByCodeHopital(@PathVariable("code") Long code) { // code hopital
        logger.info("Trouver toutes les spécialités présentes dans un hôpital.");
        return specialiteFacade.findAllSpecialiteByCodeHopital(code);
    }
    
    // ----- Rechercher tous les hopitaux ayant une spécialité ET des lits disponibles -----
    @RequestMapping("/api/medhead/hopital/specialite/{code}")
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public Collection<Hopital> findAllBySpecialiteLitDisponible(@PathVariable("code") Long code) { // code spécialite
        logger.info("Trouver tous les hôpitaux ayant une spécialité ET des lits disponibles.");
        return hopitalFacade.findAllBySpecialiteLitDisponible(code);
    }

    /*
    // Rechercher l'hôpital le plus proche pour un patient ayant un incident d'un type précis (cardiologie)
    // ET que l'urgence soit localisée près d'un hôpital disposant de ce soin
    // ET qu'un lit soit disponible pour être réservé     
    @RequestMapping(value = "/api/medhead/specialite/{code}/hopital")
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public Optional findOne(@PathVariable("code") int code) {
        logger.info("Recherche en cours de l'hôpital le plus proche...");
        return Optional.empty();
    }
     */
}
