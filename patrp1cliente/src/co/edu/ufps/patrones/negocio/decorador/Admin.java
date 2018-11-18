/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.decorador;

import negocio.ChatInterface;

/**
 *
 * @author jerson
 */
public class Admin extends MyNeg {

    public ChatInterface ch;

    public Admin(ChatInterface ch) {
        this.ch = ch;
    }

    public Admin() {
    }

  
    
    @Override
    public ChatInterface getCh() {
        return this.ch;
    }

    @Override
    public void setCh(ChatInterface ch) {
        this.ch = ch;
    }

}
