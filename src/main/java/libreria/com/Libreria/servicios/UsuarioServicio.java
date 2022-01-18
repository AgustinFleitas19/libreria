/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.servicios;

import javax.transaction.Transactional;
import libreria.com.Libreria.entidades.Usuario;
import libreria.com.Libreria.errores.WebException;
import libreria.com.Libreria.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Agustín
 */
@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Usuario guardar(String username, String password, String password2) {
        Usuario usuario = new Usuario();

        return usuarioRepositorio.save(usuario);

    }

        @Transactional
        public void validar (String username, String password, String password2) throws WebException{
         if(username.isEmpty() || username == null){
             throw new WebException("El nombre de usuario no puede estar vacio o nulo");
}
         if(password.isEmpty() || password == null){
             throw new WebException("El nombre de usuario no puede estar vacio o nulo");
}
         if(password2.isEmpty() || password2 == null){
             throw new WebException("El nombre de usuario no puede estar vacio o nulo");
         }
        
    }
    
}
