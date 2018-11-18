/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.basedatos.DAO;

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
public class UserDAO {

    private Conexion cn;
    List<UserDTO> users;
    String tipobd;

    public UserDAO() {
        this.tipobd = "mysql";
        this.cn = FactoryConection.obtenConexion(tipobd);
        this.cn.conectar();

    }

    public List<UserDTO> listar() {
        // TODO Auto-generated method stub
        String sql = "select * from usuario";
        users = new ArrayList<UserDTO>();

        try {

            ResultSet res = cn.query(sql);
            while (res.next()) {
                UserDTO c = new UserDTO();
                c.setId(res.getInt("id"));
                c.setTipo(res.getInt("tipo_usuario"));
                c.setNombre(res.getString("nombre"));
                c.setPassword(res.getString("password"));
                users.add(c);
            }
            res.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método obtener");
            e.printStackTrace();
        }
        return users;
    }

    public UserDTO obtener(String nom) {
        // TODO Auto-generated method stub

        String a = "";
        try {
            ResultSet res = cn.query("SELECT * FROM usuario where nombre= '" + nom + "' ");
            while (res.next()) {

                UserDTO c = new UserDTO();
                c.setId(res.getInt("id"));
                c.setTipo(res.getInt("tipo_usuario"));
                c.setNombre(res.getString("nombre"));
                c.setPassword(res.getString("password"));
                return c;

            }
            res.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n"+e);
        }
        return null;
    }

    public UserDTO obtener(int id) {
        // TODO Auto-generated method stub

        String a = "";
        try {
             ResultSet res = cn.query("SELECT * FROM usuario where id= '" + id + "' ");
            while (res.next()) {

                UserDTO c = new UserDTO();
                c.setId(res.getInt("id"));
                c.setNombre(res.getString("nombre"));
                c.setPassword(res.getString("password"));
                c.setTipo(res.getInt("tipo_usuario"));
                return c;

            }
            res.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n"+e);
        }
        return null;
    }

    public void insertar(UserDTO us) {
        // TODO Auto-generated method stub

    
        try {
            String consulta = "INSERT INTO usuario (nombre, password) VALUES ('" + us.getNombre() + "', "
                    + "'" + us.getPassword() + "')";
           cn.insert(consulta);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //JOptionPane.showMessageDialog(null, "No se Registro la persona");
        }

    }

    public void actualizar(UserDTO ser) {
        // TODO Auto-generated method stub

        String sql = "UPDATE usuario SET nombre='" + ser.getNombre() + "', password='" + ser.getPassword() + "', tipo_usuario= " + ser.getTipo() + " WHERE id=" + ser.getId();
        try {
            cn.insert(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void eliminar(UserDTO us) {

        String sql = "DELETE FROM `usuario` WHERE `usuario`.`id` = " + us.getId();

        try {
            cn.insert(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
