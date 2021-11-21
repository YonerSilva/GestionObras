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
    private Long id_rol;
    
    @NotEmpty
    private String tipo_rol;
    
    @Override
    public String toString(){
        String cadena = "";
        for (int i = 5; i < this.tipo_rol.length(); i++) {
            cadena += this.tipo_rol.charAt(i);
        }
        return cadena;
    }
}
