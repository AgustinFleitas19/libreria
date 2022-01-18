/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.controladores;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author Agustín
 */
@Controller
@RequestMapping
public class ControladorGeneral {

    

    @GetMapping("/")
    public String inicio() {
        return "inicio.html";
    }

    @GetMapping("/inicio")
    public String inicio2() {
        return "inicio.html";
    }
    
    

    

     
   

   
}
