package com.gestionObras.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "insumo")
@Data
public class Insumo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_insumo;
    
    private String descripcion;
    
    private double precio;
    
    @ManyToOne(targetEntity = Punto.class)
    @JoinColumn(name = "id_punto")
    private Punto punto;
    
    @ManyToOne(targetEntity = Pedido.class)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
}
