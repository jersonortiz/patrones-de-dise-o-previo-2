/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.decorador;

/**
 *
 * @author jerson
 */
public abstract class NegocioDecorador implements INeg {
    private INeg ineg;

    public NegocioDecorador(INeg ineg) {
        this.ineg = ineg;
    }

    public INeg getIneg() {
        return ineg;
    }

    public void setIneg(INeg ineg) {
        this.ineg = ineg;
    }
    
    
    
}
