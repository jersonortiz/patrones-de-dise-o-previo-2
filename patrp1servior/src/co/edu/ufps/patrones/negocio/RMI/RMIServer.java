/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.RMI;

import co.edu.ufps.patrones.negocio.basedatos.DAO.ContactoDAO;
import co.edu.ufps.patrones.negocio.basedatos.DAO.MensajeDAO;
import co.edu.ufps.patrones.negocio.basedatos.DAO.UserDAO;
import co.edu.ufps.patrones.negocio.basedatos.DTO.ContactoDTO;
import co.edu.ufps.patrones.negocio.basedatos.DTO.MensajeDTO;
import co.edu.ufps.patrones.negocio.basedatos.DTO.UserDTO;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Vector;
import negocio.ChatInterface;
import negocio.RemoteObserver;

/**
 *
 * @author jerson
 */
public class RMIServer extends java.rmi.server.UnicastRemoteObject implements ChatInterface {

    private class WrappedObserver implements Observador, Serializable {

        private static final long serialVersionUID = 1L;

        private RemoteObserver ro = null;

        public WrappedObserver(RemoteObserver ro) {
            this.ro = ro;
        }

        @Override
        public void update(ChatInterface o, Object arg) {
            try {
                ro.update(o.toString(), arg);
            } catch (RemoteException e) {
                System.out.println("Remote exception removing observer:" + this);
                ((RMIServer) o).deleteObservador((Observador) this);
            }
        }

    }

    private int thisPort = 3232;
    private Registry registry;
    private boolean changed = false;
    private Vector<Observador> obs;

    public RMIServer() throws RemoteException, UnknownHostException, AlreadyBoundException {
        System.out.println("Escuchando en " + InetAddress.getLocalHost());
        // create the registry and bind the name and object.
        registry = LocateRegistry.createRegistry(thisPort);
        registry.rebind("chat", (ChatInterface) this);
        obs = new Vector<>();
    }

    @Override
    public String Login(String nom, String pass, String ip) throws RemoteException {
        UserDAO ud = new UserDAO();
        UserDTO u = ud.obtener(nom);
        if (u != null) {
            u.setIp(ip);
            this.setChanged();
            this.notifyObservadors("mtxt:" + u.getNombre() + " ha entrado al chat");
            return "ok" + ":" + u.getId() + ":" + u.getTipo();

        } else {
            return "no";
        }

    }

    @Override
    public String registro(String nom, String pass) throws RemoteException {

        UserDAO ud = new UserDAO();

        UserDTO u = new UserDTO(nom, pass);
        ud.insertar(u);
        //comando mostrar en caja de texto
        this.setChanged();
        this.notifyObservadors("mtxt:" + u.getNombre() + " se ha registrado en entrado al chat");
        //comando algo mas
        this.setChanged();
        this.notifyObservadors("comm:acus");
        return "exito";
    }

    @Override
    public String enviarMensaje(int idusrenv, String txt) throws RemoteException {

        MensajeDAO md = new MensajeDAO();
        md.insertarpublic(new MensajeDTO(idusrenv, txt));
        this.setChanged();
        this.notifyObservadors("comm:envmen");
        return "ok";
    }

    @Override
    public String actualizarMensajes(int id) throws RemoteException {

        MensajeDAO md = new MensajeDAO();
        ArrayList<MensajeDTO> mens = (ArrayList<MensajeDTO>) md.listarmayorquepublic(id);

        UserDAO ud = new UserDAO();
        UserDTO usr;
        String most = "";
        int lid = 0;
        if (!mens.isEmpty()) {

            for (MensajeDTO m : mens) {
                lid = m.getIdm();

                usr = ud.obtener(m.getIdusrenv());

                most = most + "-" + usr.getNombre() + " : " + m.getTexto() + "\n";
            }
           
            return lid + "%" + most;
        } else {
            return "-1";
        }

    }

    @Override
    public String enviarMensajePriv(int idusrenv, int idusrdest, String txt) throws RemoteException {

        MensajeDAO md = new MensajeDAO();
        md.insertarprivado(new MensajeDTO(idusrenv, idusrdest, txt));

        return "ok";

    }

    @Override
    public String listMenPriEnv(int idusrenv) throws RemoteException {

        MensajeDAO md = new MensajeDAO();
        ArrayList<MensajeDTO> menpriv = (ArrayList<MensajeDTO>) md.obtprimensenv(idusrenv);
        UserDAO ud = new UserDAO();
        UserDTO usr;
        String most = "";
        for (MensajeDTO m : menpriv) {
            usr = ud.obtener(m.getIdusrenv());
            most = most + usr.getNombre() + " : " + m.getTexto() + "\n";

        }
        return most;
    }

    @Override
    public String listUsers() throws RemoteException {

        UserDAO ud = new UserDAO();
        ArrayList<UserDTO> usrs = (ArrayList<UserDTO>) ud.listar();
        String most = "";
        for (UserDTO usr : usrs) {
            most = most + usr.getId() + ":" + usr.getNombre() + "/";

        }

        return most;
    }

    @Override
    public String añadirContacto(int id_usr, int id_cont) throws RemoteException {
        ContactoDAO cd = new ContactoDAO();
        cd.insertar(id_usr, id_cont);
        return "contacto agregado";
    }

    @Override
    public String listContactos(int i) throws RemoteException {
        ContactoDAO cd = new ContactoDAO();
        ArrayList<ContactoDTO> conts = (ArrayList<ContactoDTO>) cd.listar(i);
        UserDAO ud = new UserDAO();
        String cont = "";
        String most = "";
        UserDTO u;
        for (ContactoDTO c : conts) {
            u = ud.obtener(c.getId_cont());
            cont = u.getId() + ":" + u.getNombre();
            most = most + "/" + cont;

        }
        return most;

    }

    @Override
    public String borrarMensaje(int i, int i1) throws RemoteException {
        MensajeDAO ud = new MensajeDAO();

        MensajeDTO dt = ud.obtener(i);
        ud.eliminar(dt);
        this.setChanged();
        this.notifyObservadors("mtxt:administrador elimino un mensaje ");
        this.setChanged();
        this.notifyObservadors("comm:menelim");

        return "ok";
    }

    @Override
    public String borrarUsuario(int idelim, int i1) throws RemoteException {
        UserDAO ud = new UserDAO();

        UserDTO dt = ud.obtener(idelim);
        ud.eliminar(dt);
        this.setChanged();
        this.notifyObservadors("mtxt:administrador elimino a " + dt.getNombre());
        this.setChanged();
        this.notifyObservadors("comm:acus");
        return "ok";

    }

    @Override
    public String nombrarAdmin(int id_usrnewadm, int rg, int adm) throws RemoteException {

        UserDAO ud = new UserDAO();

        UserDTO dt = ud.obtener(id_usrnewadm);
     
        dt.setTipo(rg);
        ud.actualizar(dt);
        this.setChanged();
        this.notifyObservadors("mtxt:felicidades esclavo " + dt.getNombre() + " ahora eres administrador");
        return "ok";
    }

    @Override
    public String listMensajesUsr(int idusr) throws RemoteException {

        MensajeDAO md = new MensajeDAO();
        ArrayList<MensajeDTO> mens = new ArrayList<MensajeDTO>();
        mens = (ArrayList<MensajeDTO>) md.listarMenUsr(idusr);
        String most = "";
        for (MensajeDTO m : mens) {
            most = most + m.getIdm() + ":" + m.getTexto() + "/";
        }
        return most;

    }

    @Override
    public String logout(int i, String string) throws RemoteException {
        UserDAO ud = new UserDAO();

        UserDTO dt = ud.obtener(i);
        this.setChanged();
        this.notifyObservadors("mtxt:" + dt.getNombre() + "ha salido del chat");
        return "";
    }

    public synchronized void addObservador(Observador o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    @Override
    public void addObserver(RemoteObserver ob) throws RemoteException {
        WrappedObserver mo = new WrappedObserver(ob);
        this.addObservador(mo);
        System.out.println("Added observer:" + mo);
    }

    public synchronized void deleteObservador(Observador o) {
        obs.removeElement(o);
    }

    public void notifyObservadors() {
        notifyObservadors(null);
    }

    public void notifyObservadors(Object arg) {

        Object[] arrLocal;

        synchronized (this) {

            if (!changed) {
                return;
            }
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--) {
            ((Observador) arrLocal[i]).update(this, arg);
        }
    }

    public synchronized void deleteObservadors() {
        obs.removeAllElements();
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservadors() {
        return obs.size();
    }

}
