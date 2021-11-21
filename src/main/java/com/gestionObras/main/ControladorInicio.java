package com.gestionObras.main;

import com.gestionObras.dao.UsuarioDAO;
import com.gestionObras.entities.Rol;
import com.gestionObras.entities.Usuario;
import com.gestionObras.service.UsuarioService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private 

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        return userAuthentication(user);
    }

    private String userAuthentication(@AuthenticationPrincipal User user) {
        Usuario usuario = usuarioService.encontrarUsuario(user.getUsername());
        for (Rol rol : usuario.getRoles()) {
            if (rol.getTipo_rol().equals("ADMINISTRADOR")) {
                return "/html/Sis_Administrador_Prin";
            }
            if (rol.getTipo_rol().equals("SUPERVISOR")){
                return "/html/Sis_Supervisor_Prin";
            } 
            if (rol.getTipo_rol().equals("INTERVENTOR")){
                return "/html/Sis_Interventor_Prin";
            } 
        }
        return "/login";    
    }

    @PostMapping("/registrarse")
    public String registrarUsuario(@Valid Usuario usuario, Errors errores) {
        if (errores.hasErrors()) {
            return "/html/Registrar_User";
        }
        
        return "/login";
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
