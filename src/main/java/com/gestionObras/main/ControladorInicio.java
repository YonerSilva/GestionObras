/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestionObras.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ACER
 */
@Controller
@Slf4j
public class ControladorInicio {
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        
        System.out.println(user.getUsername());
        //return "/html/Sis_Administrador_Prin";
        return userAuthentication(user);
    }
    
    private String userAuthentication(@AuthenticationPrincipal User user){
        if(user.getUsername().equals("user")){
            System.out.println("Hola1");
            return "/html/Sis_Supervisor_Prin";
        }else{
            if(user.getUsername().equals("admin")){
                System.out.println("hola2");
                return "/html/Sis_Administrador_Prin";
            }
        }
        return "";
    }
    
}
