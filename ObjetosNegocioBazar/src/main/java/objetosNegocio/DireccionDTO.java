/*
 * DireccionDTO.java
 */
package objetosNegocio;

/**
 * Representa la direccion fisica de un proveedor (negocio) o de usuario.
 * @author rramirez
 */
public class DireccionDTO {
    private String ciudad;
    private String numeroEdificio;
    private String calle;
    private String colonia;
    private String codigoPostal;

    public DireccionDTO() {

    }

    public DireccionDTO(String ciudad, String numeroEdificio, String calle, String colonia, String codigoPostal) {
        this.ciudad = ciudad;
        this.numeroEdificio = numeroEdificio;
        this.calle = calle;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

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
