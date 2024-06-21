package modelo;

import java.util.ArrayList;

public class dbProducto extends dbManejador implements Persistencia{
    
    public dbProducto(){
    super();
    
    }

    @Override
    public void insertar(Object object) throws Exception {
            Productos pro = new Productos();
            pro = (Productos)object;
            
 
            
            String consulta = "";
            consulta = "insert into productos(codigo,nombre,fecha,precio,status)" +  "values(?,?,?,?,?)";
            
            this.sqlConsulta = this.conexion.prepareStatement(consulta);
            this.sqlConsulta.setString(1, pro.getCodigo());
            this.sqlConsulta.setString(2, pro.getNombre());
            this.sqlConsulta.setString(3, "2024-06-20");
            this.sqlConsulta.setFloat(4, pro.getPrecio());
            this.sqlConsulta.setInt(5, pro.getStatus());
            
            if (this.conectar()) {
                this.sqlConsulta.executeUpdate();
                this.desconectar();
                
                
            }
            
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void habilitar(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deshabilitar(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean siExiste(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList lista() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscar(String codigo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
