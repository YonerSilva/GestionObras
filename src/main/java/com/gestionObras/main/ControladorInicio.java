package com.gestionObras.main;

import com.gestionObras.dao.UsuarioDAO;
import com.gestionObras.entities.Rol;
import com.gestionObras.entities.Solicitud_Registro;
import com.gestionObras.entities.Usuario;
import com.gestionObras.service.Solicitud_RegistroServiceImpl;
import com.gestionObras.service.UsuarioService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private Solicitud_RegistroServiceImpl solicitudImpl;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        Usuario usuario = usuarioService.encontrarUsuario(user.getUsername());
        model.addAttribute("usuario", usuario);
        return userAuthentication(user);
    }

    private String userAuthentication(User user) {
        Collection<GrantedAuthority> roles =  user.getAuthorities();
        
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_ADMINISTRADOR")) {
                return "/html/Sis_Administrador_Prin";
            }
            if (role.getAuthority().equals("ROLE_SUPERVISOR")){
                return "/html/Sis_Supervisor_Prin";
            } 
            if (role.getAuthority().equals("ROLE_INTERVENTOR")){
                return "/html/Sis_Interventor_Prin";
            } 
        }
        return "/login";    
    }

    @GetMapping("/sign_in")
    public String registrar_User(Usuario usuario) {
        return "/html/Registrar_User";
    }
    
    @PostMapping("/Guardar_Solicitud")
    public String guardar(@Valid Usuario usuario, Errors errores) {
        if (errores.hasErrors()) {
            return "Registrar_User";
        }
        Solicitud_Registro solicitud = new Solicitud_Registro();
        solicitud.setNombre(usuario.getNombre());
        solicitud.setApellido(usuario.getApellido());
        solicitud.setFoto(usuario.getFoto());
        solicitud.setUsername(usuario.getUsername());
        solicitud.setPassword(usuario.getPassword());
        solicitud.setRoles(usuario.getRoles());
        solicitudImpl.guardarSolicitud(solicitud);
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