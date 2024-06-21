package modelo;

import javax.swing.JOptionPane;


public class TestDbProductos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        dbProducto db = new dbProducto();
        
        if (db.conectar()){
            System.out.println("Fue posible conectarse");
            
            Productos pro = new Productos();
            pro.setCodigo("1000");
            pro.setNombre("Atun");
            pro.setPrecio(10.50f);
            pro.setStatus(0);
            
            try {
            db.insertar(pro);
            JOptionPane.showMessageDialog(null, "se agrego con exito");
            } catch (Exception e){
                
                JOptionPane.showMessageDialog(null, "Surgio un error " + e.getMessage());
            }
                
        }
        
        
    }
    
}
