package com.gestionObras.service;

import com.gestionObras.dao.Solicitud_RegistroDAO;
import com.gestionObras.entities.Solicitud_Registro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Solicitud_RegistroServiceImpl implements Solicitud_RegistroService{
    
    @Autowired
    private Solicitud_RegistroDAO solicitud;

    @Override
    public List<Solicitud_Registro> listarSolicitudes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardarSolicitud(Solicitud_Registro solicitud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarSolicitud(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
