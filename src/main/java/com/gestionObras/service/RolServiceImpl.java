package com.gestionObras.service;

import com.gestionObras.dao.RolDAO;
import com.gestionObras.entities.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService{
    
    @Autowired
    private RolDAO rolDao;

    @Override
    public void agregarRol(Rol rol) {
        rolDao.save(rol);
    }

    @Override
    public void eliminarRol(long id) {
        rolDao.deleteById(id);
    }
    
}
