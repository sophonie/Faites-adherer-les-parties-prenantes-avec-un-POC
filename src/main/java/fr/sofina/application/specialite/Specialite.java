package fr.sofina.application.specialite;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.sofina.application.groupespecialite.GroupeSpecialite;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TBSPECIALITE", schema = "GESTION_URGENCE")
public class Specialite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_specialite", unique = true, nullable = false)
    private Long code_specialite;

    @Column(name = "nom_specialite", nullable = false)
    @NotBlank
    private String nom;

    @JoinColumn(name = "code_groupe_specialite")
    @ManyToOne
    private GroupeSpecialite groupe_specialite;

    public Specialite() {
    }

    public Specialite(
            @JsonProperty("code_specialite") final Long codespecialite,
            @JsonProperty("nom_specialite") final String nom_specialite,
            @JsonProperty("code_groupe_specialite") final GroupeSpecialite groupe_specialite) {
        this.code_specialite = codespecialite;
        this.nom = nom_specialite;
        this.groupe_specialite = groupe_specialite;
    }

    public Long getCodeSpecialite() {
        return code_specialite;
    }

    public String getNom() {
        return nom;
    }

    public GroupeSpecialite getGroupeSpecialite() {
        return groupe_specialite;
    }

    public void setCodeSpecialite(Long code_specialite) {
        this.code_specialite = code_specialite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setGroupeSpecialite(GroupeSpecialite code_groupe_specialite) {
        this.groupe_specialite = code_groupe_specialite;
    }
}
