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
    public void guardarPedido(Pedido pedido) throws SQLException {
        pedidoDao.save(pedido);
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
    @Transactional(readOnly = true)
    public Pedido encontrarPedido(Pedido pedido) {
        return pedidoDao.findById(pedido.getId_pedido()).orElse(null);
    }
    
}
