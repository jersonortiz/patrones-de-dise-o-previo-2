/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.decorador;

import java.rmi.RemoteException;
import negocio.ChatInterface;

/**
 *
 * @author jerson
 */
public class NegocioAdmin1 extends NegocioDecorador {

    public NegocioAdmin1(INeg ineg) {
        super(ineg);
    }

    public String borrarMensajes(int id_mens, int id_adm) throws RemoteException {

        String most = this.getCh().borrarMensaje(id_mens, id_adm);
        return most;
    }

    public INeg getAdmindefault() {
        return super.getIneg();
    }

    @Override
    public ChatInterface getCh() {
        return super.getIneg().getCh();
    }

    @Override
    public void setCh(ChatInterface ch) {
        super.getIneg().setCh(ch);
    }

}
