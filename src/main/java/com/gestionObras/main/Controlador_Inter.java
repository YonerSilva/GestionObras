package com.gestionObras.main;

import com.gestionObras.entities.Insumo;
import com.gestionObras.entities.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gestionObras.entities.Pedido;
import com.gestionObras.service.PedidoService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
        try {
            pedido = pedidoService.encontrarPedido(pedido);
            model.addAttribute("pedido", pedido);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "html/Sis_Interventor_Pedido";
    }
    
    @GetMapping("/eliminarPedidoInter/{id_pedido}")
    public String eliminarPedidoInter(Pedido pedido, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            pedido = pedidoService.encontrarPedido(pedido);
            pedidoService.eliminarPedido(pedido);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        model.addAttribute("pedido", pedido);
        return "redirect:/Sis_Interventor_GesPed";
    }

    @GetMapping("/verPedidoInter/{id_pedido}")
    public String verPedidoInter(Pedido pedido, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        List<Insumo> insumos = new ArrayList<>();
        try {
            pedido = pedidoService.encontrarPedido(pedido);

            if (pedido != null) {
                insumos = pedido.getInsumos();
                model.addAttribute("pedido", pedido);
                model.addAttribute("insumos", insumos);
            }else{
                throw new Exception("No se pudo encontrar el pedido.");
            }
        } catch (Exception e) {
            model.addAttribute("errores", e.getMessage());
        }
        
        return "/html/Sis_Interventor_Pedido";
    }
    
    @GetMapping("/aprobarPedido/{id_pedido}")
    public String aprobarPedido(Pedido pedido, Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            pedido = pedidoService.encontrarPedido(pedido);
            pedido.setFecha_aprobacion(new Date(System.currentTimeMillis()));
            pedido.setEstado("Aprobado");
            pedido.setInterventor(usuario);
            pedidoService.guardarPedido(pedido);
        } catch (Exception e) {
            model.addAttribute("errores", e.getMessage());
        }
        
        return "redirect:/Sis_Interventor_GesPed";
    }

    @GetMapping("/Sis_Interventor_DesPedido")
    public String Sis_Interventor_DesPedido(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/html/Sis_Interventor_DesPedido";
    }

}
