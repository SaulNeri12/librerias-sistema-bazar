
package bazarDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import objetosNegocio.Producto;
import objetosNegocio.Proveedor;

/**
 * Crea una vista con la informacion basica de un producto.
 * @author saul
*/
public class ProductoDTO implements Serializable {

    private final Long id;
    private final String codigo;
    private final String nombre;
    private final float precio;
    private final LocalDateTime fechaRegistro;
    private List<String> clavesProveedores;

    /**
     * Crea un DTO de un producto en base a la informacion del objeto Producto
     * dado.
     * @param producto Producto.
     */
    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
        this.codigo = producto.getCodigo();
        this.fechaRegistro = producto.getFechaRegistro();
        
        if (producto.obtenerProveedores() != null) {
            for (Proveedor p: producto.obtenerProveedores()) {
                this.clavesProveedores.add(p.getId().toString());
            }
        }
    }
            
    
    /**
     * Crea un DTO que contiene la informacion basica de un producto.
     * @param id Codigo de barras del producto.
     * @param codigo Codigo interno del producto (sistema).
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param fechaRegistro Fecha y hora en que se registro el producto.
     * @param clavesProveedores Claves de los proveedores que venden
     * dicho producto.
     */
    public ProductoDTO(Long id, String codigo, String nombre, float precio, LocalDateTime fechaRegistro, List<String> clavesProveedores) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
        this.clavesProveedores = clavesProveedores;
    }

    public Long getId() {
        return id;
    }
    
    public String getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public LocalDateTime getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public List<String> obtenerClavesProveedores() {
        return this.clavesProveedores;
    }
    
    public String obtenerClavesProveedoresEnTexto() {
        String res = "";
        
        for (String claveProveedor: this.clavesProveedores) {
            res += claveProveedor;
            res += ", ";
        }
        
        return res;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", fecha_registro=" + fechaRegistro + '}';
    }

}
