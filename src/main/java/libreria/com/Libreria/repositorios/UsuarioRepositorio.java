/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.repositorios;

import libreria.com.Libreria.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Agustín
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
}
