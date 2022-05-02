package fr.sofina.application.journal;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBJOURNAL_INCIDENT", schema = "GESTION_URGENCE")
public class JournalIncident implements Serializable {

    private static final long serialVersionUID = 1L;
    // ne pas mettre les annotations @GeneratedValue, @JoinColums
    @Id
    @Column(name = "code_incident", unique = true, nullable = false)
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

    @Column(name = "code_hopital", nullable = false)
    private Long code_hopital;

    public JournalIncident() {
    }

    public void setCodeIncident(Long code_incident) {
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

    public void setNO_SS(Long no_ss) {
        this.no_ss = no_ss;
    }

    public void setCodeHopital(Long code_hopital) {
        this.code_hopital = code_hopital;
    }

    public Long getCode_incident() {
        return code_incident;
    }

    public Long getCodeHopital() {
        return code_hopital;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDatenaissance() {
        return datenaissance;
    }

    public String getGenre() {
        return genre;
    }

    public Long getNO_SS() {
        return no_ss;
    }

}
