package com.gestionObras.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "solicitud_registro")
public class SolicitudRegistro implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_solicitud;
    
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;
    
    @NotEmpty
    @Column(name = "apellido")
    private String apellido;
    
    @NotEmpty
    @Column(name = "foto")
    private String foto;
    
    @NotEmpty
    @Column(name = "username")
    private String username;
    
    @NotEmpty
    @Column(name = "password")
    private String password;
    
    @OneToMany(fetch = FetchType.EAGER, targetEntity = SolicitudRol.class)
    @JoinColumn(name = "id_solicitud",referencedColumnName = "id_solicitud")
    private List<SolicitudRol> solicitudRoles = new ArrayList<>();
    
    
}
