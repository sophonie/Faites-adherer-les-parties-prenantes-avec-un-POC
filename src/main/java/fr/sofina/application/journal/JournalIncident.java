package fr.sofina.application.journal;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TBJOURNAL_INCIDENT", schema = "GESTION_URGENCE")
public class JournalIncident implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_incident", unique = true, nullable = false)    
    @JoinColumn(name = "code_incident")
    private Long code_incident;   

    @Column(name = "nom_patient", nullable = false)
    private String nom;

    @Column(name = "prenom_patient", nullable = false)
    private String prenom;

    @Column(name = "datenaissance_patient", nullable = false)
    private LocalDate datenaissance;

    @Column(name = "genre_patient", nullable = false)
    private String genre;

    @Column(name = "no_ss_patient", nullable = false)
    private Long no_ss;

    @Column(name = "nom_hopital", nullable = false)
    private String nom_hopital;

    public JournalIncident() {
    }

    public void setCode_incident(Long code_incident) {
        this.code_incident = code_incident;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDatenaissance(LocalDate datenaissance) {
        this.datenaissance = datenaissance;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNo_ss(Long no_ss) {
        this.no_ss = no_ss;
    }

    public void setNom_hopital(String nom_hopital) {
        this.nom_hopital = nom_hopital;
    }
}
