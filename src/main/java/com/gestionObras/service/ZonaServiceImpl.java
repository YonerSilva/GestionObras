package com.gestionObras.service;

import com.gestionObras.dao.ZonaDAO;
import com.gestionObras.entities.Zona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaServiceImpl implements ZonaService{
    
    @Autowired
    private ZonaDAO zonaDao;

    @Override
    @Transactional(readOnly=true)
    public List<Zona> listarZonas() {
        return (List<Zona>) zonaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Zona zona) {
        zonaDao.save(zona);
    }

    @Override
    @Transactional
    public void eliminar(Zona zona) {
        zonaDao.delete(zona);
    }

    @Override
    @Transactional(readOnly=true)
    public Zona encontrarZona(Zona zona) {
        return zonaDao.findById(zona.getId_zona()).orElse(null);
    }
    
}
