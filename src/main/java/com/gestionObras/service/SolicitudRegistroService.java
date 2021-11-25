package com.gestionObras.service;

import com.gestionObras.entities.SolicitudRegistro;
import java.util.List;

public interface SolicitudRegistroService{
    
    public List<SolicitudRegistro> listarSolicitudes();
    
    public void guardarSolicitud(SolicitudRegistro solicitud);
    
    public void eliminarSolicitud(long id);
    
    public SolicitudRegistro buscarSolicitudRegistro(String username);
}
