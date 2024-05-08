/*
 * ProductoDTO.java
 */
package objetosNegocio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;

/**
 * Contiene la informacion de un producto en el catalogo de productos.
 * 
 * @author rramirez
 */
public class ProductoDTO {
    private ObjectId _id;
    private Long codigoBarras;
    private String codigoInterno;
    private String nombre;
    private float precio;
    private LocalDateTime fechaRegistro;

    public ProductoDTO() {
    }

    /**
     * Crea una instancia con la informacion de un producto en el catalogo de
     * productos.
     * 
     * @param codigoBarras
     * @param codigoInterno
     * @param nombre        Nombre del producto.
     * @param precio        Precio del producto.
     * @param fechaRegistro Fecha de registro del producto en el sistema.
     */
    public ProductoDTO(Long codigoBarras, String codigoInterno, String nombre, float precio,
            LocalDateTime fechaRegistro) {
        this.codigoBarras = codigoBarras;
        this.codigoInterno = codigoInterno;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId id) {
        this._id = id;
    }
    
    public Long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Long id) {
        this.codigoBarras = id;
    }

    public void setCodigoInterno(String codigo) {
        this.codigoInterno = codigo;
    }

    public String getCodigoInterno() {
        return this.codigoInterno;
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

    @Override
    public String toString() {
        return "Producto{" + "codigoBarras=" + codigoBarras + ", codigo=" + codigoInterno + ", nombre=" + nombre
                + ", precio=" + precio + ", fecha_registro=" + fechaRegistro + '}';
    }

}
