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
import libreria.com.Libreria.entidades.Editorial;
import libreria.com.Libreria.entidades.Libro;
import libreria.com.Libreria.errores.ErrordeServicio;
import libreria.com.Libreria.repositorios.AutorRepositorio;
import libreria.com.Libreria.repositorios.EditorialRepositorio;
import libreria.com.Libreria.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Agustín
 */
@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @Transactional
    public void registrarLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, String autor, String editorial) throws ErrordeServicio {
        validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
        libro.setAlta(true);
        Autor autor2 = autorRepositorio.findByName(autor);
        libro.setAutor(autor2);
        Editorial editorial2 = editorialRepositorio.findByName(editorial);
        libro.setEditorial(editorial2);

        libroRepositorio.save(libro);
    }

    @Transactional
    public void modificarlibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, String autor, String editorial) throws ErrordeServicio {
        validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
            libro.setAlta(true);
            Autor autor2 = autorRepositorio.findByName(autor);
//            if (autor2 == null) {
//                
//                autorServicio.registrarAutor(autor);
//            }
            libro.setAutor(autor2);
            Editorial editorial2 = editorialRepositorio.findByName(editorial);
            libro.setEditorial(editorial2);

            libroRepositorio.save(libro);
        } else {
            throw new ErrordeServicio("No se encontró el libro buscado");
        }

    }

    @Transactional
    public void deshabilitarLibro(String id) throws ErrordeServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setAlta(false);

            libroRepositorio.save(libro);
        } else {
            throw new ErrordeServicio("No se encontró el libro buscado");
        }

    }

    public void habilitarLibro(String id) throws ErrordeServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setAlta(true);

            libroRepositorio.save(libro);
        } else {
            throw new ErrordeServicio("No se encontró el libro buscado");
        }

    }

    public void validar(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, String autor, String editorial) throws ErrordeServicio {

        if (isbn == null) {
            throw new ErrordeServicio("El isbn no puede estar vacio o nulo.");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrordeServicio("El titulo no puede estar vacio o nulo.");
        }
        if (anio == null) {
            throw new ErrordeServicio("El año no puede estar vacio o nulo.");
        }
        if (ejemplares == null) {
            throw new ErrordeServicio("La cantidad de ejemplares no pueden ser vacia o nula.");
        }
        if (ejemplaresPrestados == null) {
            throw new ErrordeServicio("La cantidad de ejemplares prestados no pueden ser vacia o nula.");
        }
        if (autor == null) {
            throw new ErrordeServicio("El autor no puede estar nulo o vacio");
        }
        if (editorial == null) {
            throw new ErrordeServicio("La editorial no puede estar vacio o nulo.");
        }

    }

    public List<Libro> listar() {
        return libroRepositorio.findAll();
    }

    public void eliminarLibro(String id) throws ErrordeServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {

            libroRepositorio.delete(respuesta.get());
        } else {
            throw new ErrordeServicio("No se encontró el libro buscado");
        }
    }
     public void editarLibro(String id) throws ErrordeServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {

            libroRepositorio.delete(respuesta.get());
        } else {
            throw new ErrordeServicio("No se encontró el libro buscado");
        }
    }
}
