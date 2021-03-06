package com.gestionObras.main;

import com.gestionObras.entities.Insumo;
import com.gestionObras.entities.Pedido;
import com.gestionObras.entities.Usuario;
import com.gestionObras.service.PedidoService;
import com.gestionObras.service.PuntoService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Controlador_Super {

    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private PuntoService puntoService;

    @GetMapping("/Sis_Supervisor_CargarPedido/{id_pedido}")
    public String Sis_Supervisor_CargarPedido(Pedido pedido, Insumo insumo, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);

        if (pedido.getId_pedido() != 0) {
            try {
                pedido = pedidoService.encontrarPedido(pedido);
                model.addAttribute("pedido", pedido);
                var puntos = puntoService.listarPuntos();
                model.addAttribute("puntos", puntos);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        List<Insumo> insumos = pedido.getInsumos();
        model.addAttribute("insumos", insumos);
        return "html/Sis_Supervisor_CargarPedido";
    }

    @PostMapping("/guardarPedido")
    public String guardarPedido(Pedido pedido, Model model, RedirectAttributes atributes, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        if (pedido.getTipo_pedido() == 0) {
            atributes.addFlashAttribute("errores_ped", "Los campos estan vacios.");
        } else {
            try {
                pedido.setEstado("Pendiente");
                pedido.setFecha_carga(new Date(System.currentTimeMillis()));
                pedido.setSupervisor(usuario);
                pedido = pedidoService.guardarPedido(pedido);
            } catch (Exception e) {
                atributes.addFlashAttribute("errores_ped", "El pedido ya existe.");
            }
        }
        model.addAttribute("pedido", pedido);
        return "redirect:/Sis_Supervisor_CargarPedido/" + pedido.getId_pedido();
    }

    @GetMapping("/eliminarPedidoSuper/{id_pedido}")
    public String eliminarPedidoSuper(Pedido pedido, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            pedido = pedidoService.encontrarPedido(pedido);
            pedidoService.eliminarPedido(pedido);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        model.addAttribute("pedido", pedido);
        return "redirect:/Sis_Supervisor_GesPed";
    }

    @PostMapping("/guardarInsumo/{id_pedido}")
    public String guardarInsumo(Pedido pedido, Insumo insumo, Model model, RedirectAttributes atributes, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
            pedido = pedidoService.encontrarPedido(pedido);
            double valorInsumo = insumo.getCantidad() * insumo.getPrecio();
            pedido.setTotal_pedido(pedido.getTotal_pedido() + valorInsumo);
            pedido.getInsumos().add(insumo);
            pedidoService.guardarPedido(pedido);
        } catch (Exception e) {
            atributes.addFlashAttribute("errores_ins", "El insumo ya existe.");
        }
        model.addAttribute("pedido", pedido);
        return "redirect:/Sis_Supervisor_CargarPedido/" + pedido.getId_pedido();
    }

    @GetMapping("/Sis_Supervisor_GesPed")
    public String Sis_Supervisor_GesPed(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        var pedidos = pedidoService.listarPedido();
        model.addAttribute("pedidos", pedidos);
        return "html/Sis_Supervisor_GesPed";
    }

    @GetMapping("/verPedidoSuper/{id_pedido}")
    public String verPedidoSuper(Pedido pedido, Model model, HttpSession session) {
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
        
        return "html/Sis_Supervisor_Pedido";
    }


    @GetMapping("/Sis_Supervisor_ConsuPe")
    public String Sis_Supervisor_ConsuPe(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "html/Sis_Supervisor_ConsuPe";
    }

    @GetMapping("/Sis_Supervisor_DesPedido")
    public String Sis_Supervisor_DesPedido(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        var pedidos = pedidoService.listarPedido();
        model.addAttribute("pedidos", pedidos);
        return "html/Sis_Supervisor_DesPedido";
    }
}
