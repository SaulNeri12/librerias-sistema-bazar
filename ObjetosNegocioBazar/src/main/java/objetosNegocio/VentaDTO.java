/*
 * VentaDTO.java
 */
package objetosNegocio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Contiene la informacion de una venta a un cliente.
 * @author rramirez
*/
public class VentaDTO {

    private Long id;

    private String nombreCliente;
    private String apellidoCliente;
    private float montoTotal;

    private MetodoPago metodoPago;

    private UsuarioDTO usuario;

    private List<DetalleVentaDTO> productosVendidos;

    private LocalDateTime fechaVenta;
    
    public VentaDTO() {
        this.productosVendidos = new ArrayList<>();
    }
        
    /**
     * Crea una instancia con la informacion de una venta a un cliente.
     * @param nombreCliente Nombre del cliente
     * @param apellidoCliente Apellido/s del cliente.
     * @param montoTotal Monto total de la venta.
     * @param metodoPago Metodo de pago realizado.
     * @param usuario UsuarioDTO a cargo de la transaccion.
     * @param productosVendidos Lista de los productos vendidos.
     * @param fecha Fecha en la que se realizo la venta
     * 
     */
    public VentaDTO(String nombreCliente, String apellidoCliente, float montoTotal, MetodoPago metodoPago, UsuarioDTO usuario, List<DetalleVentaDTO> productosVendidos, LocalDateTime fecha) {
        super();
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.productosVendidos = productosVendidos;
        this.usuario = usuario;
        this.fechaVenta = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setApellidoCliente(String apellido) {
        this.apellidoCliente = apellido;
    }
    
    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    public void setProductosVendidos(List<DetalleVentaDTO> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }
    
    public List<DetalleVentaDTO> getProductosVendidos() {
        return this.productosVendidos;
    }
    
    public void setFechaVenta(LocalDateTime fecha) {
        this.fechaVenta = fecha;
    }
    
    public LocalDateTime getFechaVenta() {
        return this.fechaVenta;
    }

    public enum MetodoPago {
        EFECTIVO,
        TARJETA
    }
    
    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", nombreCliente=" + nombreCliente + ", monto_total=" + montoTotal + ", metodo_pago=" + metodoPago + ", usuario=" + usuario + '}';
    }

}
