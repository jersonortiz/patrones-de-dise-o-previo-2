/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.basedatos.DTO;

/**
 *
 * @author jerson
 */
public class UserDTO {
    
    int id;
    int tipo;
    String nombre;
    String password;
    String ip;

    public UserDTO(String nombre,int tipo, String password) {
        this.tipo=tipo;
        this.nombre = nombre;
        this.password = password;
    }

    public UserDTO(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.tipo=0;
    }
    
    
    

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
    
    
    
}
