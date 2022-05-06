package fr.sofina.application.hopital;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.sofina.application.incident.Incident;
import fr.sofina.application.specialite.Specialite;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @NotBlank
    private float latitude;

    @Column(name = "longitude_hopital", nullable = false)
    @NotBlank
    private float longitude;

    @Column(name = "lits_disponibles", nullable = false)
    @NotBlank
    private int litsDisponibles;

    @Column(name = "tel_hopital", nullable = true)
    private String telephone;

    @OneToMany(mappedBy = "code_specialite", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Specialite> specialites;

    @OneToMany(mappedBy = "code_incident", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Incident> incidents;

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
            @JsonProperty("lits_disponibles") final int lits_disponibles,
            @JsonProperty("tel_hopital") final String telephone) {
        this.codehopital = codehopital;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.litsDisponibles = lits_disponibles;
        this.telephone = telephone;
        this.specialites = new HashSet<>();
        this.incidents = new HashSet<>();
    }

    public void setCodeHopital(Long codehopital) {
        this.codehopital = codehopital;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLitsDisponibles(int litsDisponibles) {
        this.litsDisponibles = litsDisponibles;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getCodeHopital() {
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

    public String getTelephone() {
        return telephone;
    }

    public Set<Specialite> getSpecialites() {
        return specialites;
    }

    public void setSpecialites(Set<Specialite> specialites) {
        this.specialites = specialites;
    }

    public Set<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents) {
        this.incidents = incidents;
    }

}
