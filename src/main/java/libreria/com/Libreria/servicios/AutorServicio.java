/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.servicios;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import libreria.com.Libreria.entidades.Autor;
import libreria.com.Libreria.errores.ErrordeServicio;
import libreria.com.Libreria.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Agustín
 */
@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;
     @Transactional
    public void registrarAutor(String nombre) throws ErrordeServicio {
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);

        autorRepositorio.save(autor);

    }
     @Transactional
    public void modificarAutor(String id, String nombre) throws ErrordeServicio {
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autor.setAlta(true);

            autorRepositorio.save(autor);

        } else {
            throw new ErrordeServicio(("No se encontró el autor buscado"));
        }
    }
     @Transactional
    public void habilitarAutor(String id, String nombre) throws ErrordeServicio {
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            autor.setAlta(true);

            autorRepositorio.save(autor);

        } else {
            throw new ErrordeServicio(("No se encontró el autor buscado"));
        }
    }
    @Transactional
    public void deshabilitarAutor(String id) throws ErrordeServicio {
       
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            autor.setAlta(false);

            autorRepositorio.save(autor);

        } else {
            throw new ErrordeServicio(("No se encontró el autor buscado"));
        }
    }

    public void validar(String nombre) throws ErrordeServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrordeServicio("El nombre del autor no puede estar vacio o nulo.");
        }
    }
     public List<Autor> listar(){
         return autorRepositorio.findAll();
     }
     public List<Autor> mostrame(){
         List<Autor>autores=autorRepositorio.findAll();
         return autores;
     }
    
    
}
