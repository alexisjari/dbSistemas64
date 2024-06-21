/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 52667
 */
public class Productos {
    private int idProductos;
    private String codigo;
    private String nombre;
    private float precio;
    private String fecha;
    private int status; // 0 = Habilitado 1 No habilitado

    public Productos(int idProductos, String codigo, String nombre, float precio, String fecha, int status) {
        this.idProductos = idProductos;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
        this.status = status;
    }
    
    public Productos (){
        this.idProductos = 0;
        this.codigo = "";
        this.nombre = "";
        this.precio = 0 ;
        this.fecha = "";
        this.status = 0;
        
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
    
}