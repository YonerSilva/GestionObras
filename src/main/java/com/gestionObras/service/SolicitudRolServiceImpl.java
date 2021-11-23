package com.gestionObras.service;

import com.gestionObras.entities.Rol;
import com.gestionObras.entities.SolicitudRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestionObras.dao.SolicitudRolDAO;

@Service
public class SolicitudRolServiceImpl implements SolicitudRolService{
    
    @Autowired
    private SolicitudRolDAO solicitud_rolDao;

    @Override
    public void agregarRol(SolicitudRol solicitud_rol) {
        solicitud_rolDao.save(solicitud_rol);
    }

    @Override
    public void eliminarRol(long id) {
        solicitud_rolDao.deleteById(id);
    }


}
