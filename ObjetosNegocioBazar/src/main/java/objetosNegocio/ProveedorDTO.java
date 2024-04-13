/*
 * ProveedorDTO.java
 */
package objetosNegocio;

import java.time.LocalDateTime;


/**
 * Contiene la informacion de un proveedor.
 * @author rramirez
*/
public class ProveedorDTO {
    private Long id;

    private String nombre;
    private String telefono;
    private String email;
    private String descripcion;
    private LocalDateTime fechaRegistro;

    public ProveedorDTO() {
    }

    /**
     * Crea una instancia con la informacion de un proveedor especificada.
     * @param nombre Nombre del proveedor o negocio.
     * @param telefono Numero telefonico del proveedor o negocio.
     * @param email Correo electronico del proveedor o negocio.
     * @param descripcion Descripcion del proveedor o negocio.
     * @param fechaRegistro Fecha de registro del proveedor en el sistema.
     */
    public ProveedorDTO(String nombre, String telefono, String email, String descripcion, LocalDateTime fechaRegistro) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
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

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", descripcion=" + descripcion + ", fecha_registro=" + fechaRegistro + '}';
    }

}
