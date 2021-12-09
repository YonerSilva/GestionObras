package com.gestionObras.dao;

import com.gestionObras.entities.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoDAO extends CrudRepository<Pedido,Long>{
    
}
