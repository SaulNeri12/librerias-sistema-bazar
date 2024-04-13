/*
 * Venta.java
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Juventino López García
 */
@Entity
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "apellido_paterno_cliente")
    private String apellidoPaternoCliente;
    
    @Column(name = "apellido_materno_cliente")
    private String apellidoMaternoCliente;
    
    @Column(name = "monto_total")
    private Float montoToal;

    @Column(name = "metodo_pago")
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "venta")
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

    public String getApellidoPaternoCliente() {
        return apellidoPaternoCliente;
    }

    public void setApellidoPaternoCliente(String apellidoPaternoCliente) {
        this.apellidoPaternoCliente = apellidoPaternoCliente;
    }

    public String getApellidoMaternoCliente() {
        return apellidoMaternoCliente;
    }

    public void setApellidoMaternoCliente(String apellidoMaternoCliente) {
        this.apellidoMaternoCliente = apellidoMaternoCliente;
    }

    public Float getMontoToal() {
        return montoToal;
    }

    public void setMontoToal(Float montoToal) {
        this.montoToal = montoToal;
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

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

}
