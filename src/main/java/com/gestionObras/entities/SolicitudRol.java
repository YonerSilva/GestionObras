package com.gestionObras.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="solicitud_rol")
public class SolicitudRol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rol_solicitud;
    
    @NotEmpty
    private String tipo_rol;
        
    @ManyToOne
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    private SolicitudRegistro solicitudRegistro;
    
}
