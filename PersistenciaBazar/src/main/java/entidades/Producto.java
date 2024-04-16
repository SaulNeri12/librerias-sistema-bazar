/*
 * Producto.java
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "codigo_interno", unique = true, nullable = false)
    private String codigoInterno;
    
    @Column(name = "codigo_barras", unique = true, nullable = false)
    private Long codigoBarras;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "precio")
    private Float precio;
    
    @Column(name = "fecha_registro", columnDefinition = "DATE")
    private LocalDateTime fechaRegistro;
    
    public Producto() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigoInterno(String codigo) {
        this.codigoInterno = codigo;
    }

    public String getCodigoInterno() {
        return this.codigoInterno;
    }
    
    @Override
    public String toString() {
        return "entidades.Producto[ id=" + id + ", codigo=" + codigoInterno + " ]";
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
}
