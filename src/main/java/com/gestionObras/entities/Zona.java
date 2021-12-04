/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestionObras.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "zona")
public class Zona implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_zona;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String descripcion;
    
    @NotEmpty
    private String ubicacion;
    
    @OneToMany(targetEntity=Punto.class, cascade=CascadeType.ALL)
    @JoinColumn(name="id_zona",referencedColumnName="id_zona")
    private List<Punto> puntosZ;    
    
}
