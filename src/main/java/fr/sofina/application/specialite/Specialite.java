package fr.sofina.application.specialite;

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
@Table(name = "TBSPECIALITE", schema = "GESTION_URGENCE")
public class Specialite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_specialite", unique = true, nullable = false)
    private Long codespecialite;

    @Column(name = "nom_specialite", nullable = false)
    @NotBlank
    private String nom;

    @Column(name = "code_groupe_specialite", nullable = false)
    private Short groupespecialite;

    public Specialite() {
    }
    
    public Specialite(Long codespecialite) {
        this.codespecialite = codespecialite;
    }

    public Specialite(
            @JsonProperty("code_specialite") final Long codespecialite,
            @JsonProperty("nom_specialite") final String nom,
            @JsonProperty("code_groupe_specialite") final Short groupespecialite) {
        this.codespecialite = codespecialite;
        this.nom = nom;
        this.groupespecialite = groupespecialite;
    }

    public Long getCodespecialite() {
        return codespecialite;
    }

    public String getNom() {
        return nom;
    }

    public Short getGroupeSpecialite() {
        return groupespecialite;
    }

    public void setCodespecialite(Long codespecialite) {
        this.codespecialite = codespecialite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setGroupeSpecialite(Short groupespecialite) {
        this.groupespecialite = groupespecialite;
    }
}
