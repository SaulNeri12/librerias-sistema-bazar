/*
 * Producto.java
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import objetosNegocio.ProductoDTO;

/**
 * 
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "codigo_barras", unique = true, nullable = false)
    private Long codigoBarras;
    
    @Column(name = "codigo_interno", unique = true, nullable = false)
    private String codigoInterno;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "precio")
    private Float precio;
    
    @Column(name = "fecha_registro", columnDefinition = "DATE")
    private LocalDateTime fechaRegistro;
    
    public Producto() {
        
    }
    
    public void setCodigoInterno(String codigo) {
        this.codigoInterno = codigo;
    }

    public String getCodigoInterno() {
        return this.codigoInterno;
    }
    
    @Override
    public String toString() {
        return "entidades.Producto[ codigoBarras=" + codigoBarras + ", codigoInterno=" + codigoInterno + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Long codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    
    public ProductoDTO toDTO() {
        ProductoDTO p = new ProductoDTO();
        
        p.setCodigoBarras(this.codigoBarras);
        p.setCodigoInterno(this.codigoInterno);
        p.setFechaRegistro(this.fechaRegistro);
        p.setPrecio(this.precio);
        p.setNombre(this.nombre);
        // TODO: Agregar la lista de proveedores si es necesario...
        
        return p;
    }
}
