/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.com.Libreria.errores;

/**
 *
 * @author Agustín
 */
public class ErrordeServicio extends Exception{
    
    public ErrordeServicio(String msn){
        super(msn);
    }
}
