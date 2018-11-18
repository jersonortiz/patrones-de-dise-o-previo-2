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
public class NegocioAdmin3 extends NegocioDecorador {

    public NegocioAdmin3(INeg ineg) {
        super(ineg);
    }

    public String nombrarAdmin(int id_usr, int rg, int id_adm) throws RemoteException {

        return this.getCh().nombrarAdmin(id_usr, rg, id_adm);
    }
    
    public INeg getAdminlv2f(){
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
