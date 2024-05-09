/*
 * Proveedor.java
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import objetosDTO.ProveedorDTO;

/**
 * 
 * @author Juventino López García
 */
@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "fecha_registro", columnDefinition = "DATE")
    private LocalDateTime fechaRegistro;
    
    /*
    @OneToMany(mappedBy = "proveedor")
    private List<Compra> compras;
*/
    
    @Embedded
    @OneToOne(mappedBy = "proveedor")
    private Direccion direccion;

    public Proveedor() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /*
    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }*/

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    public ProveedorDTO toDTO() {
        ProveedorDTO p = new ProveedorDTO();
        
        p.setId(id);
        p.setDescripcion(descripcion);
        p.setEmail(email);
        p.setNombre(nombre);
        p.setTelefono(telefono);
        p.setDireccion(direccion.toDTO());
        
        return p;
    }

}
