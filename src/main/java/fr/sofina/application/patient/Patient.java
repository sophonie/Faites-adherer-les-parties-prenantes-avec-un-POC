package fr.sofina.application.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.sofina.application.incident.Incident;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TBPATIENT", schema = "GESTION_URGENCE")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_patient", unique = true, nullable = false)
    private Long code_patient;

    @Column(name = "nom_patient", nullable = false)
    private String nom;

    @Column(name = "prenom_patient", nullable = false)
    private String prenom;

    @Column(name = "datenaissance_patient", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate datenaissance;

    @Column(name = "genre_patient", nullable = false)
    private String genre;

    @Column(name = "no_ss_patient", nullable = false)
    private Long no_ss;

    @OneToMany(mappedBy = "code_incident", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Incident> incidents;

    public Patient() {
        this.incidents = new HashSet<>();
    }

    public Patient(
            @JsonProperty("nom_patient") final String nom,
            @JsonProperty("prenom_patient") final String prenom,
            @JsonProperty("datenaissance_patient") final LocalDate datenaissance,
            @JsonProperty("genre_patient") final String genre,
            @JsonProperty("no_ss_patient") final Long no_ss) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.genre = genre;
        this.no_ss = no_ss;
        this.incidents = new HashSet<>();
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

    public void setNo_SS(Long no_ss) {
        this.no_ss = no_ss;
    }

    public Long getCodePatient() {
        return code_patient;
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

    public Long getNo_SS() {
        return no_ss;
    }

    public Set<Incident> getIncidents() {
        return incidents;
    }

}
