package com.gestionObras.dao;

import com.gestionObras.entities.Punto;
import org.springframework.data.repository.CrudRepository;


public interface PuntoDAO extends CrudRepository<Punto, Long> {
    
}
