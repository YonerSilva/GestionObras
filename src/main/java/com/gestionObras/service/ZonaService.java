package com.gestionObras.service;

import com.gestionObras.entities.Zona;
import java.util.List;

public interface ZonaService {
    public List<Zona> listarZonas();
    public void guardar (Zona zona);
    public void eliminar (Zona zona);
    public Zona encontrarZona (Zona zona);
  
}
