
package objetosNegocio;

import java.io.Serializable;

/**
 * Contiene la informacion de una compra a un proveedor.
 * @author rramirez
 */

//@Entity
//@Table(name = "Compra")
public class Compra implements Serializable {
//public class Compra {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    private float precioProducto;
    private float montoTotal;

    //@ManyToOne
    //@JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;

    //@ManyToOne
    //@JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    private Proveedor proveedor;

    public Compra() {
        
    }

    /**
     * Crea una instancia de una clase de compra a un proveedor.
     * @param cantidad Cantidad de productos comprados.
     * @param precioProducto Precio del producto en ese momento.
     * @param producto Producto en cuestion.
     * @param proveedor Proveedor al cual se le compra el producto.
     */
    public Compra(Integer cantidad, float precioProducto, Producto producto, Proveedor proveedor) {
        this.cantidad = cantidad;
        this.precioProducto = precioProducto;
        this.producto = producto;
        this.proveedor = proveedor;
        
        this.calcularMontoTotal();
    }

    private void calcularMontoTotal() {
        float total = this.cantidad * this.precioProducto;
        this.montoTotal = total;
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
        this.calcularMontoTotal();
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precio) {
        this.precioProducto = precio;
        this.calcularMontoTotal();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    public float getMontoTotal() {
        return this.montoTotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra))
        {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", cantidad=" + cantidad + ", precio=" + precioProducto + ", producto=" + producto + ", proveedor=" + proveedor + '}';
    }

}
