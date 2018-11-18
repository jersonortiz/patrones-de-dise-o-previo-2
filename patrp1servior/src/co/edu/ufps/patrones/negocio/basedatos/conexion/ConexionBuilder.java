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
public abstract class ConexionBuilder {

    protected Conexion con; 

    public Conexion getCon() {
        return con;
    }

    public void crear() {
        this.con = Conexion.getConexion();
    }
    
    public abstract void builUrl();
    public abstract void buildDbNam();
    public abstract void buildDriver();
    public abstract void buildUserName();
    public abstract void buildPassword();
    
}
