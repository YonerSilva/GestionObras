/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestionObras.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "punto")
public class Punto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_punto;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String descripcion;
    
    @ManyToOne(targetEntity = Area.class, cascade = CascadeType.ALL)
    @JoinColumn(name="id_area")
    private Area area;
    
    @ManyToOne(targetEntity = Zona.class)
    @JoinColumn(name="id_zona")
    private Zona zona;
    
    public Punto(long id_punto, String nombre, String descripcion) {
        super();
        this.id_punto = id_punto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Punto() {
        super();
    }
    
    @Override
    public String toString(){
        if(zona==null)
            return "Area: "+area.getNombre()+" - Zona: Vacio";
        return "Area: "+area.getNombre()+" - Zona: "+zona.getNombre();
    }

}
