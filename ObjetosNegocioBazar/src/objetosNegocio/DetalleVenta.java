/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import java.io.Serializable;

/*
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
*/

/**
 * Contiene la informacion de venta de un producto, perteneciente a una venta.
 * @author rramirez
 */
//@Entity
//@Table(name = "DetalleVenta")
public class DetalleVenta implements Serializable {
//public class DetalleVenta {

    //private static final long serialVersionUID = 1L;
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    //@ManyToOne
    //@JoinColumn(name = "producto_id")
    private Producto producto;
    
    private float precioProducto;


    public DetalleVenta() {

    }

    /**
     * Crea una instancia de un detalle de venta, producto comprado.
     * @param precioProducto Precio del producto en ese momento.
     * @param cantidad Cantidad de unidades vendidas.
     * @param producto Producto vendido.
     */
    public DetalleVenta(float precioProducto, Integer cantidad, Producto producto) {
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setPrecioProducto(float precio) {
        this.precioProducto = precio;
    }
    
    public float getPrecioProducto() {
        return this.precioProducto;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + '}';
    }

}
