/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import java.io.Serializable;
import java.util.List;

/*
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
*/

/**
 * Contiene la informacion de una venta a un cliente.
 * @author rramirez
*/
public class Venta implements Serializable {

//public class Venta {

    /*
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    */
    private Long id;

    private String nombreCliente;
    private String apellidoCliente;
    private float montoTotal;

    //@Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    //@ManyToOne
    //@JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private List<DetalleVenta> productosVendidos;
    
    public Venta() {
        
    }
        
    /**
     * Crea una instancia con la informacion de una venta a un cliente.
     * @param nombreCliente Nombre del cliente
     * @param apellidoCliente Apellido/s del cliente.
     * @param montoTotal Monto total de la venta.
     * @param metodoPago Metodo de pago realizado.
     * @param usuario Usuario a cargo de la transaccion.
     * @param productosVendidos Lista de los productos vendidos.
     */
    public Venta(String nombreCliente, String apellidoCliente, float montoTotal, MetodoPago metodoPago, Usuario usuario, List<DetalleVenta> productosVendidos) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.productosVendidos = productosVendidos;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setProductosVendidos(List<DetalleVenta> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }
    
    public List<DetalleVenta> getProductosVendidos() {
        return this.productosVendidos;
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
