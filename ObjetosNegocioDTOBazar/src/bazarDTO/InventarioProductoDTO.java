
package bazarDTO;

import java.io.Serializable;
import objetosNegocio.InventarioProducto;

/**
 * Crea una vista de la informacion de la cantidad de un producto presente en el
 * inventario.
 * @author saul
 */

public class InventarioProductoDTO implements Serializable {

    private final Integer cantidad;
    private final String codigoProducto;
    private final String nombreProducto;

    /**
     * Crea un DTO del inventario de un producto.
     * @param productoInventario Producto en el inventario.
     */
    public InventarioProductoDTO(InventarioProducto productoInventario) {
        this.cantidad = productoInventario.getCantidad();
        this.codigoProducto = productoInventario.getProducto().getCodigo();
        this.nombreProducto = productoInventario.getProducto().getNombre();
    }
    
    /**
     * Crea un DTO que contiene la informacion basica de un producto en inventario.
     * @param codigoProducto Codigo interno del producto (sistema)
     * @param nombreProducto Nombre del producto.
     * @param cantidad Cantidad de productos en inventario.
     */
    public InventarioProductoDTO(String codigoProducto, String nombreProducto, Integer cantidad) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.codigoProducto = codigoProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    @Override
    public String toString() {
        return "InventarioProducto{" + "codigoProducto=" + codigoProducto + ", nombreProducto=" + nombreProducto + ", cantidad=" + cantidad + '}';
    }

}
