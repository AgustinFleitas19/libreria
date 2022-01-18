/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.repositorios;

import java.util.List;
import libreria.com.Libreria.entidades.Editorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Agustín
 */
@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
     @Query("SELECT e from Editorial e WHERE e.nombre = :nombre")
    public Editorial findByName(@Param("nombre")String nombre);
    
    @Query("SELECT e from Editorial e WHERE e.alta =true")
    public List<Editorial> findByTrue();
}
