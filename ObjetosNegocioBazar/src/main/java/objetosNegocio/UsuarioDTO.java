/*
 * UsuarioDTO.java
 */
package objetosNegocio;

import java.time.LocalDateTime;

/*
 * Contiene la informacion de un usuario (empleado o administrador).
 * @author rramirez
 */
public class UsuarioDTO {

    private Long id;

    private String nombre;
    private LocalDateTime fechaContratacion;
    private Puesto puesto;
    private String telefono;
    private String contrasena;

    public static enum Puesto {
        CAJERO,
        GERENTE,
        ADMIN
    }

    public UsuarioDTO() {

    }

    /**
     * Crea una instancia con la informacion de un usuario (empleado como
     * cajero, gerente o administrador).
     *
     * @param nombre Nombre completo del usuario
     * @param fechaContratacion Fecha de contratacion del usuario (empleado).
     * @param puesto Puesto del usuario (empleado).
     * @param telefono Numero telefonico de contacto del usuario (empleado).
     * @param contrasena Contrasena del usuario (empleado) en el sistema.
     */
    public UsuarioDTO(String nombre, LocalDateTime fechaContratacion, Puesto puesto, String telefono, String contrasena) {
        this.nombre = nombre;
        this.fechaContratacion = fechaContratacion;
        this.puesto = puesto;
        this.telefono = telefono;
        this.contrasena = contrasena;
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

    public void setFechaContratacion(LocalDateTime fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public LocalDateTime getFechaContratacion() {
        return this.fechaContratacion;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido_paterno=" + ", fecha_contratacion=" + fechaContratacion + ", puesto=" + puesto + ", telefono=" + telefono + ", contrasena=" + contrasena + '}';
    }

}
