package com.gestionObras.service;

import com.gestionObras.entities.Zona;
import java.sql.SQLException;
import java.util.List;

public interface ZonaService {
    public List<Zona> listarZonas();
    public void guardar (Zona zona) throws SQLException;
    public void eliminar (Zona zona);
    public Zona encontrarZona (Zona zona);
  
}
