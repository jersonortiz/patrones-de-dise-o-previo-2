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
public class MensajeDTO {
    int idm;
    int idusrenv;
    int idusrdest;
    String texto;

    public MensajeDTO(int idm, int idusrenv, int idusrdest, String texto) {
        this.idm = idm;
        this.idusrenv = idusrenv;
        this.idusrdest = idusrdest;
        this.texto = texto;
    }

    public MensajeDTO() {
    }

    public MensajeDTO(int idusrenv, String texto) {
        this.idusrenv = idusrenv;
        this.texto = texto;
    }

    public MensajeDTO(int idusrenv, int idusrdest, String texto) {
        this.idusrenv = idusrenv;
        this.idusrdest = idusrdest;
        this.texto = texto;
    }

    
    
    
    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public int getIdusrenv() {
        return idusrenv;
    }

    public void setIdusrenv(int idusrenv) {
        this.idusrenv = idusrenv;
    }

    public int getIdusrdest() {
        return idusrdest;
    }

    public void setIdusrdest(int idusrdest) {
        this.idusrdest = idusrdest;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


     
    
    
    
}
