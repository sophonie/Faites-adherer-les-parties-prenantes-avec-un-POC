package fr.sofina.application.possede;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

// table d'association (non entité JPA)
@Table(name = "TBHOPITAL_POSSEDE_SPECIALITE", schema = "GESTION_URGENCE")
public final class HopitalPossedeSpecialite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "code_hopital", nullable = false)
    @NotBlank
    private Long code_hopital;

    @ManyToOne
    @JoinColumn(name = "code_specialite", nullable = false)
    @NotBlank
    private Long codespecialite;

    public HopitalPossedeSpecialite() {
    }

    public HopitalPossedeSpecialite(Long codespecialite) {
        this.codespecialite = codespecialite;
    }
    
    public Long getCodeHopital() {
        return code_hopital;
    }

    public Long getCodespecialite() {
        return codespecialite;
    }
}
