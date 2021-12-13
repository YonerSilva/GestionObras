package com.gestionObras.service;

import com.gestionObras.entities.Pedido;
import java.sql.SQLException;
import java.util.List;

public interface PedidoService {
    
    public Pedido guardarPedido(Pedido pedido)throws SQLException;
    
    public void eliminarPedido(Pedido pedido);
    
    public List<Pedido> listarPedido();
        
    public Pedido encontrarPedido(Pedido pedido);
}
