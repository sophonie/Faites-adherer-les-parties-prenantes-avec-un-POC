package fr.sofina.application.incident;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.sofina.application.evenement.Evenement;
import fr.sofina.application.patient.Patient;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBINCIDENT", schema = "GESTION_URGENCE")
public class Incident implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_incident", unique = true, nullable = false)
    private Long code_incident;

    @Column(name = "latitude_incident", nullable = false)
    private float latitude;

    @Column(name = "longitude_incident", nullable = false)
    private float longitude;
    
    // The unsaved transient entity must be saved in an operation prior to saving these dependent entities :
    // ajouter (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "code_evenement")
    private Evenement evenement;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "code_patient")
    private Patient patient;

    public Incident() {
    }

    public Incident(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Incident(
            @JsonProperty("latitude_incident") float latitude,
            @JsonProperty("longitude_incident") float longitude,
            @JsonProperty("evenement") final Evenement evenement,
            @JsonProperty("patient") final Patient patient) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.evenement = evenement;
        this.patient = patient;
    }

    public Long getCodeIncident() {
        return code_incident;
    }

    public void setCodeIncident(Long code_incident) {
        this.code_incident = code_incident;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
