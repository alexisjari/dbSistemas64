/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author 52667
 */
public interface Persistencia {
    public void insertar(Object object)throws Exception;
    public void actualizar(Object object)throws Exception;
    public void habilitar(Object object)throws Exception;
    public void deshabilitar(Object object)throws Exception;
    public boolean siExiste(int id) throws Exception;
    
    
    public ArrayList lista()throws Exception;
    public Object buscar(String codigo) throws Exception;
    
    
}
