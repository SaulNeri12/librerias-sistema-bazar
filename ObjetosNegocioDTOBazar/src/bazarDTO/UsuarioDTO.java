
package bazarDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import objetosNegocio.Usuario;

/*
 * Crea una vista que contiene la informacion basica de un usuario.
 * @author saul
 */
public class UsuarioDTO implements Serializable {

    private final Long id;
    private final String nombre;
    private final LocalDateTime fechaContratacion;
    private final String puesto;
    private final String telefono;
    
    /**
     * Crea un DTO de un usuario en base a la informacion proporciada por el 
     * objeto Usuario.
     * @param usuario Usuario.
     */
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.fechaContratacion = usuario.getFechaContratacion();
        this.puesto = conviertePuestoATexto(usuario.getPuesto());
        this.telefono = usuario.getTelefono();
    }
    
    /**
     * Crea un DTO que contiene la informacion basica de un usuario.
     * @param id ID del usuario en el sistema.
     * @param nombre Nombre completo del usuario.
     * @param fechaContratacion Fecha y hora de contratacion del empleado.
     * @param puesto Puesto del empleado en el sistema.
     * @param telefono Numero telefonico del empleado.
     */
    public UsuarioDTO(Long id, String nombre, LocalDateTime fechaContratacion, Usuario.Puesto puesto, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.fechaContratacion = fechaContratacion;
        this.puesto = conviertePuestoATexto(puesto);
        this.telefono = telefono;
    }

    private String conviertePuestoATexto(Usuario.Puesto puesto) {
        if (puesto == Usuario.Puesto.ADMIN) {
            return "ADMINISTRADOR";
        }
        if (puesto == Usuario.Puesto.GERENTE) {
            return "GERENTE";
        }
        if (puesto == Usuario.Puesto.CAJERO) {
            return "CAJERO";
        }
        
        return "NO DEFINIDO";
    }
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getTelefono() {
        return this.telefono;
    }
    
    public LocalDateTime getFechaContratacion() {
        return this.fechaContratacion;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido_paterno=" + ", fecha_contratacion=" + fechaContratacion + ", puesto=" + puesto + ", telefono=" + telefono + '}';
    }

}