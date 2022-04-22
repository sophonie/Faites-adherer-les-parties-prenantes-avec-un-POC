package fr.sofina.application.evenement;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.sofina.application.specialite.Specialite;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TBEVENEMENT", schema = "GESTION_URGENCE")
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_evenement", unique = true, nullable = false)
    private Long code_evenement;

    @Column(name = "date_evenement", nullable = false)
    @NotBlank
    private LocalDate date_evenement;

    @JoinColumn(name = "code_specialite")
    @OneToOne
    private Specialite code_specialite;

    public Evenement() {
    }

    
    public Evenement(
            @JsonProperty("code_evenement") Long code_evenement,
            @JsonProperty("type_evenement") String type_evenement,
            @JsonProperty("date_evenement") LocalDate date_evenement,
            @JsonProperty("code_specialite") Specialite code_specialite) {
        this.code_evenement = code_evenement;
        this.date_evenement = date_evenement;
        this.code_specialite = code_specialite;
    }
    
    public Long getCode_evenement() {
        return code_evenement;
    }

    public LocalDate getDate_evenement() {
        return date_evenement;
    }

    public Specialite getCode_groupe_specialite() {
        return code_specialite;
    }

    public void setCode_evenement(Long code_evenement) {
        this.code_evenement = code_evenement;
    }

    public void setDate_evenement(LocalDate date_evenement) {
        this.date_evenement = date_evenement;
    }

    public void setCode_Specialite(Specialite code_specialite) {
        this.code_specialite = code_specialite;
    }
}
