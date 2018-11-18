/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.gui;

import co.edu.ufps.patrones.negocio.negocio;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author jerson
 */
public class Actualizador extends Thread {

    private JTextArea txt;
    private negocio neg;

    public Actualizador() {

    }

    Actualizador(JTextArea txtconv, negocio neg) {

        this.txt = txtconv;
        this.neg = neg;
    }

        @Override
    public void run() {
        
        try {
            while(true){
            String a = neg.actualizarChat();
            this.txt.append(a);
            Thread.sleep(500);
        }
        } catch (RemoteException ex) {
            Logger.getLogger(Actualizador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Actualizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
