package com.gestionObras.service;

import com.gestionObras.dao.PuntoDAO;
import com.gestionObras.entities.Punto;
import com.gestionObras.entities.Zona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PuntoServiceImpl implements PuntoService{
    
    @Autowired
    private PuntoDAO puntoDao;

    @Override
    @Transactional(readOnly=true)
    public List<Punto> listarPuntos() {
        return (List<Punto>) puntoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Punto punto) {
        puntoDao.save(punto);
    }

    @Override
    @Transactional
    public void eliminar(Punto punto) {
        puntoDao.delete(punto);
    }

    @Override
    @Transactional(readOnly=true)
    public Punto encontrarPunto(Punto punto) {
        return puntoDao.findById(punto.getId_punto()).orElse(null);
    }  
}
