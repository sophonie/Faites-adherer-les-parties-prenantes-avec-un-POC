package fr.sofina.application.possede;

import fr.sofina.application.hopital.Hopital;
import fr.sofina.application.incident.Incident;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBHOPITAL_POSSEDE_INCIDENT", schema = "GESTION_URGENCE")
public class HopitalPossedeIncident implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "possede_incident_id_seq"
    )
    @SequenceGenerator(
            name = "possede_incident_id_seq",
            sequenceName = "seq_possede_incident",
            allocationSize = 1
    )
    @Column(
            name = "id",
            unique = true,
            updatable = false,
            nullable = false
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "code_hopital", unique = true, nullable = false)
    private Hopital hopital;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "code_incident", unique = true, nullable = false)
    private Incident incident;

    public HopitalPossedeIncident() {
    }

    public HopitalPossedeIncident(Long id, Hopital hopital, Incident incident) {
        this.id = id;
        this.hopital = hopital;
        this.incident = incident;
    }

    public Long getId() {
        return id;
    }

    public Hopital getHopital() {
        return hopital;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setHopital(Hopital hopital) {
        this.hopital = hopital;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

}
