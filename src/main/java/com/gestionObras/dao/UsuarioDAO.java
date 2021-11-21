package com.gestionObras.dao;

import com.gestionObras.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario,Long>{
    
    Usuario findByUsername(String username);
}
