package com.gestionObras.main;

import com.gestionObras.entities.Area;
import com.gestionObras.entities.Punto;
import com.gestionObras.entities.Rol;
import com.gestionObras.entities.SolicitudRegistro;
import com.gestionObras.entities.SolicitudRol;
import com.gestionObras.entities.Usuario;
import com.gestionObras.entities.Zona;
import com.gestionObras.service.AreaService;
import com.gestionObras.service.PuntoServiceImpl;
import com.gestionObras.service.SolicitudRegistroServiceImpl;
import com.gestionObras.service.UsuarioService;
import com.gestionObras.service.ZonaService;
import com.gestionObras.util.EncriptarPassword;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Controlador_Admin {

    @Autowired
    private AreaService areaService;

    @Autowired
    private ZonaService zonaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SolicitudRegistroServiceImpl solicitudService;

    @Autowired
    private PuntoServiceImpl puntoService;

    @GetMapping("/Sis_Administrador_Area")
    public String Sis_Administrador_Area(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        var areas = areaService.listarAreas();
        model.addAttribute("areas", areas);
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Administrador_Area";
    }

    @GetMapping("/Sis_Agregar_Areas")
    public String Sis_Agregar_Areas(Area area, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Agregar_Areas";
    }

    @PostMapping("/guardarArea")
    public String guardarArea(Model model, Area area, RedirectAttributes atribute, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            areaService.guardar(area);
            var areas = areaService.listarAreas();
            model.addAttribute("areas", areas);
        } catch (Exception e) {
            atribute.addFlashAttribute("errores", "El área ya existe.");
            
            return "redirect:/Sis_Agregar_Areas";
        }

        return "/html/Sis_Administrador_Area";
    }

    @GetMapping("/Sis_Modificar_Areas_Mod/{id_area}")
    public String Sis_Modificar_Areas_Mod(Area area, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        area = areaService.encontrarArea(area);
        model.addAttribute("area", area);
        return "html/Sis_Modificar_Areas_Mod";
    }
    
    @PostMapping("/modificarArea")
    public String modificarArea(Model model, Area area, RedirectAttributes atribute, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            areaService.guardar(area);
            var areas = areaService.listarAreas();
            model.addAttribute("areas", areas);
        } catch (Exception e) {
            atribute.addFlashAttribute("errores", "El área ya existe.");
            return "redirect:/Sis_Modificar_Areas_Mod/"+String.valueOf(area.getId_area());
        }
        return "/html/Sis_Administrador_Area";
    }

    @GetMapping("/eliminarArea/{id_area}")
    public String eliminarArea(Area area, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        Area areaAux = areaService.encontrarArea(area);
        areaService.eliminar(areaAux);
        var areas = areaService.listarAreas();
        model.addAttribute("areas", areas);
        return "/html/Sis_Administrador_Area";
    }

    @GetMapping("/Sis_VerPuntos_Area/{id_area}")
    public String Sis_VerPuntos_Area(Area area, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        area = areaService.encontrarArea(area);
        var puntos = area.getPuntosA();
        model.addAttribute("puntos", puntos);
        model.addAttribute("usuario", usuario);
        return "/html/Sis_VerPuntos_Area";
    }

    @GetMapping("/Sis_Administrador_Zona")
    public String Sis_Administrador_Zona(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        var zonas = zonaService.listarZonas();
        model.addAttribute("zonas", zonas);
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Administrador_Zona";
    }

    @GetMapping("/Sis_Agregar_Zonas")
    public String Sis_Agregar_Zonas(Zona zona, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Agregar_Zonas";
    }

    @PostMapping("/guardarZona")
    public String guardarZona(Model model, Zona zona,RedirectAttributes atribute, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            zonaService.guardar(zona);
            var zonas = zonaService.listarZonas();
            model.addAttribute("zonas", zonas);
        } catch (Exception e) {
            atribute.addFlashAttribute("errores", "La zona ya existe.");
            return "redirect:/Sis_Agregar_Zonas";
        }
        return "/html/Sis_Administrador_Zona";
    }
    
    @PostMapping("/modificarZona")
    public String modificarZona(Model model, Zona zona, RedirectAttributes atribute, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            zonaService.guardar(zona);
            var zonas = zonaService.listarZonas();
            model.addAttribute("zonas", zonas);
        } catch (Exception e) {
            atribute.addFlashAttribute("errores", "La zona ya existe.");
            return "redirect:/Sis_Modificar_Zonas_Mod/"+String.valueOf(zona.getId_zona());
        }
        return "/html/Sis_Administrador_Zona";
    }

    @GetMapping("/Sis_Modificar_Zonas_Mod/{id_zona}")
    public String Sis_Modificar_Zonas_Mod(Zona zona, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        zona = zonaService.encontrarZona(zona);
        model.addAttribute("zona", zona);
        return "html/Sis_Modificar_Zonas_Mod";
    }

    @GetMapping("/eliminarZona/{id_zona}")
    public String eliminarZona(Zona zona, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        zonaService.eliminar(zona);
        var zonas = zonaService.listarZonas();
        model.addAttribute("zonas", zonas);
        return "/html/Sis_Administrador_Zona";
    }

    @GetMapping("/Sis_VerPuntos_Zona/{id_zona}")
    public String Sis_VerPuntos_Zona(Zona zona, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        zona = zonaService.encontrarZona(zona);
        var puntos = zona.getPuntosZ();
        model.addAttribute("puntos", puntos);
        model.addAttribute("usuario", usuario);
        return "/html/Sis_VerPuntos_Zona";
    }

    @GetMapping("/Sis_Administrador_Punto")
    public String Sis_Administrador_Punto(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        var puntos = puntoService.listarPuntos();
        model.addAttribute("puntos", puntos);
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Administrador_Punto";
    }

    @GetMapping("/Sis_Agregar_Punto")
    public String Sis_Agregar_Punto(Punto punto, RedirectAttributes errores, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        List<Area> areas = new ArrayList<>();
        areas = areaService.listarAreas();
        model.addAttribute("areas", areas);
        List<Zona> zonas = new ArrayList<>();
        zonas = zonaService.listarZonas();
        model.addAttribute("zonas", zonas);
        return "/html/Sis_Agregar_Punto";
    }

    @PostMapping("/guardarPunto")
    public String guardarPunto(Model model, Punto punto, RedirectAttributes errores, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            puntoService.guardar(punto);
            var puntos = puntoService.listarPuntos();
            model.addAttribute("puntos", puntos);

        } catch (Exception e) {
            errores.addFlashAttribute("errores", "El punto ya existe.");
            return "redirect:/Sis_Agregar_Punto";
        }
        return "/html/Sis_Administrador_Punto";
    }

    @GetMapping("/eliminarPunto/{id_punto}")
    public String eliminarPunto(Punto punto, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        puntoService.eliminar(punto);
        var puntos = puntoService.listarPuntos();
        model.addAttribute("puntos", puntos);
        return "/html/Sis_Administrador_Punto";
    }

    @GetMapping("/Sis_Modificar_Puntos_Mod/{id_punto}")
    public String Sis_Modificar_Puntos_Mod(Punto punto, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        List<Area> areas = new ArrayList<>();
        areas = areaService.listarAreas();
        model.addAttribute("areas", areas);
        List<Zona> zonas = new ArrayList<>();
        zonas = zonaService.listarZonas();
        model.addAttribute("zonas", zonas);
        punto = puntoService.encontrarPunto(punto);
        model.addAttribute("punto", punto);
        return "html/Sis_Modificar_Puntos_Mod";
    }
    
    @PostMapping("/modificarPunto")
    public String modificarPunto(Model model, Punto punto, RedirectAttributes errores, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            puntoService.guardar(punto);
            var puntos = puntoService.listarPuntos();
            model.addAttribute("puntos", puntos);

        } catch (Exception e) {
            errores.addFlashAttribute("errores", "El punto ya existe.");
            return "redirect:/Sis_Modificar_Puntos_Mod/"+String.valueOf(punto.getId_punto());
        }
        return "/html/Sis_Administrador_Punto";
    }

    @GetMapping("/Sis_Administrador_GesReg")
    public String Sis_Administrador_GesReg(Model model, HttpSession session) {
        List<SolicitudRegistro> solicitudes = solicitudService.listarSolicitudes();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        model.addAttribute("solicitudes", solicitudes);
        return "/html/Sis_Administrador_GesReg";
    }

    @GetMapping("/aceptarSolicitud/{id_solicitud}")
    public String aceptarSolicitud(SolicitudRegistro solicitud, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        solicitud = solicitudService.findById(solicitud.getId_solicitud());
        Usuario nuevoUser = new Usuario();
        nuevoUser.setNombre(solicitud.getNombre());
        nuevoUser.setApellido(solicitud.getApellido());
        nuevoUser.setFoto(solicitud.getFoto());
        nuevoUser.setUsername(solicitud.getUsername());
        nuevoUser.setPassword(solicitud.getPassword());
        List<Rol> roles = new ArrayList<>();
        for (SolicitudRol aux : solicitud.getSolicitudRoles()) {
            roles.add(new Rol(aux.getTipo_rol()));
        }
        nuevoUser.setRoles(roles);
        usuarioService.guardarUsuario(nuevoUser);
        solicitudService.eliminarSolicitud(solicitud);
        var solicitudes = solicitudService.listarSolicitudes();
        model.addAttribute("solicitudes", solicitudes);
        return "/html/Sis_Administrador_GesReg";
    }

    @GetMapping("/rechazarSolicitud/{id_solicitud}")
    public String rechazarSolicitud(SolicitudRegistro solicitud, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        solicitud = solicitudService.findById(solicitud.getId_solicitud());
        solicitudService.eliminarSolicitud(solicitud);
        var solicitudes = solicitudService.listarSolicitudes();
        model.addAttribute("solicitudes", solicitudes);
        return "/html/Sis_Administrador_GesReg";
    }

    @GetMapping("/Sis_Administrador_GesUs")
    public String Sis_Administrador_GesUs(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "/html/Sis_Administrador_GesUs";
    }

    @GetMapping("/eliminarUsuario/{id_usuario}")
    public String eliminarUsuario(Usuario usuario, Model model, HttpSession session) {
        Usuario aux = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", aux);
        usuario = usuarioService.findById(usuario.getId_usuario());
        usuarioService.eliminarUsuario(usuario.getId_usuario());
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "/html/Sis_Administrador_GesUs";
    }

    @GetMapping("/modificarUsuario/{id_usuario}")
    public String modificarUsuario(Usuario usuario, Model model, HttpSession session) {
        Usuario aux = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", aux);
        usuario = usuarioService.findById(usuario.getId_usuario());
        model.addAttribute("usuarioAux", usuario);
        return "/html/Editar_User";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario, Model model, HttpSession session) {
        EncriptarPassword encriptar = new EncriptarPassword();
        Usuario aux = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", aux);
        Usuario info = usuarioService.findById(usuario.getId_usuario());
        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();
        String username = usuario.getUsername();
        String password = usuario.getPassword();
        if (!nombre.equals("")) {
            info.setNombre(nombre);
        }
        if (!apellido.equals("")) {
            info.setApellido(apellido);
        }
        if (!username.equals("")) {
            info.setUsername(username);
        }
        if (!password.equals("")) {
            info.setPassword(encriptar.encriptarPassword(password));
        }
        usuarioService.guardarUsuario(info);
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "/html/Sis_Administrador_GesUs";
    }
}
