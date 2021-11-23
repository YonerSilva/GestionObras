package com.gestionObras.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="rol")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rol;
    
    @NotEmpty
    private String tipo_rol;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Rol(long id_rol, String tipo_rol) {
        super();
        this.id_rol = id_rol;
        this.tipo_rol = tipo_rol;
    }

    public Rol() {
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
