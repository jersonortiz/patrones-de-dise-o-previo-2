/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.RMI;


import negocio.ChatInterface;

/**
 *
 * @author Jerson
 */
public interface Observador {


   public void update(ChatInterface o, Object arg);

}
