package com.gestionObras.main;

import com.gestionObras.entities.SolicitudRegistro;
import com.gestionObras.entities.SolicitudRol;
import com.gestionObras.entities.Usuario;
import com.gestionObras.service.SolicitudRegistroServiceImpl;
import com.gestionObras.service.UsuarioService;
import com.gestionObras.util.EncriptarPassword;
import com.gestionObras.util.Imagen;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SolicitudRegistroServiceImpl solicitudImpl;

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
        try {
            EncriptarPassword encriptar = new EncriptarPassword();
            SolicitudRegistro solicitud = new SolicitudRegistro();
            solicitud.setNombre(usuario.getNombre());
            solicitud.setApellido(usuario.getApellido());
            solicitud.setUsername(usuario.getUsername());
            String [] nombres = solicitud.getNombre().split(" ");
            String fileName = String.valueOf(solicitud.hash(solicitud.getUsername()))+'-'+nombres[0];
            Imagen imagen = new Imagen();
            solicitud.setFoto(guardarfoto(req, res,fileName,imagen));
            solicitud.setPassword(encriptar.encriptarPassword(usuario.getPassword()));

            String checkAdmin = req.getParameter("check_admin");
            String checkSuper = req.getParameter("check_super");
            String checkInter = req.getParameter("check_inter");
            List<SolicitudRol> roles = new ArrayList<>();
            SolicitudRol solicitud_rol;
            if (checkAdmin != null) {
                if (checkAdmin.equals("1")) {
                    solicitud_rol = new SolicitudRol();
                    solicitud_rol.setTipo_rol("ROLE_ADMINISTRADOR");
                    roles.add(solicitud_rol);
                }
            }
            if (checkSuper != null) {
                if (checkSuper.equals("2")) {
                    solicitud_rol = new SolicitudRol();
                    solicitud_rol.setTipo_rol("ROLE_SUPERVISOR");
                    roles.add(solicitud_rol);
                }
            }
            if (checkInter != null) {
                if (checkInter.equals("3")) {
                    solicitud_rol = new SolicitudRol();
                    solicitud_rol.setTipo_rol("ROLE_INTERVENTOR");
                    roles.add(solicitud_rol);
                }
            }
            solicitud.setSolicitudRoles(roles);
            solicitudImpl.guardarSolicitud(solicitud);
            imagen.saveFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return "/login";
    }

    private String guardarfoto(HttpServletRequest req, HttpServletResponse res, String fileName, Imagen imagen) {
        String photo = null;
        try {
            Part part = req.getPart("file");

            if (part == null) {
                System.out.println("No ha seleccionado un archivo");
                return null;
            }
            if (imagen.isExtension(part.getSubmittedFileName(), imagen.getExtens())) {
                photo = imagen.getRutaFoto(part, imagen.getUploads(), fileName);
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
