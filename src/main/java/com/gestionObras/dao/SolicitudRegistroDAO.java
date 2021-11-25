package com.gestionObras.dao;

import com.gestionObras.entities.SolicitudRegistro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRegistroDAO extends JpaRepository<SolicitudRegistro,Long>{
    SolicitudRegistro findByUsername(String username);
}
