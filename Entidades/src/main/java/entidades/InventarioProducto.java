/*
 * InventarioProducto.java
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import objetosDTO.InventarioProductoDTO;

/**
 * 
 * @author Juventino López García
 */
@Entity
@Table(name = "inventario_productos")
public class InventarioProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private Integer cantidad;
    
    @OneToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public InventarioProducto() {
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
    
    public InventarioProductoDTO toDTO() {
        InventarioProductoDTO i = new InventarioProductoDTO();
        i.setCantidad(cantidad);
        i.setProducto(producto.toDTO());
        
        return i;
    }

}
