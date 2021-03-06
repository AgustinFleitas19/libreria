/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.repositorios;

import java.util.List;
import libreria.com.Libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Agustín
 */
@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
      @Query("SELECT a from Autor a WHERE a.nombre = :nombre")
    public Autor findByName(@Param("nombre")String nombre);
}
