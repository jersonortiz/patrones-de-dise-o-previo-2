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
public class ContactoDTO {
    
    int id;
    int id_usr;
    int id_cont;

    public ContactoDTO(int id_usr, int id_cont) {
        this.id_usr = id_usr;
        this.id_cont = id_cont;
    }

    public ContactoDTO(int id, int id_usr, int id_cont) {
        this.id = id;
        this.id_usr = id_usr;
        this.id_cont = id_cont;
    }

    public ContactoDTO() {
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usr() {
        return id_usr;
    }

    public void setId_usr(int id_usr) {
        this.id_usr = id_usr;
    }

    public int getId_cont() {
        return id_cont;
    }

    public void setId_cont(int id_cont) {
        this.id_cont = id_cont;
    }
    
    
    
}
