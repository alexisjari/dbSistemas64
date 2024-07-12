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
import java.util.HashSet;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author 52667
 */
public class Controlador implements ActionListener {
    private jifProductos vista ;
    private dbProducto db;
    private boolean EsActualizar;
    private int idProductos;

    public Controlador(jifProductos vista, dbProducto db) {
        this.vista = vista;
        this.db = db;
        
        vista.btnCancelar.addActionListener(this);
        vista.btnCerrar.addActionListener(this);
        vista.btnDeshabilitar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnNuevo.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        this.deshabilitar();
        
        
    }
    
    //helpers
    public void limpiar(){
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.dtfFecha.setDate(new Date());
        
    }
    
    public void cerrar(){
        int res = JOptionPane.showConfirmDialog(vista,"desea cerrar el sistema", 
                "productos", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (res == JOptionPane.YES_OPTION){
            vista.dispose();
        }
        
    }
    
    public void habilitar(){
        vista.txtCodigo.setEnabled(true);
        vista.txtNombre.setEnabled(true);
        vista.txtPrecio.setEnabled(true);
        vista.btnBuscar.setEnabled(true);
        vista.btnDeshabilitar.setEnabled(true);
        vista.btnGuardar.setEnabled(true);
        vista.btnNuevo.setEnabled(true);
        
         
    }
    
    public void deshabilitar(){
        vista.txtCodigo.setEnabled(false);
        vista.txtNombre.setEnabled(false);
        vista.txtPrecio.setEnabled(false);
        vista.btnBuscar.setEnabled(false);
        vista.btnDeshabilitar.setEnabled(false);
        
    }
    
    public boolean validar(){
        boolean exito = true;
    
    if (vista.txtCodigo.getText().equals("") || 
        vista.txtNombre.getText().equals("") || 
        vista.txtPrecio.getText().equals("")) {
        exito = false;
    }
    
    return exito;
        
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource()==vista.btnLimpiar){this.limpiar();}
        if (ae.getSource()==vista.btnCancelar){this.limpiar();this.deshabilitar();}
        if (ae.getSource()==vista.btnCerrar){this.cerrar();}
        
         if (ae.getSource()==vista.btnNuevo){this.habilitar(); this.EsActualizar=false;}
         
         if (ae.getSource()==vista.btnGuardar){
             
             //validar guardar
             if(this.validar()){
                 Productos pro = new Productos();
                 pro.setCodigo(vista.txtCodigo.getText());
                 pro.setNombre(vista.txtNombre.getText());
                 pro.setPrecio(Float.parseFloat(vista.txtPrecio.getText()));
                 pro.setStatus(0);
                 pro.setFecha(this.convertirAñoMesDia(vista.dtfFecha.getDate()));
                 
                 try{
                      if(this.EsActualizar==false){
                     //agregar un nuevo producto
                     db.insertar(pro);
                     JOptionPane.showMessageDialog(vista,"se agrego con exito el producto");
                     this.limpiar();
                     this.deshabilitar();
                     this.ActualizarTabla(db.lista());
                     // ActualizarTabla()
                     
                 } else{
                          pro.setIdProductos(idProductos);
                          db.actualizar(pro);
                          JOptionPane.showMessageDialog(vista,"se actualizo con exito el producto");
                          this.limpiar(); this.deshabilitar();
                          this.ActualizarTabla(db.lista());
                          // ActualizarTabla()
                      }
                      
                 } catch (Exception e){
                     JOptionPane.showMessageDialog(vista, "surgio un error" + e.getMessage());
                 }
                    
                 
                 
             } else JOptionPane.showMessageDialog(vista, "faltaron datos de capturar");
             
         }
         
         if(ae.getSource()==vista.btnBuscar){
         Productos pro = new Productos();
         
         if (vista.txtCodigo.getText().equals("")){
             JOptionPane.showMessageDialog(vista, "falto capturar el codigo");
         }
         
          else{
             
             try{
                pro = (Productos) db.buscar(vista.txtCodigo.getText());
                if(pro.getIdProductos()!=0){
                    idProductos = pro.getIdProductos();
                    
                    vista.txtNombre.setText(pro.getNombre());
                    vista.txtPrecio.setText(String.valueOf(pro.getPrecio()));
                    convertirStringDate(pro.getFecha());
                    this.EsActualizar=true;
                    vista.btnDeshabilitar.setEnabled(true);
                    vista.btnGuardar.setEnabled(true);
                       
                }
                else JOptionPane.showMessageDialog(vista, "no se encuentro");
                
                 
             }catch(Exception e){
                 JOptionPane.showMessageDialog(vista, "surgio un error" + e.getMessage());
             }
             
         }//else
         
         }//buscar
         
         if(ae.getSource()==vista.btnDeshabilitar){
             int opcion =0;
             
             opcion = JOptionPane.showConfirmDialog(vista, "deseas deshabilitar el producto", "productos",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
             
             
             if(opcion == JOptionPane.YES_OPTION){
                 Productos pro = new Productos();
                 pro.setIdProductos(idProductos);
                 
                 try{
                      db.deshabilitar(pro);
                      JOptionPane.showMessageDialog(vista, "el producto fue deshabilitado");
                      this.limpiar();
                      this.deshabilitar();
                      this.ActualizarTabla(db.lista());
                      //actualiza la tabla
                      
                     
                 }catch(Exception e){
                     JOptionPane.showMessageDialog(vista, "surgio un error" + e.getMessage());
                 }
                    
                 
                 
             } 
             
         }
                
        
    }
    
    
    public void IniciarVista() throws Exception{
        vista.setTitle("Productos");
        vista.setSize(700, 800);
        vista.setVisible(true);
        try{
            this.ActualizarTabla(db.lista());

        }catch(Exception e){
            JOptionPane.showMessageDialog(vista, "surgio un error" + e.getMessage());
        }
        
        
    }
    
    
    public String convertirAñoMesDia(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fecha);
    }
    
    
    public void convertirStringDate(String fecha){
        
        try{
            //covertir la cadena de texto a un objeto date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(fecha);
            vista.dtfFecha.setDate(date);
            
        }catch(ParseException e){
            System.err.print(e.getMessage());
        }
        
        
    }
    
    public void ActualizarTabla(ArrayList <Productos> arr){
        String campos[] = {"idProductos", "codigo", "buscar", "precio", "fecha" } ;
        
        String[] [] datos = new String[arr.size()][5];
        int reglon = 0;
        for (Productos registro : arr){
            
            datos[reglon] [0]= String.valueOf(registro.getIdProductos());
            datos[reglon] [1]= String.valueOf(registro.getCodigo());
            datos[reglon] [2]= String.valueOf(registro.getNombre());
            datos[reglon] [3]= String.valueOf(registro.getPrecio());
            datos[reglon] [4]= String.valueOf(registro.getFecha());
            
            reglon ++;
        }
        DefaultTableModel tb = new DefaultTableModel(datos,campos);
        vista.lista.setModel(tb);
        
    }
    
    
    
    

    
}


    
   
  
    

