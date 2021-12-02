package com.gestionObras.main;

import com.gestionObras.entities.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador_Inter {
    
    @GetMapping("/Sis_Interventor_ConsuPe")
    public String Sis_Interventor_ConsuPe(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Interventor_ConsuPe";
    }
    
    @GetMapping("/Sis_Interventor_GesPed")
    public String Sis_Interventor_GesPed(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Interventor_GesPed";
    }
    
    @GetMapping("/Sis_Interventor_DesPedido")
    public String Sis_Interventor_DesPedido(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Interventor_DesPedido";
    }
    
}
