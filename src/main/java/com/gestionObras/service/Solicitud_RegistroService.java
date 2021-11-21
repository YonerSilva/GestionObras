package com.gestionObras.service;

import com.gestionObras.entities.Solicitud_Registro;
import java.util.List;

public interface Solicitud_RegistroService{
    
    public List<Solicitud_Registro> listarSolicitudes();
    
    public void guardarSolicitud(Solicitud_Registro solicitud);
    
    public void eliminarSolicitud(long id);
    
}
