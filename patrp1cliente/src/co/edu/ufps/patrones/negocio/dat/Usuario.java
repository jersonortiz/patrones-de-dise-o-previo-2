/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.dat;

/**
 *
 * @author jerson
 */
public  class Usuario {

    private static Usuario usr;
    int id;
    int tipo;
    String nombre;
    String password;

    private Usuario() {
    }

    public static Usuario obtenUsuario() {
        if (usr == null) {
            usr = new Usuario();
            return usr;
        } else {
            return usr;
        }
    }

    public static void vaciar() {
        usr = null;
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

}
