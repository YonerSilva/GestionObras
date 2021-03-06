package com.gestionObras.service;

import com.gestionObras.dao.PedidoDAO;
import com.gestionObras.entities.Pedido;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoDAO pedidoDao;
    
    @Override
    @Transactional
    public Pedido guardarPedido(Pedido pedido) throws SQLException {
        return pedidoDao.save(pedido);
    }

    @Override
    @Transactional
    public void eliminarPedido(Pedido pedido) {
        pedidoDao.delete(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listarPedido() {
        return (List<Pedido>) pedidoDao.findAll();
    }

    @Override
    public Pedido encontrarPedido(Pedido pedido) throws SQLException{
        long id = pedido.getId_pedido();
        pedido = pedidoDao.findById(id).orElse(null);
        if(pedido!=null)
            return pedido;
        return null;
    }
    
}
