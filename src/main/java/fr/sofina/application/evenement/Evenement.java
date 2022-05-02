package fr.sofina.application.evenement;

import com.fasterxml.jackson.annotation.JsonFormat;
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

@Entity
@Table(name = "TBEVENEMENT", schema = "GESTION_URGENCE")
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "code_evenement", unique = true, nullable = false)
    private Long code_evenement;

    @Column(name = "date_evenement") 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date_evenement;

    @JoinColumn(name = "code_specialite")
    @OneToOne
    private Specialite specialite;

    public Evenement() {
    }

    public Evenement(
            @JsonProperty("code_evenement") Long code_evenement,            
            @JsonProperty("specialite") Specialite specialite) {        
        this.code_evenement = code_evenement;        
        this.specialite = specialite;
    }
    
    public Long getCodeEvenement() {
        return code_evenement;
    }

    public LocalDate getDateEvenement() {
        return date_evenement;
    }

    public Specialite getCodeGroupeSpecialite() {
        return specialite;
    }

    public Specialite getSpecialite() {
        return specialite;
    }
    
    

    public void setCodeEvenement(Long code_evenement) {
        this.code_evenement = code_evenement;
    }

    public void setDateEvenement(LocalDate date_evenement) {
        this.date_evenement = date_evenement;
    }

    public void setCodeSpecialite(Specialite code_specialite) {
        this.specialite = code_specialite;
    }
}
