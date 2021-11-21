package com.gestionObras.service;

import com.gestionObras.dao.AreaDAO;
import com.gestionObras.entities.Area;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AreaServiceImpl  implements AreaService{

    @Autowired
    private AreaDAO areaDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Area> listarAreas() {
        return (List<Area>) areaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Area area) {
        areaDao.save(area);
    }

    @Override
    @Transactional
    public void eliminar(Area area) {
        areaDao.delete(area);
    }

    @Override
    @Transactional(readOnly=true)
    public Area encontrarArea(Area area) {
        return areaDao.findById(area.getId_area()).orElse(null);
    }

    
    
}
