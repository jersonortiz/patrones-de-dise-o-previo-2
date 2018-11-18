/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.basedatos.DAO;

import co.edu.ufps.patrones.negocio.basedatos.DTO.MensajeDTO;
import co.edu.ufps.patrones.negocio.basedatos.DTO.UserDTO;
import co.edu.ufps.patrones.negocio.basedatos.conexion.Conexion;
import co.edu.ufps.patrones.negocio.basedatos.conexion.FactoryConection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jerson
 */
public class MensajeDAO {

    private Conexion cn;
    List<MensajeDTO> mens;
    String tipobd;
    ArrayList<MensajeDTO> mensajes;

    public MensajeDAO() {
        this.tipobd = "mysql";
        this.cn = FactoryConection.obtenConexion(tipobd);
        //  this.cn  = Conexion.getConexion();
        this.cn.conectar();
        // TODO Auto-generated constructor stub
    }

    public List<MensajeDTO> listar() {
        // TODO Auto-generated method stub
        String sql = "select * from mensajes";
        mens = new ArrayList<MensajeDTO>();

        try {

            ResultSet res = cn.query(sql);
            while (res.next()) {
                MensajeDTO c = new MensajeDTO();
                c.setIdm(res.getInt("id"));
                c.setIdusrenv(res.getInt("id_usr_env"));
                c.setIdusrdest(res.getInt("id_usr_dest"));
                c.setTexto(res.getString("texto"));
                mens.add(c);
            }
            res.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }
        return mens;
    }

    public List<MensajeDTO> listarMenUsr(int id_usr_men) {
        // TODO Auto-generated method stub
        String sql = "select * from mensajes where id_usr_env=" + id_usr_men;
        mens = new ArrayList<MensajeDTO>();

        try {

            ResultSet res = cn.query(sql);
            while (res.next()) {
                MensajeDTO c = new MensajeDTO();
                c.setIdm(res.getInt("id"));
                c.setIdusrenv(res.getInt("id_usr_env"));
                c.setIdusrdest(res.getInt("id_usr_dest"));
                c.setTexto(res.getString("texto"));
                mens.add(c);
            }
            res.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }
        return mens;
    }

    public MensajeDTO obtener(int id) {
        // TODO Auto-generated method stub
        try {
            ResultSet res = cn.query("SELECT * FROM mensajes where id= " + id + " ");
            while (res.next()) {

                MensajeDTO c = new MensajeDTO();
                c.setIdm(res.getInt("id"));
                c.setIdusrenv(res.getInt("id_usr_env"));
                c.setIdusrdest(res.getInt("id_usr_dest"));
                c.setTexto(res.getString("texto"));
                return c;

            }
            res.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n"+e);
        }
        return null;
    }

    public void insertarpublic(MensajeDTO us) {
        // TODO Auto-generated method stub
        //INSERT INTO `mensajes` ( `id_usr_env`, `id_usr_dest`, `texto`) VALUES ( '1',null, 'hola todos')
        //INSERT INTO `mensajes` ( `id_usr_env`, `id_usr_dest`, `texto`) VALUES ( '1', '3', 'ke ase')
        try {
            String consulta = "INSERT INTO mensajes (id_usr_env, texto) VALUES ('" + us.getIdusrenv() + "', "
                    + "'" + us.getTexto() + "')";
             cn.insert(consulta); // insert(consulta);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //JOptionPane.showMessageDialog(null, "No se Registro la persona");
        }

    }

    public void insertarprivado(MensajeDTO us) {
       try {
            String consulta = "INSERT INTO mensajes (id_usr_env,id_usr_dest, texto) VALUES ('" + us.getIdusrenv() + "', "
                    + "'" + us.getIdusrdest() + "' , " + us.getTexto() + "' )";
           cn.insert(consulta); // insert(consulta);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void actualizar(MensajeDTO ser) {
        // TODO Auto-generated method stub

        String sql = "UPDATE mensajes SET texto='" + ser.getTexto() + "'" + " WHERE id=" + ser.getIdm();
        try {
            cn.insert(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<MensajeDTO> listarmayorquepublic(int id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM mensajes WHERE id > " + id + "  AND id_usr_dest = 0";
        mensajes = new ArrayList<MensajeDTO>();

        try {

            ResultSet res = cn.query(sql);
            while (res.next()) {
                MensajeDTO c = new MensajeDTO();
                c.setIdm(res.getInt("id"));
                c.setIdusrenv(res.getInt("id_usr_env"));
                c.setIdusrdest(res.getInt("id_usr_dest"));
                c.setTexto(res.getString("texto"));
                this.mensajes.add(c);
            }
            res.close();

        } catch (SQLException e) {
            System.out.println("Error: paso algo malo , muy malo");
            e.printStackTrace();
        }
        return mensajes;
    }

    public List<MensajeDTO> obtprimensenv(int idusrenv) {
        // TODO Auto-generated method stub
        String sql = "select * from mensajes where id_usr_env=" + idusrenv + "and id_usr_dest > 0";
        mens = new ArrayList<MensajeDTO>();

        try {

            ResultSet res = cn.query(sql);
            while (res.next()) {
                MensajeDTO c = new MensajeDTO();
                c.setIdm(res.getInt("id"));
                c.setIdusrenv(res.getInt("id_usr_env"));
                c.setIdusrdest(res.getInt("id_usr_dest"));
                c.setTexto(res.getString("texto"));
                mens.add(c);
            }
            res.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }
        return mens;
    }

    public void eliminar(MensajeDTO us) {
        String sql = "DELETE FROM `mensajes` WHERE `mensajes`.`id` = " + us.getIdm() + "";

        try {
            cn.insert(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
