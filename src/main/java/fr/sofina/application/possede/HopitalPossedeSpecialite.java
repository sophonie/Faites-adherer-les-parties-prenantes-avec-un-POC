package fr.sofina.possede;

import fr.sofina.hopital.Hopital;
import fr.sofina.specialite.Specialite;
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
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "SMALLSERIAL")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="CODE_HOPITAL", nullable = false, columnDefinition = "INT2")    
    @NotBlank
    private Hopital codehopital;
    
    @ManyToOne
    @JoinColumn(name="CODE_SPECIALITE", nullable = false, columnDefinition = "INT2")    
    @NotBlank
    private Specialite codespecialite;
    
    
    
}
