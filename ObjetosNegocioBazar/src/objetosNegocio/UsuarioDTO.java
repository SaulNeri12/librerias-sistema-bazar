/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/

/*
 * Contiene la informacion de un usuario (empleado o administrador).
 * @author rramirez
*/
public class Usuario implements Serializable {

//public class Usuario {

    /*
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    */
    private Long id;

    private String nombre;
    private LocalDateTime fechaContratacion;
    //@Enumerated(EnumType.STRING)
    private Puesto puesto;
    private String telefono;
    private String contrasena;

    public static enum Puesto {
        CAJERO,
        GERENTE,
        ADMIN
    }
    
    public Usuario() {
        
    }
    
    /**
     * Crea una instancia con la informacion de un usuario (empleado como cajero, gerente o administrador).
     * @param nombre Nombre completo del usuario
     * @param fechaContratacion Fecha de contratacion del usuario (empleado).
     * @param puesto Puesto del usuario (empleado).
     * @param telefono Numero telefonico de contacto del usuario (empleado).
     * @param contrasena Contrasena del usuario (empleado) en el sistema.
     */
    public Usuario(String nombre, LocalDateTime fechaContratacion, Puesto puesto, String telefono, String contrasena) {
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario))
        {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido_paterno=" + ", fecha_contratacion=" + fechaContratacion + ", puesto=" + puesto + ", telefono=" + telefono + ", contrasena=" + contrasena + '}';
    }

}