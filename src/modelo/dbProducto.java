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

     if (this.conectar()) {            
            this.sqlConsulta = this.conexion.prepareStatement(consulta);
            this.sqlConsulta.setString(1, pro.getCodigo());
            this.sqlConsulta.setString(2, pro.getNombre());
            this.sqlConsulta.setString(3, pro.getFecha());
            this.sqlConsulta.setFloat(4, pro.getPrecio());
            this.sqlConsulta.setInt(5, pro.getStatus());
            
       
                this.sqlConsulta.executeUpdate();
                this.desconectar();
                
                
            }
            
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(Object object) throws Exception {
       Productos pro = new Productos();
       pro = (Productos)object;
        String consulta = "";
        consulta = "update productos set codigo = ?, nombre = ?, fecha=?, precio =?"
                + "where idProducto = ? and status = 0";
        
        if(this.conectar()){
            this.sqlConsulta = this.conexion.prepareStatement(consulta);
            this.sqlConsulta.setString(1, pro.getCodigo());
            this.sqlConsulta.setString(2, pro.getNombre());
            this.sqlConsulta.setString(3, pro.getFecha());
            this.sqlConsulta.setFloat(4, pro.getPrecio());
            this.sqlConsulta.setInt(5, pro.getIdProductos());
            
            this.sqlConsulta.executeUpdate();
            this.desconectar();
        }
    }

    @Override
    public void habilitar(Object object) throws Exception {
        Productos pro = new Productos();
        pro = (Productos)object;
        
        String consulta = "";
        consulta = "update productos set status = 0 where idProducto = ? and status = 1";
        if(this.conectar()){
           this.sqlConsulta = this.conexion.prepareStatement(consulta);
           this.sqlConsulta.setInt(1,pro.getIdProductos());
           this.sqlConsulta.executeUpdate();
           this.desconectar();
        }        
                
    }

    @Override
    public void deshabilitar(Object object) throws Exception {
        Productos pro = new Productos();
        pro = (Productos)object;

        String consulta = "";
        consulta = "update productos set status = 0 where idProducto = ? and status = 1";        
        if(this.conectar()){
           this.sqlConsulta = this.conexion.prepareStatement(consulta);
           this.sqlConsulta.setInt(1,pro.getIdProductos());
           this.sqlConsulta.executeUpdate();
           this.desconectar();
        }
        
    }

    @Override
    public boolean siExiste(int id) throws Exception {
    boolean exito = false;
    String consulta = "";
    consulta = "selecte * from productos where idProductos = ? and status = 0";
    if(this.conectar()){
        this.sqlConsulta = this.conexion.prepareStatement(consulta);
        this.sqlConsulta.setInt(1,id);
        registros = this.sqlConsulta.executeQuery();
        if(registros.next()) exito = true;
        this.desconectar();
    }
    return exito;
    }

    @Override
    public ArrayList lista() throws Exception {
        ArrayList listaProductos = new ArrayList<Productos>();
        String consulta = "select * from productos where status = 0 order by codigo ";
        
        if (this.conectar()){
            this.sqlConsulta = this.conexion.prepareStatement(consulta);
            registros = this.sqlConsulta.executeQuery();
            
            while(registros.next()){
            Productos pro = new Productos();
            
                pro.setCodigo(registros.getString("codigo"));
                pro.setNombre(registros.getString("nombre"));
                pro.setPrecio(registros.getInt("precio"));
                pro.setFecha(registros.getString("fecha"));
                pro.setIdProductos(registros.getInt("idProducto"));
                pro.setStatus(registros.getInt("status"));               
                
                //agrarlo al arrylist
                listaProductos.add(pro);
            }

            
        }
        
         this.desconectar();
         return listaProductos;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscar(String codigo) throws Exception {
        
        Productos pro = new Productos();
        String consulta = "Select * from productos where codigo = ? and status = 0";
        
        if (this.conectar()){
        this.sqlConsulta = this.conexion.prepareStatement(consulta);
        this.sqlConsulta.setString(1, codigo);
        
        registros = this.sqlConsulta.executeQuery();
        
          if(registros.next()){
              pro.setCodigo(codigo);
              pro.setNombre(registros.getString("nombre"));
              pro.setPrecio(registros.getInt("precio"));
              pro.setFecha(registros.getString("fecha"));
              pro.setIdProductos(registros.getInt("idCodigo"));
              pro.setStatus(registros.getInt("status"));
              
          }      
            
           this.desconectar();
     
        }

        return pro;
    }
    
    
    
    
}
