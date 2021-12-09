package com.gestionObras.service;

import com.gestionObras.entities.Pedido;
import java.sql.SQLException;
import java.util.List;

public interface PedidoService {
    
    public void guardarPedido(Pedido pedido)throws SQLException;
    
    public void eliminarPedido(Pedido pedido);
    
    public List<Pedido> listarPedido(Pedido pedido);
        
    public Pedido findById(long id_pedido);
}
