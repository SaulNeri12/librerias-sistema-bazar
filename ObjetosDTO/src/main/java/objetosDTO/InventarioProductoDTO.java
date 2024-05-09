/*
 * InventarioProductoDTO.java
 */
package objetosDTO;

/**
 *
 * @author rramirez
 */
public class InventarioProductoDTO {

    private Integer cantidad;

    private ProductoDTO producto;

    public InventarioProductoDTO() {

    }

    public InventarioProductoDTO(Integer cantidad, ProductoDTO producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "InventarioProducto{" + "cantidad=" + cantidad + ", producto=" + producto + '}';
    }

}
