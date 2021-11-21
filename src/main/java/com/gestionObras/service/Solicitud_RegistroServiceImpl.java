package com.gestionObras.service;

import com.gestionObras.dao.Solicitud_RegistroDAO;
import com.gestionObras.entities.Solicitud_Registro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Solicitud_RegistroServiceImpl implements Solicitud_RegistroService{
    
    @Autowired
    private Solicitud_RegistroDAO solicitudDao;

    @Override
    public List<Solicitud_Registro> listarSolicitudes() {
        return solicitudDao.findAll();
    }

    @Override
    public void guardarSolicitud(Solicitud_Registro solicitud) {
        solicitudDao.save(solicitud);
    }

    @Override
    public void eliminarSolicitud(long id) {
        solicitudDao.deleteById(id);
    }
    
}
