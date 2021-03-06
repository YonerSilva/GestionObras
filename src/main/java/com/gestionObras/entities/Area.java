package com.gestionObras.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "area")
public class Area implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_area;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String descripcion;
    
    @NotEmpty
    private String ubicacion;
    
    @OneToMany(targetEntity=Punto.class, cascade=CascadeType.ALL)
    @JoinColumn(name="id_area",referencedColumnName="id_area")
    private List<Punto> puntosA;
    
    @PostRemove
    public void eliminarPuntos(){
        puntosA.forEach(punto -> punto=null);
    }
}
