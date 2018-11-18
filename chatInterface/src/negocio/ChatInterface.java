/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author jerson
 */
public interface ChatInterface extends Remote {

    public String Login(String nom, String pass, String ip) throws java.rmi.RemoteException;

    public String logout(int id, String ip) throws java.rmi.RemoteException;

    public String registro(String nom, String pass) throws java.rmi.RemoteException;

    public String enviarMensaje(int idusrenv, String txt) throws java.rmi.RemoteException;

    public String enviarMensajePriv(int idusrenv, int idusrdest, String txt) throws java.rmi.RemoteException;

    public String actualizarMensajes(int idultimo) throws java.rmi.RemoteException;

    public String listMenPriEnv(int idusr) throws java.rmi.RemoteException;

    public String listUsers() throws java.rmi.RemoteException;

    public String añadirContacto(int id_usr, int id_cont) throws java.rmi.RemoteException;

    public String listContactos(int id_usr) throws java.rmi.RemoteException;

    public String listMensajesUsr(int id_usrmen) throws java.rmi.RemoteException;

    public String borrarMensaje(int id_mensaje, int id_admin) throws java.rmi.RemoteException;

    public String borrarUsuario(int id_usr, int id_admin) throws java.rmi.RemoteException;

    public String nombrarAdmin(int id_usr, int rg, int id_admin) throws java.rmi.RemoteException;

     void addObserver(RemoteObserver o) throws RemoteException;
     
     
    
}
