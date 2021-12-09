package com.gestionObras.main;

import com.gestionObras.entities.Pedido;
import com.gestionObras.entities.Usuario;
import com.gestionObras.service.PedidoService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controlador_Super {
    
    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping("/Sis_Supervisor_CargarPedido")
    public String Sis_Supervisor_CargarPedido(Pedido pedido,Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Supervisor_CargarPedido";
    }
    
    @PostMapping("/guardarPedido")
    public String guardarPedido(Pedido pedido, Model model, HttpSession session){
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            
        } catch (Exception e) {
            
        }
        return "/html/Sis_Supervisor_CargarPedido";
    }
    
    @GetMapping("/Sis_Supervisor_GesPed")
    public String Sis_Supervisor_GesPed(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Supervisor_GesPed";
    }
    
    @GetMapping("/Sis_Supervisor_ConsuPe")
    public String Sis_Supervisor_ConsuPe(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Supervisor_ConsuPe";
    }
    
    @GetMapping("/Sis_Supervisor_DesPedido")
    public String Sis_Supervisor_DesPedido(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Supervisor_DesPedido";
    }
}
