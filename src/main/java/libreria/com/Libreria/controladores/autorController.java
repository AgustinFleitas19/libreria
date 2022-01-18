/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.com.Libreria.errores.ErrordeServicio;
import libreria.com.Libreria.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Agustín
 */
@Controller
@RequestMapping
public class autorController {
    @Autowired
    AutorServicio autorServicio;
    
    @GetMapping("/cargarautor")
    public String cargarAutor() {
        return "cargarautor.html";
    }
    @PostMapping("/cargarautor")
    public String registro(@RequestParam String nombre) {
        try {
            autorServicio.registrarAutor(nombre);
        } catch (ErrordeServicio ex) {
            Logger.getLogger(ControladorGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "inicio.html";
    }
     @GetMapping("/autores")
    public String verlibros(Model model){
        model.addAttribute("autores",autorServicio.listar());
        return "autores";
    }
     @GetMapping("/dardebajaAutor")
    public String darDeBajaAutor(@RequestParam(required = true) String id) throws ErrordeServicio{
        autorServicio.deshabilitarAutor(id);
        return "redirect:/autores";
    }
}
