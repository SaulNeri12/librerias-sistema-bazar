/*
 * Direccion.java
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Juventino López García
 */
@Embeddable
public class Direccion implements Serializable {

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "calle")
    private String calle;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "numero_edificio")
    private Integer numeroEdificio;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    public Direccion() {
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getNumeroEdificio() {
        return numeroEdificio;
    }

    public void setNumeroEdificio(Integer numeroEdificio) {
        this.numeroEdificio = numeroEdificio;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
