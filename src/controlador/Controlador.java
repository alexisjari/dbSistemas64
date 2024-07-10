package controlador;

import Vista.*;
import modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;

// manejo de fechas
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author 52667
 */
public class Controlador implements ActionListener {
    private jifProductos vista ;
    private dbProducto db;
    private boolean EsActualizar;

    public Controlador(jifProductos vista, dbProducto db) {
        this.vista = vista;
        this.db = db;
        
        vista.btnBuscar.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
        vista.btnCerrar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnNuevo.addActionListener(this);
        vista.btnDeshabilitar.addActionListener(this);
        this.deshabilitar();
        
    }
    //helpers
    
    public void limpiar(){
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
      //  vista.dtfFecha.setDate(date);
        
    }
    
    public void cerrar(){
        
        int res =JOptionPane.showConfirmDialog(vista, "Desea cerrar el sistema ", "Productos",
        JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        if (res == JOptionPane.YES_OPTION){
        vista.dispose();
        
    }
        
    }
    
    public void habilitar(){                        
        vista.txtCodigo.setEnabled(true);
        vista.txtNombre.setEnabled(true);
        vista.txtPrecio.setEnabled(true);
        vista.btnBuscar.setEnabled(true);
        vista.btnGuardar.setEnabled(true);
        vista.btnNuevo.setEnabled(true);

    }
    
    public void deshabilitar(){
        vista.txtCodigo.setEnabled(false);
        vista.txtNombre.setEnabled(false);
        vista.txtPrecio.setEnabled(false);
        vista.btnBuscar.setEnabled(false);
       
        vista.btnGuardar.setEnabled(false);

    }
    
    public boolean validar(){
        boolean exito = true;
        
        if (vista.txtCodigo.getText().equals("")
                ||vista.txtNombre.getText().equals("")
                ||vista.txtPrecio.getText().equals("")) exito = false;
        
        return exito;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
      if (ae.getSource()==vista.btnLimpiar){ this.limpiar();}  
      if (ae.getSource()==vista.btnCancelar){ this.limpiar(); this.deshabilitar(); }
      if (ae.getSource()==vista.btnCerrar){ this.cerrar();}
      
      if (ae.getSource()==vista.btnNuevo){ this.habilitar(); this.EsActualizar=false;}
      
      if (ae.getSource()==vista.btnGuardar){
      if (ae.getSource()==vista.btnDeshabilitar)   
          //Validacion
          
          
          
          if (this.validar()){
              Productos pro = new Productos();
              pro.setCodigo(vista.txtCodigo.getText());
              pro.setNombre(vista.txtNombre.getText());
              pro.setPrecio(Float.parseFloat(vista.txtPrecio.getText()));
              pro.setStatus(0);
              pro.setFecha(this.convertirAñoMesDia(vista.dtfFecha.getDate()));
              try{
              if(this.EsActualizar == false){
                  // Agregar nuevo producto
                  
                  db.insertar(pro);
                  JOptionPane.showMessageDialog(vista, "Se agrego con exito el tipo de producto");
                  this.limpiar(); 
                  this.deshabilitar();
                  //Acutlizar Tabla()
                  
                  
                  
                  
              }else {
                  pro.setIdProductos(idProducto);
                  db.actualizar(pro);
                  JOptionPane.showMessageDialog(vista, "Se ha actualizado el producto");
                  this.limpiar(); this.deshabilitar();
                  //Acutlizar Tabla()

                  
              }   
              }catch (Exception e){
                  JOptionPane.showMessageDialog(vista, "Ha surgio un error, tio!!! "+ e.getMessage());
              }
            
              
              
          }else JOptionPane.showMessageDialog(vista, "faltan los datos correspondientes");
          
          
          
      }
      
      if (ae.getSource()==vista.btnBuscar){
      
      Productos pro = new Productos();
      
      if (vista.txtCodigo.getText().equals("")) {
          JOptionPane.showMessageDialog(vista, "Falto capturar el codigo ");
      }
      else{ 
      
          try{
             pro = (Productos) db.buscar(vista.txtCodigo.getText());
             if (pro.getIdProductos()!=0){
                 
                          idProducto = pro.getIdProductos();
             
                          vista.txtNombre.setText(pro.getNombre());
                          vista.txtPrecio.setText(String.valueOf(pro.getPrecio()));
                          convertirStringDate(pro.getFecha());
                          this.EsActualizar = true;
                          vista.btnDeshabilitar.setEnabled(true);
                          vista.btnGuardar.setEnabled(true);
             
             }
      
          } catch(Exception e){
              JOptionPane.showMessageDialog(vista, "surgio un error" + e.getMessage());
          }  
                //else

      }
            //buscar

      }
            //deshabilitar
       if (ae.getSource()==vista.btnDeshabilitar){
           int opcion = 0;
           opcion = JOptionPane.showMessageDialog(vista, "Deseas deshabilitar el producto","Producto",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
           
           if(opcion == JOptionPane.YES_OPTION){
               
               Productos pro = new Productos();
               pro.setIdProductos(idProducto);
               
               try {
                   db.deshabilitar(pro);
                   JOptionPane.showMessageDialog(vista, "el producto fue deshabilitado");
                   this.limpiar();
                   this.deshabilitar();
                   //actualizar la tabla
                   
       }catch(Exception e){
                   
        JOptionPane.showMessageDialog(vista, "Surgio un error" + e.getMessage());
       }              
       } 
       } 
    
       
            
            
      }
    public void iniciarVista(){
        vista.setTitle("Productos");
        vista.resize(700,800);
        vista.setVisible(true);
        try {
        this.ActualizarTabla( db.lista());
        }catch(Exception e){
            JOptionPane.showMessageDialog(vista, "Surgio u error" + e.getMessage());
            
        }
    }
    public String convertirAñoMesDia(Date fecha){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        return sdf.format(fecha);
    }
    public void convertirStringDate(String fecha){
           
           try {
               // convertir la cadena de texto a un objeto Date
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
               Date date = dateFormat.parse(fecha);
               vista.dtfFecha.setDate(date);
               
           }catch(ParseException e){
               System.err.print(e.getMessage());
           }
    
    
    }
    public void ActualizarTabla(ArrayList <Productos>arr){
        String campos [] = {"idProducto","Codigo","Nombre","Precio","Fecha"} ;
        
        String [][] datos = new String [arr.size()][5];
        int reglon =0;
        for(Productos registro : arr){
            
            datos[reglon][0] = String.valueOf(registro.getIdProductos());
            datos[reglon][1] = registro.getCodigo();
            datos[reglon][2] = registro.getNombre();
            
            datos[reglon][3] = String.valueOf(registro.getPrecio());
            datos[reglon][4] = registro.getFecha();
        
        
        reglon++;
        }
        DefaultTableModel tb = new DefaultTableModel(datos,campos);
        vista.lista.setModel(tb);
    }
    
}
    
   
  
    

