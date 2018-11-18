/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.patrones.negocio.basedatos.conexion;

/**
 *
 * @author jerson
 */
public class ConexionDerbyBuilder extends ConexionBuilder{
    
        private String url = "jdbc:derby://localhost:1527/";
    private String dbName = "Poo2Project";
    private String driver = "org.apache.derby.jdbc.ClientDriver";
    private String userName = "root";
    private String password = "";
    
    
    
    
        @Override
    public void builUrl() {
        con.setUrl(url);
           }

    @Override
    public void buildDbNam() {
        con.setDbName(dbName);
      }

    @Override
    public void buildDriver() {
        con.setDriver(driver);
     }

    @Override
    public void buildUserName() {
        con.setUserName(userName);
     }

    @Override
    public void buildPassword() {
        con.setPassword(password);
     }
    
    
    
}
