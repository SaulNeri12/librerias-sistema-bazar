/*
 * ProductoDTO.java
 */
package objetosNegocio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contiene la informacion de un producto en el catalogo de productos.
 * @author rramirez
*/
public class ProductoDTO {

    private Long id;
    private String codigo;
    private String nombre;
    private float precio;
    private LocalDateTime fechaRegistro;
    private List<ProveedorDTO> proveedores;

    public ProductoDTO() {
        proveedores = new ArrayList<>();
    }

    /**
     * Crea una instancia con la informacion de un producto en el catalogo de
     * productos.
     * @param codigo Codigo interno del producto (no codigo de barras o ID)
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param fechaRegistro Fecha de registro del producto en el sistema.
     * @param proveedores Proveedores que venden dicho producto.
     */
    public ProductoDTO(String codigo, String nombre, float precio, LocalDateTime fechaRegistro, List<ProveedorDTO> proveedores) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
        this.proveedores = proveedores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public void agregarProveedor(ProveedorDTO proveedor) {
        this.proveedores.add(proveedor);
    }
    
    public void quitarProveedor(ProveedorDTO proveedor) {
        this.proveedores.removeIf(p -> Objects.equals(p.getId(), proveedor.getId()) 
                || p.getNombre().toLowerCase().equals(proveedor.getNombre().toLowerCase())
                || p.getEmail().equals(proveedor.getEmail())
        );
    }
    
    public List<ProveedorDTO> obtenerProveedores() {
        return this.proveedores;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", fecha_registro=" + fechaRegistro + '}';
    }

}
