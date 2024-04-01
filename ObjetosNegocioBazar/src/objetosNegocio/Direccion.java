/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import java.io.Serializable;

/*
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
*/
/**
 * Representa la direccion fisica de un proveedor (negocio) o de usuario.
 * @author rramirez
 */
//@Entity
//@Table(name = "Direccion")
public class Direccion implements Serializable {
//public class Direccion {

    //private static final long serialVersionUID = 1L;
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    private String ciudad;
    private String numeroEdificio;
    private String calle;
    private String colonia;
    private String codigoPostal;

    //@ManyToOne
    //@JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    //private Proveedor proveedor;

    //@ManyToOne
    //@JoinColumn(name = "usuario_id", referencedColumnName = "id")
    //private Usuario usuario;

    public Direccion() {

    }

    public Direccion(String ciudad, String numeroEdificio, String calle, String colonia, String codigoPostal) {
        this.ciudad = ciudad;
        this.numeroEdificio = numeroEdificio;
        this.calle = calle;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    /*
    public Long getId() {
        return id;
    }
    */

    /*
    public void setId(Long id) {
        this.id = id;
    }
    */

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNumeroEdificio() {
        return numeroEdificio;
    }

    public void setNumeroEdificio(String numero) {
        this.numeroEdificio = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigo_postal) {
        this.codigoPostal = codigo_postal;
    }

    @Override
    public String toString() {
        return "Direccion{" + "ciudad=" + ciudad + ", numero_edificio=" + numeroEdificio + ", calle=" + calle + ", colonia=" + colonia + ", codigo_postal=" + codigoPostal + '}';
    }

}
