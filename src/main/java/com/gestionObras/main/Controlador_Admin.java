package com.gestionObras.main;

import com.gestionObras.entities.Area;
import com.gestionObras.entities.Zona;
import com.gestionObras.service.AreaService;
import com.gestionObras.service.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controlador_Admin {
    
    @Autowired
    private AreaService areaService;
    private ZonaService zonaService;
        
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
    public String guardarArea(Model model,Area area){
        areaService.guardar(area);
        var areas = areaService.listarAreas();
        model.addAttribute("areas", areas);
        return "/html/Sis_Administrador_Area";
    }
    
    @GetMapping("/Sis_Modificar_Areas_Mod/{id_area}")
    public String Sis_Modificar_Areas_Mod(Area area, Model model){
        area = areaService.encontrarArea(area);
        model.addAttribute("area", area);
        return "html/Sis_Modificar_Areas_Mod";
    }
    
    @GetMapping("/eliminar/{id_area}")
    public String eliminarArea(Area area, Model model){
        areaService.eliminar(area);
        var areas = areaService.listarAreas();
        model.addAttribute("areas", areas);
        return "/html/Sis_Administrador_Area";
    }
    
     @GetMapping("/Sis_Administrador_Zona")
    public String Sis_Administrador_Zona(Model model) {
        var zonas = zonaService.listarZonas();
        model.addAttribute("zonas", zonas);
        return "/html/Sis_Administrador_Zona";
    }
    
     @GetMapping("/Sis_Agregar_Zonas")
    public String Sis_Agregar_Zonas(Zona zona){
        return "/html/Sis_Agregar_Zonas";
    }
    
    @PostMapping("/guardarZona")
    public String guardarZona(Model model,Zona zona){
        zonaService.guardar(zona);
        var zonas = zonaService.listarZonas();
        model.addAttribute("zonas", zonas);
        return "/html/Sis_Administrador_Zona";
    }
    /*
    @GetMapping("/Sis_Modificar_Zonas_Mod/{id_zona}")
    public String Sis_Modificar_Zonas_Mod(Zona zona, Model model){
        zona = zonaService.encontrarZona(zona);
        model.addAttribute("zona", zona);
        return "html/Sis_Modificar_Zonas_Mod";
    }
    
    @GetMapping("/eliminarZona/{id_zona}")
    public String eliminarZona(Zona zona, Model model){
        zonaService.eliminar(zona);
        var zonas = zonaService.listarZonas();
        model.addAttribute("zonas", zonas);
        return "/html/Sis_Administrador_Zona";
    }
    */
    @GetMapping("/Sis_Administrador_GesReg")
    public String Sis_Administrador_GesReg() {
        return "/html/Sis_Administrador_GesReg";
    }
    
    @GetMapping("/Sis_Administrador_GesUs")
    public String Sis_Administrador_GesUs() {
        return "/html/Sis_Administrador_GesUs";
    }
}
