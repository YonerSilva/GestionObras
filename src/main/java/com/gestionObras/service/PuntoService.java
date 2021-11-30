package com.gestionObras.service;

import com.gestionObras.entities.Punto;
import java.util.List;

public interface PuntoService {
    public List<Punto> listarPuntos();
    public void guardar (Punto punto);
    public void eliminar (Punto punto);
    public Punto encontrarPunto (Punto punto);
  
}
