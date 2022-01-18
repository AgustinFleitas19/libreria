/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.servicios;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import libreria.com.Libreria.entidades.Editorial;
import libreria.com.Libreria.errores.ErrordeServicio;
import libreria.com.Libreria.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Agustín
 */
@Service
public class EditorialServicio {
      @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void registrarEditorial(String nombre) throws ErrordeServicio{
        validar(nombre);
       Editorial editorial= new Editorial();
       editorial.setNombre(nombre);
       editorial.setAlta(true);
       
       editorialRepositorio.save(editorial);
        
    }
     @Transactional
    public void modificarEditorial(String id, String nombre) throws ErrordeServicio{
        validar(nombre);
        Optional<Editorial>respuesta= editorialRepositorio.findById(id);
        if(respuesta.isPresent()){
         Editorial editorial= respuesta.get();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
       
       editorialRepositorio.save(editorial);
        
    } else{
            throw new ErrordeServicio(("No se encontró la editorial buscado"));
        }
    }
    @Transactional
     public void habilitarEditorial(String id, String nombre) throws ErrordeServicio{
        validar(nombre);
        Optional<Editorial>respuesta= editorialRepositorio.findById(id);
        if(respuesta.isPresent()){
         Editorial editorial= respuesta.get();
      
        editorial.setAlta(false);
       
       editorialRepositorio.save(editorial);
        
    } else{
            throw new ErrordeServicio(("No se encontró la editorial buscado"));
        }
     }
       @Transactional
     public void deshabilitarEditorial(String id) throws ErrordeServicio{
        
        Optional<Editorial>respuesta= editorialRepositorio.findById(id);
        if(respuesta.isPresent()){
         Editorial editorial= respuesta.get();
      
        editorial.setAlta(false);
       
       editorialRepositorio.save(editorial);
        
    } else{
            throw new ErrordeServicio(("No se encontró la editorial buscada"));
        }
    }
    
    
    public void validar(String nombre) throws ErrordeServicio{
        if(nombre==null||nombre.isEmpty()){
            throw new ErrordeServicio("El nombre de la editorial no puede estar vacio o nulo.");
        }
    }
    public List<Editorial> listar(){
        return editorialRepositorio.findAll();
    }
    public List<Editorial> mostrame(){
        List<Editorial>editoriales= editorialRepositorio.findAll();
        return editoriales;
    }
}
