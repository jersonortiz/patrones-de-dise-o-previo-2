/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.decorador;

import co.edu.ufps.patrones.negocio.dat.Usuario;
import java.rmi.RemoteException;
import negocio.ChatInterface;

/**
 *
 * @author jerson
 */
public class NegocioAdmin2 extends NegocioDecorador {

    public NegocioAdmin2(INeg ineg) {
        super(ineg);

    }

    public INeg getAdminlv1f() {
        return super.getIneg();
    }

    public String borrarUsuario(int id_toborr, int id_adm) throws RemoteException {
        return this.getCh().borrarUsuario(id_toborr, id_adm);
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
