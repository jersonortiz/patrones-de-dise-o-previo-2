/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.decorador;


import co.edu.ufps.patrones.negocio.dat.Usuario;
import negocio.ChatInterface;

/**
 *
 * @author jerson
 */
public interface INeg {

    public ChatInterface getCh();

    public void setCh(ChatInterface ch);


}
