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
    @JoinColumn(name = "id_solicitud")
    private SolicitudRegistro solicitudRegistro;

    public SolicitudRol(long id_rol_solicitud, String tipo_rol) {
        super();
        this.id_rol_solicitud = id_rol_solicitud;
        this.tipo_rol = tipo_rol;
    }

    public SolicitudRol() {
        super();
    }
    
    @Override
    public String toString(){
        String cadena = "";
        for (int i = 5; i < this.tipo_rol.length(); i++) {
            cadena += this.tipo_rol.charAt(i);
        }
        return cadena;
    }
}
