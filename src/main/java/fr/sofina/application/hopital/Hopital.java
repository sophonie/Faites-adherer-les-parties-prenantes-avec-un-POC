package fr.sofina.application.hopital;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TBHOPITAL", schema = "GESTION_URGENCE")
public class Hopital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_hopital", unique = true, nullable = false)
    private Long codehopital;

    @Column(name = "nom_hopital", nullable = false)
    @NotBlank
    private String nom;

    @Column(name = "latitude_hopital", nullable = false)
    private float latitude;

    @Column(name = "longitude_hopital", nullable = false)
    private float longitude;

    @Column(name = "lits_disponibles", nullable = false)
    private int litsDisponibles;

    public Hopital() {
    }

    public Hopital(@JsonProperty("nom_hopital") final String nom) {
        this.nom = nom;
    }

    public Hopital(
            @JsonProperty("code_hopital") final Long codehopital,
            @JsonProperty("nom_hopital") final String nom,
            @JsonProperty("latitude_hopital") final float latitude,
            @JsonProperty("longitude_hopital") final float longitude,
            @JsonProperty("lits_disponibles") final int lits_disponibles) {
        this.codehopital = codehopital;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.litsDisponibles = lits_disponibles;
    }

    public void setCodehopital(Long codehopital) {
        this.codehopital = codehopital;
    }
    
    public Long getCodehopital() {
        return codehopital;
    }

    public String getNom() {
        return nom;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public int getLitsDisponibles() {
        return litsDisponibles;
    }
}
