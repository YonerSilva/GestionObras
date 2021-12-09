package com.gestionObras.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    private String foto;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    @OneToMany(targetEntity=Rol.class,cascade=CascadeType.ALL)
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private List<Rol> roles;
    
    @OneToMany(targetEntity = Pedido.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_supervsior",referencedColumnName = "id_usuario")
    private List<Pedido> pedidos_super;
    
    @OneToMany(targetEntity = Pedido.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_interventor",referencedColumnName = "id_usuario")
    private List<Pedido> pedidos_inter;
}
