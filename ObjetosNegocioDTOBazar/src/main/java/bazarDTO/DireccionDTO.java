
package bazarDTO;

import java.io.Serializable;
import objetosNegocio.Direccion;

/**
 * Crea una vista de la informacion de una direccion.
 * @author saul
 */
public class DireccionDTO implements Serializable {

    private final String ciudad;
    private final String numeroEdificio;
    private final String calle;
    private final String colonia;
    private final String codigoPostal;
    
    /**
     * Crea un DTO de direccion en base a la informacion proporcianada por el
     * objeto Direccion dado.
     * @param direccion Direccion.
     */
    public DireccionDTO(Direccion direccion) {
        this.ciudad = direccion.getCiudad();
        this.numeroEdificio = direccion.getNumeroEdificio();
        this.calle = direccion.getCalle();
        this.colonia = direccion.getColonia();
        this.codigoPostal = direccion.getCodigoPostal();
    }
    
    /**
     * Crea un DTO de una direccion con la informacion proporcionada.
     * @param ciudad Ciudad de la direccion.
     * @param numeroEdificio Numero de edificio.
     * @param calle Calle.
     * @param colonia Colonia.
     * @param codigoPostal Codigo postal.
     */
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

    public String getNumeroEdificio() {
        return numeroEdificio;
    }
    
    public String getCalle() {
        return calle;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    @Override
    public String toString() {
        return "Direccion{" + "ciudad=" + ciudad + ", numero_edificio=" + numeroEdificio + ", calle=" + calle + ", colonia=" + colonia + ", codigo_postal=" + codigoPostal + '}';
    }

}
