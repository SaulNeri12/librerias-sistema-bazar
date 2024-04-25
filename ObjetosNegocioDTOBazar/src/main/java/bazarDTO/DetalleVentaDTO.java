
package bazarDTO;

import java.io.Serializable;
import objetosNegocio.DetalleVenta;

/**
 * Crea una vista de un detalle de una venta (producto comprado, perteneciente
 * a una venta).
 * @author saul
 */
public class DetalleVentaDTO implements Serializable {

    private final Long id;
    private final Integer cantidad;
    private final String codigoProducto;
    private final float precioProducto;
    private final Long ventaId;

    /**
     * Crea un DTO en base la informacion proporcionada por el detalle de venta.
     * @param detalleVenta Objeto DetalleVenta.
     */
    public DetalleVentaDTO(DetalleVenta detalleVenta) {
        this.id = detalleVenta.getId();
        this.cantidad = detalleVenta.getCantidad();
        this.codigoProducto = detalleVenta.getProducto().getCodigo();
        this.ventaId = detalleVenta.getVenta().getId();
        this.precioProducto = detalleVenta.getPrecioProducto();
    }
    
    /**
     * Crea un DTO que contiene la informacion de un producto comprado por un 
     * cliente, perteneciente a una venta.
     * @param id ID de la compra del producto individual.
     * @param precioProducto Precio del producto en el moment de la compra.
     * @param cantidad Cantidad de productos comprados.
     * @param codigoProducto Codigo interno del producto (codigo del sistema).
     * @param ventaId ID de la venta a la cual pertenece dicho detalle de venta.
     */
    public DetalleVentaDTO(Long id, float precioProducto, Integer cantidad, String codigoProducto, Long ventaId) {
        this.id = id;
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
        this.codigoProducto = codigoProducto;
        this.ventaId = ventaId;
    }

    public Long getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getCodigoProducto() {
        return this.codigoProducto;
    }

    public Long getVentaId() {
        return this.ventaId;
    }

    public float getPrecioProducto() {
        return this.precioProducto;
    }
    
    @Override
    public String toString() {
        return "DetalleVenta{" + "id=" + id + ", cantidad=" + cantidad + ", codigoProducto=" + codigoProducto + ", codigoVenta=" + ventaId + '}';
    }

}
