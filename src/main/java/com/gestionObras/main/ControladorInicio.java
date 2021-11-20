/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestionObras.main;

import com.gestionObras.dao.UsuarioDAO;
import com.gestionObras.dto.UsuarioDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ACER
 */
@Controller
@Slf4j
public class ControladorInicio {
    
    private UsuarioDAO usuarioDao;
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        
        System.out.println(user.getUsername());
        //return "/html/Sis_Administrador_Prin";
        return userAuthentication(user);
    }
    
    private String userAuthentication(@AuthenticationPrincipal User user){
        if(user.getUsername().equals("user")){
            System.out.println("Hola1");
            return "/html/Sis_Supervisor_Prin";
        }else{
            if(user.getUsername().equals("admin")){
                System.out.println("hola2");
                return "/html/Sis_Administrador_Prin";
            }
        }
        return "";
    }
    
    @PostMapping("/registrarse")
    public String registrarUsuario(@Valid UsuarioDTO usuario,Errors errores){
        if (errores.hasErrors()) {
            return "modificar";
        }
        usuarioDao.save(new UsuarioDTO())
        return "/html/Registrar_User";
    }
    
    private File guardarfoto(String ruta) {
    
        FileInputStream fis = null;
        try {
            File file = new File(ruta);
            fis = new FileInputStream(file);
            return file;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
