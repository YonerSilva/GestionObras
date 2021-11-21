package com.gestionObras.main;

import com.gestionObras.entities.Area;
import com.gestionObras.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controlador_Admin {
    
    @Autowired
    private AreaService areaService;
    
    @GetMapping("/Sis_Administrador_Area")
    public String Sis_Administrador_Area(Model model) {
        var areas = areaService.listarAreas();
        model.addAttribute("areas", areas);
        return "/html/Sis_Administrador_Area";
    }
    
    @GetMapping("/Sis_Agregar_Areas")
    public String Sis_Agregar_Areas(Area area){
        return "/html/Sis_Agregar_Areas";
    }
    
    @PostMapping("/guardarArea")
    public String guardarArea(Area area){
        areaService.guardar(area);
        return "/html/Sis_Administrador_Area";
    }
    
    @GetMapping("/Sis_Modificar_Areas_Mod/{id_area}")
    public String Sis_Modificar_Areas_Mod(Area area, Model model){
        model.addAttribute("area", area);
        return "html/Sis_Modificar_Areas_Mod";
    }
    
     @GetMapping("/Sis_Administrador_Zona")
    public String Sis_Administrador_Zona() {
        return "/html/Sis_Administrador_Zona";
    }
    
    @GetMapping("/Sis_Administrador_GesReg")
    public String Sis_Administrador_GesReg() {
        return "/html/Sis_Administrador_GesReg";
    }
    
    @GetMapping("/Sis_Administrador_GesUs")
    public String Sis_Administrador_GesUs() {
        return "/html/Sis_Administrador_GesUs";
    }
}
