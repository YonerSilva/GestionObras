
package com.gestionObras.service;

import com.gestionObras.entities.Area;
import java.sql.SQLException;
import java.util.List;

public interface AreaService {
    
    public List<Area> listarAreas();
    public void guardar (Area area) throws SQLException;
    public void eliminar (Area area);
    public Area encontrarArea (Area area);
}
