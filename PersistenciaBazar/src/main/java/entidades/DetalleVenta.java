/*
 * DetalleVenta.java
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import objetosNegocio.DetalleVentaDTO;

/**
 * 
 * @author Juventino López García
 */
@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private Integer cantidad;
    
    @ManyToOne
    @JoinColumn(name = "producto")
    private Producto producto;
    
    @Column(name = "precio_producto")
    private Float precioProducto;

    public DetalleVenta() {
        
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
    
    public Float getPrecioProducto() {
        return this.precioProducto;
    }
    
    public void setPrecioProducto(Float precio) {
        this.precioProducto = precio;
    }
    
    public DetalleVentaDTO toDTO() {
        DetalleVentaDTO d = new DetalleVentaDTO();
        d.setCantidad(cantidad);
        d.setProducto(producto.toDTO());
        d.setPrecioProducto(this.precioProducto);
        
        return d;
    }

}
