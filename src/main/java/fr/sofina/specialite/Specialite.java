package fr.sofina.specialite;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.sofina.hopital.Hopital;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TBSPECIALITE", schema = "GESTION_URGENCE")
public class Specialite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_specialite", unique = true, nullable = false, columnDefinition = "SMALLSERIAL")
    private int codespecialite;

    @Column(name = "nom_specialite", nullable = false)
    @NotBlank
    private String nom;

    @Column(name = "code_groupe_specialite", nullable = false, columnDefinition = "INT2")
    private int codeGroupeSpecialite;
    
    @ManyToMany
    private List<Hopital> hopitaux;    

    public Specialite() {
        this.hopitaux = new ArrayList<>();
    }

    public Specialite(String nom) {
        this.nom = nom;
    }
    
    public Specialite(
            @JsonProperty("code_specialite") final int codespecialite,
            @JsonProperty("nom") final String nom) {
        this.codespecialite = codespecialite;
        this.nom = nom;
    }

    public int getCodespecialite() {
        return codespecialite;
    }

    public String getNom() {
        return nom;
    }

    public int getCodeGroupeSpecialite() {
        return codeGroupeSpecialite;
    }

    public List<Hopital> getHopitaux() {
        return hopitaux;
    }
}
