/*
 * Venta.java
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import objetosNegocio.DetalleVentaDTO;
import objetosNegocio.VentaDTO;

/**
 *
 * @author Juventino López García
 */
@Entity
@Table(name = "ventas")
@NamedQuery(name = "consultaVentaID", query = "SELECT v FROM Venta v WHERE v.id = :id")
@NamedQuery(name = "consultaVentasUsuario", query = "SELECT v FROM Venta v WHERE v.usuario.id = :id")
@NamedQuery(name = "consultaVentasPeriodo", query = "SELECT v FROM Venta v WHERE v.fechaVenta BETWEEN :fechaInicio AND :fechaFin")
@NamedQuery(name = "consultaVentas", query = "SELECT v FROM Venta v")
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "apellido_cliente")
    private String apellidoCliente;
    
    @Column(name = "monto_total")
    private Float montoTotal;

    @Column(name = "fecha_venta", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaVenta;
    
    @Column(name = "metodo_pago")
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "venta")
    private List<DetalleVenta> detalleVentas;
    
    public static enum MetodoPago {
        TARJETA,
        EFECTIVO
    }

    public Venta() {
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

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public Float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setFechaVenta(LocalDateTime fecha) {
        this.fechaVenta = fecha;
    }
    
    public LocalDateTime getFechaVenta() {
        return this.fechaVenta;
    }

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }
    
    public VentaDTO toDTO() {
        VentaDTO v = new VentaDTO();
        
        v.setId(id);
        v.setFechaVenta(fechaVenta);
        v.setMontoTotal(montoTotal);
        v.setUsuario(usuario.toDTO());
        v.setNombreCliente(nombreCliente);
        v.setApellidoCliente(apellidoCliente);
        v.setMetodoPago(VentaDTO.MetodoPago.valueOf(this.metodoPago.name()));
        
        List<DetalleVentaDTO> productosVendidos = this.detalleVentas
                .stream()
                .map(detalle -> detalle.toDTO())
                .collect(Collectors.toList());
        
        v.setProductosVendidos(productosVendidos);
        
        return v;
    }

}
