
package bazarDTO;

import java.io.Serializable;
import java.util.List;
import objetosNegocio.DetalleVenta;
import objetosNegocio.Venta;

/**
 * Crea una vista que contiene la informacion basica de una venta.
 * @author saul
*/
public class VentaDTO implements Serializable {

    private final Long id;
    private final String nombreCliente;
    private final float montoTotal;
    private final String metodoPago;
    private final Long usuarioId;
    private List<String> codigosProductosVendidos;  
    
    /**
     * Crea un DTO de una venta en base a la informacion proporcionada por el
     * objeto Venta.
     * @param venta Venta.
     */
    public VentaDTO(Venta venta) {
        this.id = venta.getId();
        this.nombreCliente = venta.getNombreCliente() + " " + venta.getApellidoCliente();
        this.montoTotal = venta.getMontoTotal();
        this.metodoPago = this.convierteMetodoPagoATexto(venta.getMetodoPago());
        this.usuarioId = venta.getUsuario().getId();
        
        if (venta.getProductosVendidos() != null) {
            List<DetalleVenta> productos = venta.getProductosVendidos();
            for (DetalleVenta p: productos) {
                this.codigosProductosVendidos.add(p.getProducto().getCodigo());
            }
        }
    }
    
    /**
     * Crea un DTO con la informacion basica de una venta.
     * @param id ID de la venta en cuestion.
     * @param nombreCliente Nombre y apellido del cliente
     * @param montoTotal Monto total de la venta.
     * @param metodoPago Metodo de pago de la compra (Efectivo o Tarjeta).
     * @param usuarioId Identificador del usuario que realizo la venta.
     * @param codigosProductosVendidos Contiene una lista que contiene los
     * codigos de producto asociados a la venta (detalle venta).
     */
    public VentaDTO(Long id, String nombreCliente, float montoTotal, Venta.MetodoPago metodoPago, Long usuarioId, List<String> codigosProductosVendidos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.montoTotal = montoTotal;
        this.metodoPago = convierteMetodoPagoATexto(metodoPago);
        this.codigosProductosVendidos = codigosProductosVendidos;
        this.usuarioId = usuarioId;
    }

    private String convierteMetodoPagoATexto(Venta.MetodoPago metodoPago) {
        if (metodoPago == Venta.MetodoPago.EFECTIVO) {
            return "EFECTIVO";
        }
        if (metodoPago == Venta.MetodoPago.TARJETA) {
            return "TARJETA";
        }
        
        return "N/D";
    }
    
    public Long getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public List<String> getCodigosProductosVendidos() {
        return this.codigosProductosVendidos;
    }
    
    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", nombreCliente=" + nombreCliente + ", monto_total=" + montoTotal + ", metodo_pago=" + metodoPago + ", usuarioId=" + usuarioId + '}';
    }

}
