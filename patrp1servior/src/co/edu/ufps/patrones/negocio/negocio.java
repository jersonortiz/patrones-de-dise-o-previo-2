/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio;

import co.edu.ufps.patrones.negocio.RMI.RMIServer;
import co.edu.ufps.patrones.negocio.basedatos.DAO.MensajeDAO;
import co.edu.ufps.patrones.negocio.basedatos.DTO.MensajeDTO;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author jerson
 */
public class negocio {
    
    public static void main(String[] args) throws IOException, RemoteException, UnknownHostException, AlreadyBoundException{
   //  ServerSocketc sk = new ServerSocketc();
      // MensajeDAO md = new MensajeDAO();
     //  md.insertar(new MensajeDTO("pepe","algo"));
     RMIServer rs = new RMIServer();      
    }
    
    
}
