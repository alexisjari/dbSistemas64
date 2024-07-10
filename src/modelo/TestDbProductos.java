package modelo;

import javax.swing.JOptionPane;


public class TestDbProductos {

      public static void main(String[] args) {
        // TODO code application logic here
        dbProducto db = new dbProducto();
        
        if(db.conectar()){
            System.out.println("fue posible conectarse");
            
            //insetar
            Productos pro = new Productos();
            pro.setCodigo("200");
            pro.setNombre("pollo");
            pro.setPrecio(25.50f);
            pro.setStatus(2);
            
            try{
            db.insertar(pro);
            JOptionPane.showMessageDialog(null, "se agrego con exito");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "surgio un error" + e.getMessage());
            }
            
            
            //actualizar 
            pro.setNombre("caja de atun");
            pro.setPrecio(15.00f);
            pro.setStatus(3);

            try {
                db.actualizar(pro);
                JOptionPane.showMessageDialog(null, "Se actualizó con éxito");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Surgió un error: " + e.getMessage());
            }
            
            //buscar
            try{
                pro.setCodigo("200");
                pro = (Productos) db.buscar(pro.getCodigo());
                JOptionPane.showMessageDialog(null, pro.getNombre()+ "," + pro.getPrecio());
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "surgio un error" + e.getMessage());
            }
           
        }
        
        
    
  
    }
    
    
    
    
}
