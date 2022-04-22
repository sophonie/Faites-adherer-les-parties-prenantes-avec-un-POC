package fr.sofina.application.groupespecialite;

import fr.sofina.application.specialite.Specialite;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TBGROUPE_SPECIALITE", schema = "GESTION_URGENCE")
public class GroupeSpecialite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_groupe_specialite", unique = true, nullable = false)
    private Long code_groupe_specialite;

    @Column(name = "nom_groupe_specialite", nullable = false)
    @NotBlank
    private String nom;
    
    @OneToMany(mappedBy = "code_groupe_specialite")
    private List<Specialite> specialites;

    public GroupeSpecialite() {
    }

    public GroupeSpecialite(Long code_groupe_specialite) {
        this.code_groupe_specialite = code_groupe_specialite;
    }
    
    public Long getCodeGroupeSpecialite() {
        return code_groupe_specialite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodeGroupeSpecialite(Long code_groupe_specialite) {
        this.code_groupe_specialite = code_groupe_specialite;
    }

    public List<Specialite> getSpecialites() {
        return specialites;
    }
}
