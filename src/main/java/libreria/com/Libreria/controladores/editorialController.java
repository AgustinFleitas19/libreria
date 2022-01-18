/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.com.Libreria.errores.ErrordeServicio;
import libreria.com.Libreria.servicios.EditorialServicio;
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
public class editorialController {
    @Autowired
    EditorialServicio editorialServicio;
    
   @GetMapping("/cargarEditorial")
    public String cargarEditorial() {
        return "cargarEditorial";
    }
    @PostMapping("/cargarEditorial")
    public String registro(@RequestParam String nombre) {
        try {
            editorialServicio.registrarEditorial(nombre);
        } catch (ErrordeServicio ex) {
            Logger.getLogger(ControladorGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "inicio.html";
    }
     @GetMapping("/editoriales")
    public String verlibros(Model model){
        model.addAttribute("editoriales",editorialServicio.listar());
        return "editoriales";
    }
     @GetMapping("/dardebajaEditorial")
    public String darDeBajaEditorial(@RequestParam(required = true) String id) throws ErrordeServicio{
        editorialServicio.deshabilitarEditorial(id);
        return "redirect:/editoriales";
    }
}
