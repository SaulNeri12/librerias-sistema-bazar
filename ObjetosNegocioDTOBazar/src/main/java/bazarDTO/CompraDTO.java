
package bazarDTO;

import java.io.Serializable;
import objetosNegocio.Compra;

/**
 * Crea una vista de la informacion de una compra e un producto a un proveedor.
 * @author saul
 */
public class CompraDTO implements Serializable {

    private final Long id;
    private final Integer cantidad;
    private final float precio;
    private final String codigoProducto;
    private final Long proveedorId;

    /**
     * Crea un DTO en base a la informacion de la compra proporcionada.
     * @param compra Objeto de una compra a un proveedor
     */
    public CompraDTO(Compra compra) {
        this.id = compra.getId();
        this.cantidad = compra.getCantidad();
        this.precio = compra.getPrecioProducto();
        this.codigoProducto = compra.getProducto().getCodigo();
        this.proveedorId = compra.getProveedor().getId();
    }
    
    /**
     * Crea un DTO con la informacion basica de una compra de un producto 
     * a un proveedor.
     * @param id ID de la compra al proveedor.
     * @param cantidad Cantidad de unidades compradas.
     * @param precio Precio del producto en el momento de la compra.
     * @param codigoProducto Codigo interno del producto comprado (Codigo del
     * sistema, no de barras).
     * @param proveedorId ID del proveedor al cual se le compro el producto.
     */
    public CompraDTO(Long id, Integer cantidad, float precio, String codigoProducto, Long proveedorId) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigoProducto = codigoProducto;
        this.proveedorId = proveedorId;
    }

    public Long getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public String getCodigoProducto() {
        return this.codigoProducto;
    }

    public Long getProveedorId() {
        return this.proveedorId;
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + ", codigoProducto=" + codigoProducto + ", proveedorId=" + proveedorId + '}';
    }
}
