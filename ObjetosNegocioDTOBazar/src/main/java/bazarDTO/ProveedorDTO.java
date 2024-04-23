
package bazarDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import objetosNegocio.Proveedor;

/**
 * Crea una vista que contiene la informacion basica de un proveedor.
 * @author saul
*/
public class ProveedorDTO implements Serializable {

    private final Long id;
    private final String nombre;
    private final String telefono;
    private final String email;
    private final String descripcion;
    private final LocalDateTime fechaRegistro;

    /**
     * Crea un DTO de un proveedor en base a la informacion del objeto Proveedor
     * proporcinado.
     * @param proveedor Proveedor.
     */
    public ProveedorDTO(Proveedor proveedor) {
        this.id = proveedor.getId();
        this.nombre = proveedor.getNombre();
        this.telefono = proveedor.getTelefono();
        this.email = proveedor.getEmail();
        this.descripcion = proveedor.getDescripcion();
        this.fechaRegistro = proveedor.getFechaRegistro();
    }
    
    /**
     * Crea un DTO con la informacion basica de un proveedor.
     * @param id ID del proveedor.
     * @param nombre Nombre del proveedor o negocio.
     * @param telefono Numerico telefonico del proveedor o negocio.
     * @param email Correo electronico del proveedor o negocio.
     * @param descripcion Descripcion del proveedor o negocio.
     * @param fechaRegistro Fecha de registro del proveedor o negocio en el
     * sistema.
     */
    public ProveedorDTO(Long id, String nombre, String telefono, String email, String descripcion, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", descripcion=" + descripcion + ", fecha_registro=" + fechaRegistro + '}';
    }
}
