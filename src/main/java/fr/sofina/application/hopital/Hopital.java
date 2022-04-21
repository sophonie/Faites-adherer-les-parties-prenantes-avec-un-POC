package fr.sofina.hopital;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.sofina.specialite.Specialite;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TBHOPITAL", schema = "GESTION_URGENCE")
public class Hopital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_hopital", unique = true, nullable = false, columnDefinition = "SMALLSERIAL")
    private int codehopital;

    @Column(name = "nom_hopital", nullable = false)
    @NotBlank
    private String nom;

    @Column(name = "latitude_hopital", nullable = false, columnDefinition = "NUMERIC(9,7)")
    private float latitude;

    @Column(name = "longitude_hopital", nullable = false, columnDefinition = "NUMERIC(9,7)")
    private float longitude;    

    @Column(name = "lits_disponibles", nullable = false, columnDefinition = "INT4")
    private int litsDisponibles;
        
    @ManyToMany(mappedBy = "hopitaux", fetch = FetchType.LAZY)
    private Set<Specialite> specialites;

    public Hopital() {
        this.specialites = new HashSet<>();
    }

    public Hopital(@JsonProperty("nom_hopital") final String nom) {
        this.nom = nom;
    }

    public Hopital(
            @JsonProperty("code_hopital") final int codehopital,
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

    public int getCodehopital() {
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

    public Set<Specialite> getSpecialites() {
        return specialites;
    }

}
