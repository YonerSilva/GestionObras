package com.gestionObras.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador_Admin {
    
    @GetMapping("/Sis_Administrador_Area")
    public String Sis_Administrador_Area() {
        return "/html/Sis_Administrador_Area";
    }
}
