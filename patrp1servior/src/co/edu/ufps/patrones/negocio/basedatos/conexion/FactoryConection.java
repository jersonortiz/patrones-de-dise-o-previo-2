/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.basedatos.conexion;

/**
 *
 * @author jerson
 */
public class FactoryConection {
    
    public static Conexion obtenConexion(String tipo){
         ConexionBuilder cb = null;
        switch(tipo){
            case "mysql":  cb = new ConexionMySQLBuilder(); 
             FactoryConection.construir(cb);
             break;
            case "derby": cb= new ConexionDerbyBuilder();
            FactoryConection.construir(cb);
             break;
        }
        return cb.getCon();
    }
    
    public static void construir(ConexionBuilder  cb){
        cb.crear();
        cb.builUrl();
        cb.buildDbNam();
        cb.buildDriver();
        cb.buildPassword();
        cb.buildUserName();
        
    }
  
}
