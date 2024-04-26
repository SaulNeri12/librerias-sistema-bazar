/*
 * Usuario.java
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import objetosNegocio.UsuarioDTO;

/**
 * Clase de entidad con a información de usuarios en la base de datos
 * @author Juventino López García
 */
@Entity
@Table(name = "usuarios")
@NamedQuery(name = "consultaUsuarioID", query = "SELECT u FROM Usuario u WHERE u.id = :id")
@NamedQuery(name = "consultaUsuarioNumTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")
@NamedQuery(name = "inicioSesion", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono AND u.contrasenha = :contrasena")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable=false)
    private String nombre;
    
    @Column(name = "apellido", nullable=false)
    private String apellido;
    
    @Column(name = "contrasenha", nullable=false)
    private String contrasenha;
    
    @Column(name = "puesto", nullable=false)
    @Enumerated(EnumType.STRING)
    private Puesto puesto;
    
    @Column(name = "telefono", unique = true, nullable=false)
    private String telefono;
    
    @Column(name = "fecha_contratacion", columnDefinition = "DATE")
    private LocalDateTime fechaContratacion;
    
    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;
    
    @Embedded
    @OneToOne(mappedBy = "usuario")
    private Direccion direccion;
    
    public static enum Puesto {
        CAJERO,
        GERENTE,
        ADMIN
    }

    public Usuario() {
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

    public void setNombre(String nombres) {
        this.nombre = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getContrasena() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDateTime fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    public UsuarioDTO toDTO() {
        UsuarioDTO u = new UsuarioDTO();
        
        u.setId(id);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setTelefono(telefono);
        u.setContrasena(contrasenha);
        u.setFechaContratacion(fechaContratacion);
        u.setPuesto(UsuarioDTO.Puesto.valueOf(this.puesto.name()));
        u.setDireccion(direccion.toDTO());
        
        return u;
    }

}
