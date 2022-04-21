package fr.sofina.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TBPATIENT", schema = "GESTION_URGENCE")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_patient", unique = true, nullable = false, columnDefinition = "SMALLSERIAL")
    private int codepatient;

    @Column(name = "nom_patient", nullable = false)
    @NotBlank
    private String nom;

    @Column(name = "prenom_patient", nullable = false)
    @NotBlank
    private String prenom;

    @Column(name = "datenaissance_patient", nullable = false, columnDefinition = "DATE")
    @NotBlank
    private Date datenaissance;

    @Column(name = "genre_patient", nullable = false)
    @NotBlank
    private String genre;

    @Column(name = "no_ss_patient", nullable = false, columnDefinition = "NUMERIC")
    @NotBlank
    private int no_ss;

    public Patient() {
    }

    public Patient(
            @JsonProperty("code_patient") final int codepatient,
             @JsonProperty("nom_patient") final String nom,
             @JsonProperty("prenom_patient") final String prenom,
             @JsonProperty("datenaissance_patient") final Date datenaissance,
             @JsonProperty("genre_patient") final String genre,
             @JsonProperty("no_ss_patient") final int no_ss) {
        this.codepatient = codepatient;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.genre = genre;
        this.no_ss = no_ss;
    }

    public int getCodepatient() {
        return codepatient;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public String getGenre() {
        return genre;
    }

    public int getNo_ss() {
        return no_ss;
    }
}
