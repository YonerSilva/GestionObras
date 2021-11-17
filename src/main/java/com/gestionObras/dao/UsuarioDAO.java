package com.gestionObras.dao;

import com.gestionObras.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<UsuarioDTO,Long>{
    
    UsuarioDTO findByUsername(String username);
}
