package com.gestionObras.dto;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "usuario")
public class UsuarioDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    @OneToMany
    @JoinColumn(name = "id_usuario")
    private List<RolDTO> roles;
}
