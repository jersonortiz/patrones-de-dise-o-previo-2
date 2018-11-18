/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio;

import co.edu.ufps.patrones.gui.VistaAdmin1;
import co.edu.ufps.patrones.gui.VistaAdmin2;
import co.edu.ufps.patrones.gui.VistaAdmin3;
import co.edu.ufps.patrones.negocio.decorador.NegocioAdmin3;
import co.edu.ufps.patrones.negocio.decorador.INeg;
import co.edu.ufps.patrones.negocio.decorador.NegocioAdmin2;
import co.edu.ufps.patrones.negocio.decorador.Admin;
import co.edu.ufps.patrones.negocio.decorador.NegocioAdmin1;
import co.edu.ufps.patrones.negocio.dat.Contacto;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import negocio.ChatInterface;
import negocio.RemoteObserver;
import co.edu.ufps.patrones.negocio.dat.Users;
import co.edu.ufps.patrones.negocio.dat.Usuario;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import co.edu.ufps.patrones.util.CustomListModel;
import javax.swing.JFrame;

/**
 *
 * @author jerson
 */
public class negocio extends UnicastRemoteObject implements RemoteObserver {

    private Usuario usr;
    private String thisIP;
    //  private ClienteSocket sc;
    public String SIP = "localhost"; // Puedes cambiar a localhost
    public int PUERTO = 3232; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
    public ChatInterface ch;
    public int lastid;
    public ArrayList<Contacto> conts;
    public ArrayList<Users> users;
    public INeg adm;
    public boolean conectado;
    private JTextArea txtconv;
    CustomListModel clm = new CustomListModel();
    CustomListModel clu = new CustomListModel();
    JFrame vistaadmin;

    public negocio() throws RemoteException, NotBoundException, UnknownHostException {
        //   this.sc = new ClienteSocket();
        //   this.sc.run();

        super();
        this.conts = new ArrayList<Contacto>();
        this.users = new ArrayList<Users>();
        this.lastid = 1;
        this.obtenerIP();
        this.conexServRemoto();

    }

    public void obtenerIP() {
        String host = "";
        try {
            this.thisIP = InetAddress.getLocalHost().getHostAddress();
            System.out.println(this.thisIP);
            host = InetAddress.getLocalHost().getHostName();
            System.out.println(host);

            InetAddress address = InetAddress.getByName(host);
            System.out.println("IP address: " + address.toString());
        } catch (UnknownHostException uhEx) {
            System.out.println("Could not find " + host);
        }
    }

    public void habilitarExtra() {
        int r = this.usr.getTipo();
        int id = this.usr.getId();

        this.adm = new Admin();
        this.adm.setCh(ch);
        switch (r) {
            case 1:
                adm = new NegocioAdmin1(adm);
                break;
            case 2:
                adm = new NegocioAdmin1(adm);
                adm = new NegocioAdmin2(adm);
                break;
            case 3:
                adm = new NegocioAdmin1(adm);
                adm = new NegocioAdmin2(adm);
                adm = new NegocioAdmin3(adm);
                break;
            default:
                System.out.println("no es administrador");
        }

    }

    public void conexServRemoto() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(SIP, PUERTO);
        ch = (ChatInterface) registry.lookup("chat");
        ch.addObserver(this);
        /*
        try {
            ChatInterface remoteService = (ChatInterface) Naming.lookup("//localhost:3232/chat");
            this.conectado = true;

        } catch (Exception ex) {
            this.conectado = false;
            ex.printStackTrace();
        }
         */
    }

    public void desconectarServRemoto() {
        this.conectado = false;
        ch = null;
    }

    public void enviarMensaje(String text) throws RemoteException {

        if (this.usr != null) {

            // this.sc.enviarMensaje(this.usr.getNombre(), text);
            this.ch.enviarMensaje(this.usr.getId(), text);
        } else {
            System.out.println("no logeado");
        }

    }

    public String login(String nom, String pass) throws RemoteException {
        boolean a = false;
        if (this.usr == null) {
            //a = this.sc.login(nom, pass);
            String aa = this.ch.Login(nom, pass, this.thisIP);

            if (!aa.equals("no")) {

                String[] re = aa.split(":");

                if (re[0].equals("ok")) {

                    System.out.println("inicio exitoso");
                    this.usr = Usuario.obtenUsuario();
                    this.usr.setNombre(nom);
                    this.usr.setPassword(pass);
                    this.usr.setId(Integer.parseInt(re[1]));
                    this.usr.setTipo(Integer.parseInt(re[2]));
                }

            } else {
                System.out.println("contraseña o usuario incorrecta");
            }
        } else {
            System.out.println("ya esta logeado");
        }
        return "";
    }

    public String registro(String nom, String pass) throws RemoteException {

        //  this.sc.registro(nom, pass);
        this.ch.registro(nom, pass);
        return "";
    }

    public void cerrarSesion() throws RemoteException {
        this.ch.logout(this.usr.getId(), this.thisIP);
        Usuario.vaciar();
    }

    public String actualizarChat() throws RemoteException {
        String tx = this.ch.actualizarMensajes(this.lastid);

        if (!tx.equals("-1")) {

            String[] te = tx.split("%");
            int idl = Integer.parseInt(te[0]);
            System.out.println(idl);
            this.lastid = idl;
            String[] text = te[1].split("-");
            String most = "";
            for (String a : text) {
                most = most + a;
            }
            return most;
        } else {
            return "";
        }
    }

    public void añadirContacto(int id_cont) throws RemoteException {
        this.ch.añadirContacto(this.usr.getId(), id_cont);
    }

    public ArrayList<Users> listarUsuarios() throws RemoteException {

        String a = this.ch.listUsers();
        this.users = new ArrayList<Users>();
        Users co;
        String[] b = a.split("/");
        for (String c : b) {
            String[] d = c.split(":");
            co = new Users(Integer.parseInt(d[0]), d[1]);
            this.users.add(co);
        }

        return this.users;
    }

    public ArrayList<Contacto> cargaContactos() throws RemoteException {

        if (this.usr != null) {
            String a = this.ch.listContactos(this.usr.getId());
            this.conts = new ArrayList<Contacto>();
            Contacto co;
            String[] b = a.split("/");
            for (String c : b) {
                String[] d = c.split(":");
                co = new Contacto(Integer.parseInt(d[0]), d[1]);
                this.conts.add(co);
            }
        }
        return this.conts;

    }

    public ArrayList<String> listMenUsr(int id_usr_men) throws RemoteException {
        ArrayList<String> d = new ArrayList<String>();
        String a = this.ch.listMensajesUsr(id_usr_men);
        String[] b = a.split("/");
        for (String c : b) {
            d.add(c);
        }
        return d;

    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }

    public ChatInterface getCh() {
        return ch;
    }

    public void setCh(ChatInterface ch) {
        this.ch = ch;
    }

    public ArrayList<Contacto> getConts() {
        return conts;
    }

    public void setConts(ArrayList<Contacto> conts) {
        this.conts = conts;
    }

    public ArrayList<Users> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }

    public INeg getAdm() {
        return adm;
    }

    public void setAdm(INeg adm) {
        this.adm = adm;
    }

    public String getThisIP() {
        return thisIP;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public String getSIP() {
        return SIP;
    }

    public void setSIP(String SIP) {
        this.SIP = SIP;
    }

    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {

        String[] a = ((String) updateMsg).split(":");
        System.out.println("desde server" + (String) updateMsg);
        String aa = "";
        switch (a[0]) {
            case "mtxt":
                System.out.println("desde server" + (String) updateMsg);
                this.txtconv.append(a[1] + "\n");
                break;
            case "comm":
                System.out.println("desde server" + (String) updateMsg);
                System.out.println("hace algo");
                switch (a[1]) {

                    case "acus":
                        this.cargarUsuarios();
                        this.actadmvist();
                        break;

                    case "envmen":
                        aa = this.actualizarChat();
                        this.txtconv.append(aa);
                        break;
                        
                    case "menelim":
                        break;

                }
                break;

        }

        //   System.out.println("got message:" + updateMsg);

        /*
        actualizar clm y clu
           ud.insertar(new UserDTO(nom, pass));
        //comando mostrar en caja de texto
        this.notifyObservadors("mtxt:se ha registrado en entrado al chat");
        //comando algo mas
          this.notifyObservadors("comm:ha entrado al chat");
         */
    }

    public void cargarUsuarios() {
        this.clu.vaciar();
        try {
            String a = "";
            ArrayList<Users> us = this.listarUsuarios();
            for (Users s : us) {
                a = s.getId() + ":" + s.getNombre();
                this.clu.addString(a);
            }

        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void actadmvist() {
        int r = this.usr.getId();
        if (this.vistaadmin != null) {
            switch (r) {
                case 1:
                    VistaAdmin1 na = (VistaAdmin1) this.vistaadmin;
                    na.getJLUSR().setModel(this.clu);
                    break;
                case 2:
                    VistaAdmin2 ne = (VistaAdmin2) this.vistaadmin;
                    ne.getJLUSR().setModel(this.clu);
                    ne.getJLUSR2().setModel(this.clu);
                case 3:
                    VistaAdmin3 nc = (VistaAdmin3) this.vistaadmin;
                    nc.getJLUSR1().setModel(this.clu);
                    nc.getJLUSR2().setModel(this.clu);

            }
        }

    }
    
    
    

    public JTextArea getTxtconv() {
        return txtconv;
    }

    public void setTxtconv(JTextArea txtconv) {
        this.txtconv = txtconv;
    }

    public CustomListModel getClm() {
        return clm;
    }

    public void setClm(CustomListModel clm) {
        this.clm = clm;
    }

    public CustomListModel getClu() {
        return clu;
    }

    public void setClu(CustomListModel clu) {
        this.clu = clu;
    }

    public JFrame getVistaadmin() {
        return vistaadmin;
    }

    public void setVistaadmin(JFrame vistaadmin) {
        this.vistaadmin = vistaadmin;
    }

}
