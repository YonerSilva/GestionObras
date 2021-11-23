package com.gestionObras.service;

import com.gestionObras.entities.SolicitudRegistro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestionObras.dao.SolicitudRegistroDAO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudRegistroServiceImpl implements SolicitudRegistroService{
    
    @Autowired
    private SolicitudRegistroDAO solicitudDao;

    @Override
    public List<SolicitudRegistro> listarSolicitudes() {
        return solicitudDao.findAll();
    }

    @Override
    public void guardarSolicitud(SolicitudRegistro solicitud) {
        solicitudDao.save(solicitud);
    }

    @Override
    public void eliminarSolicitud(long id) {
        solicitudDao.deleteById(id);
    }

    @Override
    @Transactional
    public SolicitudRegistro buscarSolicitudRegistro(String username) {
        return solicitudDao.findByUsername(username);
    }
    
}
