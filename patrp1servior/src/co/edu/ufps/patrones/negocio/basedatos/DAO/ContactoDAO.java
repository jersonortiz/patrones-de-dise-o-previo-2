/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.basedatos.DAO;

import co.edu.ufps.patrones.negocio.basedatos.DTO.ContactoDTO;
import co.edu.ufps.patrones.negocio.basedatos.DTO.UserDTO;
import co.edu.ufps.patrones.negocio.basedatos.conexion.Conexion;
import co.edu.ufps.patrones.negocio.basedatos.conexion.FactoryConection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jerson
 */
public class ContactoDAO {

    private Conexion cn;
    List<ContactoDTO> conts;
    String tipobd;

    public ContactoDAO() {
        this.tipobd = "mysql";
        this.cn = FactoryConection.obtenConexion(tipobd);
        this.cn.conectar();

    }

    public List<ContactoDTO> listar(int id_usr) {
        // TODO Auto-generated method stub
        String sql = "select * from contactos where id_usr=" + id_usr + " ";
        conts = new ArrayList<ContactoDTO>();

        try {

            ResultSet res = cn.query(sql);
            while (res.next()) {
                ContactoDTO c = new ContactoDTO();
                c.setId(res.getInt(1));
                c.setId_usr(res.getInt(2));
                c.setId_cont(res.getInt(3));

                conts.add(c);
            }
            res.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }
        return conts;
    }

    public void insertar(int id_usr, int id_cont) {
        // TODO Auto-generated method stub

        try {
            String consulta = "INSERT INTO contactos (id_usr,id_cont) VALUES ('" + id_usr + "', "
                    + "'" + id_cont + "')";
          
            cn.insert(consulta);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //JOptionPane.showMessageDialog(null, "No se Registro la persona");
        }

    }

    public void eliminar(UserDTO us) {

    }

}
