package com.gestionObras.main;

import com.gestionObras.entities.SolicitudRegistro;
import com.gestionObras.entities.SolicitudRol;
import com.gestionObras.entities.Usuario;
import com.gestionObras.service.SolicitudRolServiceImpl;
import com.gestionObras.service.SolicitudRegistroServiceImpl;
import com.gestionObras.service.UsuarioService;
import com.gestionObras.util.EncriptarPassword;
import com.gestionObras.util.Imagen;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SolicitudRegistroServiceImpl solicitudImpl;

    @Autowired
    private SolicitudRolServiceImpl solicitud_rolServiceImpl;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user, HttpSession session) {
        Usuario usuario = usuarioService.encontrarUsuario(user.getUsername());
        session.setAttribute("usuario", usuario);
        model.addAttribute("usuario", usuario);
        return userAuthentication(user);
    }

    private String userAuthentication(User user) {
        Collection<GrantedAuthority> roles = user.getAuthorities();

        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_ADMINISTRADOR")) {
                return "/html/Sis_Administrador_Prin";
            }
            if (role.getAuthority().equals("ROLE_SUPERVISOR")) {
                return "/html/Sis_Supervisor_Prin";
            }
            if (role.getAuthority().equals("ROLE_INTERVENTOR")) {
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
    public String guardar(Usuario usuario, HttpServletRequest req, HttpServletResponse res) {
        EncriptarPassword encriptar = new EncriptarPassword();

        SolicitudRegistro solicitud = new SolicitudRegistro();
        solicitud.setNombre(usuario.getNombre());
        solicitud.setApellido(usuario.getApellido());
        solicitud.setFoto(guardarfoto(req, res));
        solicitud.setUsername(usuario.getUsername());
        solicitud.setPassword(encriptar.encriptarPassword(usuario.getPassword()));

        solicitudImpl.guardarSolicitud(solicitud);
        solicitud = solicitudImpl.buscarSolicitudRegistro(usuario.getUsername());
        if (solicitud != null) {
            SolicitudRol solicitud_rol = new SolicitudRol();
            //solicitud_rol.setId_solicitud(solicitud.getId_solicitud());
            String checkAdmin = req.getParameter("check_admin");
            String checkSuper = req.getParameter("check_super");
            String checkInter = req.getParameter("check_inter");
            if (checkAdmin.equals("1")) {
                solicitud_rol.setTipo_rol("ROLE_ADMINISTRADOR");
                solicitud_rolServiceImpl.agregarRol(solicitud_rol);
            }
            if (checkAdmin.equals("2")) {
                solicitud_rol.setTipo_rol("ROLE_SUPERVISOR");
                solicitud_rolServiceImpl.agregarRol(solicitud_rol);
            }
            if (checkAdmin.equals("3")) {
                solicitud_rol.setTipo_rol("ROLE_INTERVENTOR");
                solicitud_rolServiceImpl.agregarRol(solicitud_rol);
            }
        }
        return "/login";
    }

    private String guardarfoto(HttpServletRequest req, HttpServletResponse res) {
        String photo = null;
        Imagen imagen = new Imagen();
        try {
            Part part = req.getPart("file");

            if (part == null) {
                System.out.println("No ha seleccionado un archivo");
                return null;
            }

            if (imagen.isExtension(part.getSubmittedFileName(), imagen.getExtens())) {
                photo = imagen.saveFile(part, imagen.getUploads());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return photo;
    }

    @GetMapping("/Olvide_Contrasenia")
    public String olvideContrasenia() {
        return "/html/Olvide_Contrasenia";
    }
}
