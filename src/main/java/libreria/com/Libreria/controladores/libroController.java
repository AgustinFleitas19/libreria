/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.com.Libreria.entidades.Libro;
import libreria.com.Libreria.errores.ErrordeServicio;
import libreria.com.Libreria.servicios.AutorServicio;
import libreria.com.Libreria.servicios.EditorialServicio;
import libreria.com.Libreria.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
public class libroController {
    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/cargarlibro")
    public String cargarlibro(ModelMap modelo,@RequestParam(required=false) String id) {
        modelo.addAttribute("autores", autorServicio.mostrame());
        modelo.addAttribute("editoriales", editorialServicio.mostrame());
//        if(id !=null){
//           Optional<Libro> optional = libroServicio.editarLibro(id);
//           if(optional.isPresent()){
//               modelo.addAttribute("libro", op.get());
//           } else{
//               return "redirect:/libro"
//           }
//        }
        return "cargarlibro";
    }
     @PostMapping("/cargarlibro")
    public String registro(@RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam String autor, @RequestParam String editorial) {
        try {
            libroServicio.registrarLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
        } catch (ErrordeServicio ex) {
            Logger.getLogger(ControladorGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "inicio.html";
    }
    
    @GetMapping("/libros")
    public String verlibros(Model model){
        model.addAttribute("libros",libroServicio.listar());
        return "libros";
    }
    
    @GetMapping("/dardebaja")
    public String darDeBaja(@RequestParam(required = true) String id) throws ErrordeServicio{
        libroServicio.eliminarLibro(id);
        return "redirect:/libros";
    }
    
    
    
    
    
    
}
