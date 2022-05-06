package fr.sofina.application.hopital;

import fr.sofina.application.evenement.EvenementService;
import fr.sofina.application.incident.Incident;
import fr.sofina.application.incident.IncidentFacade;
import fr.sofina.application.incident.IncidentService;
import fr.sofina.application.specialite.Specialite;
import fr.sofina.application.specialite.SpecialiteService;
import java.util.Collection;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HopitalRestController {

    private final Logger logger = LoggerFactory.getLogger(HopitalRestController.class);

    @Qualifier("hopitalFacade")
    private final HopitalService hopitalFacade;

    @Qualifier("specialiteFacade")
    private final SpecialiteService specialiteFacade;

    @Qualifier("evenementFacade")
    private final EvenementService evenementFacade;

    @Qualifier("incidentFacade")
    private final IncidentService incidentFacade;

    @Autowired
    public HopitalRestController(
            HopitalService hopitalFacade, SpecialiteService specialiteFacade,
            EvenementService evenementFacade, IncidentFacade incidentFacade) {
        this.hopitalFacade = hopitalFacade;
        this.specialiteFacade = specialiteFacade;
        this.evenementFacade = evenementFacade;
        this.incidentFacade = incidentFacade;
    }

    // ------------------------------ Compter le nombre de lits disponibles dans un [hôpital] -------------------------
    @RequestMapping(value = "/api/medhead/lits/{valeur}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200 OK    
    public int countLitsDisponibles(@PathVariable("valeur") Long valeur) {
        logger.info("Compter le nombre de lits disponibles dans un hôpital.");
        return hopitalFacade.countLitsDisponibles(valeur);
    }

    // ----------------------------------- Trouver une spécialité à partir de son code --------------------------------
    @RequestMapping(value = "/api/medhead/specialite/{code}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200 OK    
    public Specialite findOneSpecialiteById(@PathVariable("code") Long code) { // code specialité
        logger.info("Trouver une spécialité à partir de son code.");
        return specialiteFacade.findOneSpecialiteById(code);
    }

    // ------------------------ Rechercher toutes les spécialités présentes dans un [hôpital] -------------------------
    @RequestMapping(value = "/api/medhead/hopital/{code}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE) // toutes les spécialités d'un hôpital pour un code spécifique
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public Collection<Specialite> findAllSpecialiteByCodeHopital(@PathVariable("code") Long code) { // code hopital
        logger.info("Trouver toutes les spécialités présentes dans un hôpital.");
        return specialiteFacade.findAllSpecialiteByCodeHopital(code);
    }

    // ----- Rechercher tous les hopitaux ayant une spécialité ET des lits disponibles -----
    @RequestMapping("/api/medhead/hopital/specialite/{code}")
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public Collection<Hopital> findAllBySpecialiteLitDisponible(@PathVariable("code") Long code) { // code spécialite
        logger.info("Trouver tous les hôpitaux ayant une spécialité ET des lits disponibles.");
        return hopitalFacade.findHopitauxBySpecialite(code);
    }

    // ----------------------------------------------- créer l'incident -----------------------------------------------    
    @RequestMapping(value = "/api/medhead/incident", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED) // 201 CREATED
    public Hopital creerIncident(@RequestBody Incident incident) {
        if (Objects.isNull(incident)) {
            throw new IllegalArgumentException("L'incident passé en paramètre doit être un objet référencé.");
        }
        return incidentFacade.creerIncident(incident);
    }
    
    // ---------------------------------------------- publier événement -----------------------------------------------
    @RequestMapping(value = "/api/medhead/evenement/{code}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200 CREATED
    public String publierEvenement(@PathVariable("code") Long code) { // code incident
        logger.info("Publication de l'événement pour la réservation d'un lit.");
        return evenementFacade.publierEvenement(code);
    }
}
