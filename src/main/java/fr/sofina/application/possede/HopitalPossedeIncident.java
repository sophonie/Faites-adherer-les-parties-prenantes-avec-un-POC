package fr.sofina.application.possede;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table(name = "TBHOPITAL_POSSEDE_INCIDENT", schema = "GESTION_URGENCE")
public class HopitalPossedeIncident {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private final Long id;

    @ManyToOne
    @JoinColumn(name = "code_hopital", nullable = false)
    @NotBlank
    private final Long code_hopital;

    @ManyToOne
    @JoinColumn(name = "code_incident", unique = true, nullable = false)
    @NotBlank
    private final Long code_incident;

    public HopitalPossedeIncident(Long id, Long code_hopital, Long code_incident) {
        this.id = id;
        this.code_hopital = code_hopital;
        this.code_incident = code_incident;
    }

    public Long getId() {
        return id;
    }

    public Long getCode_hopital() {
        return code_hopital;
    }

    public Long getCode_incident() {
        return code_incident;
    }
    
}
