package com.gestionObras.main;

import com.gestionObras.entities.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gestionObras.entities.Pedido;
import com.gestionObras.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class Controlador_Inter {
    
    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping("/Sis_Interventor_ConsuPe")
    public String Sis_Interventor_ConsuPe(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Interventor_ConsuPe";
    }
    
    @GetMapping("/Sis_Interventor_GesPed")
    public String Sis_Interventor_GesPed(Model model, HttpSession session) {
         Usuario usuario = (Usuario) session.getAttribute("usuario");
        var pedidos = pedidoService.listarPedido();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Interventor_GesPed";
    }
    
    @GetMapping("/Sis_Interventor_Pedido")
    public String Sis_Interventor_Pedido_Ver(Pedido pedido, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        pedido = pedidoService.encontrarPedido(pedido);
        model.addAttribute("pedido", pedido);
        return "html/Sis_Interventor_Pedido";
    }
    
    @GetMapping("/Sis_Interventor_Buscar_Pedido")
    public String Sis_Interventor_Buscar_Pedido_(Pedido pedido, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        pedido = pedidoService.encontrarPedido(pedido);
        model.addAttribute("pedido", pedido);
        return "html/Sis_Interventor_Pedido";
    }
    
    @GetMapping("/Sis_Interventor_DesPedido")
    public String Sis_Interventor_DesPedido(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Interventor_DesPedido";
    }
    
}
