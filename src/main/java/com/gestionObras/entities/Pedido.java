package com.gestionObras.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostRemove;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pedido")
@Data
public class Pedido implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pedido;
    
    private String nombre;
    
    private int tipo_pedido;
    
    private String estado;
    
    private Date fecha_carga;
    
    private Date fecha_aprobacion;
    
    private double total_pedido;
    
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_supervisor")
    private Usuario supervisor;
    
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_interventor")
    private Usuario interventor;
    
    @OneToMany(targetEntity = Insumo.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    private List<Insumo> insumos;
    
    @PostRemove
    public void eliminarNulos(){
        insumos.forEach(insumo -> insumo=null);
    }
    
    @Override
    public String toString(){
        String tipo = "";
        switch(tipo_pedido){
            case 1:
                tipo = "REGULAR";
                break;
            case 2:
                tipo = "MAQUINARIA EXPRESS";
                break;
            case 3:
                tipo = "KIT MENSUAL";
                break;
            case 4:
                tipo = "KIT TRIMESTRAL";
                break;
            case 5:
                tipo = "PEDIDOS ADICIONALES - 1";
                break;
            case 6:
                tipo = "PEDIDOS ADICIONALES - 2";
                break;
        }
        return tipo;
    }
}
