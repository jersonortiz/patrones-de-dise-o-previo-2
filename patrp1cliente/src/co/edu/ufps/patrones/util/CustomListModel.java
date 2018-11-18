/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.util;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Jerson
 */
public class CustomListModel extends AbstractListModel {

    private ArrayList<String> lista = new ArrayList<String>();

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        String p = lista.get(index);
        return p;
    }

    public void addString(String p) {
        lista.add(p);
        this.fireIntervalAdded(this, getSize(), getSize() + 1);
    }

    public void eliminarString(int index0) {
        lista.remove(index0);
        this.fireIntervalRemoved(index0, getSize(), getSize() + 1);
    }

    public String getString(int index) {
        return lista.get(index);
    }

    public void vaciar() {
        this.lista = new ArrayList<String>();
    }
}
