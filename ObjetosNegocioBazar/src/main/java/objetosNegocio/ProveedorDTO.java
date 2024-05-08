/*
 * ProveedorDTO.java
 */
package objetosNegocio;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

/**
 * Contiene la informacion de un proveedor.
 * 
 * @author rramirez
 */
public class ProveedorDTO {
    private ObjectId _id;
    private Long id;

    private String nombre;
    private String telefono;
    private String email;
    private String descripcion;
    private LocalDateTime fechaRegistro;
    private DireccionDTO direccion;

    public ProveedorDTO() {
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public DireccionDTO getDireccion() {
        return this.direccion;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId id) {
        this._id = id;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email
                + ", descripcion=" + descripcion + ", fecha_registro=" + fechaRegistro + '}';
    }

}
