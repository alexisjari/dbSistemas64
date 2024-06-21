/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;

/**
 *
 * @author 52667
 */
public abstract class dbManejador {
    protected Connection conexion;
    protected PreparedStatement sqlConsulta;
    protected ResultSet registros;
    private String usuario,contraseña,baseDatos,drive,url;

    public dbManejador(Connection conexion, PreparedStatement sqlConsulta, ResultSet registros, String usuario, String contraseña, String baseDatos, String drive, String url) {
        this.conexion = conexion;
        this.sqlConsulta = sqlConsulta;
        this.registros = registros;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.baseDatos = baseDatos;
        this.drive = drive;
        this.url = url;
    }
    
    public dbManejador(){
       
        this.baseDatos = "sistemas ";
        this.contraseña = "4leisjar1fr"; // Obviomanete tambien es la misma de workbecnh
        this.usuario = "root";
        this.drive = "com.mysql.cj.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/" + this.baseDatos;
        this.EsDrive();
    }
    
    public boolean EsDrive(){
        boolean exito = false;
        
        try {
            Class.forName(drive);
            
        } catch(ClassNotFoundException e){
            System.out.println("surgio un error algo maloso " + e.getMessage());
            System.exit(-1);
            
        }
        return exito;
        
    }
        
    public boolean conectar(){
        boolean exito =false;
        try {
        
        this.setConexion(DriverManager.getConnection(url, usuario, contraseña));
        exito = true;
        
        } catch(SQLException e){
            System.out.println("Error del root señor " + e.getMessage());
            
        }
        return exito;
    }
    
    public void desconectar(){
        
        try {
            //validar que coexion esta abierta
            if(!this.conexion.isClosed()) this.conexion.close();
            
        } catch (SQLException e){
            
            System.out.println("surgio un desastroso error" + e.getMessage());
              
            
        }
        
    }
    
    
    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public PreparedStatement getSqlConsulta() {
        return sqlConsulta;
    }

    public void setSqlConsulta(PreparedStatement sqlConsulta) {
        this.sqlConsulta = sqlConsulta;
    }

    public ResultSet getRegistros() {
        return registros;
    }

    public void setRegistros(ResultSet registros) {
        this.registros = registros;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
