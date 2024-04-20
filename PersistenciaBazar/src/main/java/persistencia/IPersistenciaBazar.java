
package persistencia;

import java.util.List;
import objetosNegocio.ProductoDTO;
import objetosNegocio.UsuarioDTO;
import objetosNegocio.VentaDTO;
import persistencia.excepciones.PersistenciaBazarException;

/**
 * Contiene todas las operaciones necesarias para el sistema de ventas.
 * @author saul
 */
public interface IPersistenciaBazar {
    
    public ProductoDTO consultarProductoPorCodigo(String codigoInterno) throws PersistenciaBazarException;;
    
    public ProductoDTO consultarProductoPorCodigoBarras(Long codigoBarras) throws PersistenciaBazarException;;
    
    public List<ProductoDTO> consultarProductosTodos() throws PersistenciaBazarException;
    
    public List<ProductoDTO> consultarProductosPorNombre(String nombreProducto) throws PersistenciaBazarException;
    
    public void registrarVenta(VentaDTO venta) throws PersistenciaBazarException;
    
    public UsuarioDTO iniciarSesionUsuario(String telefono, String contrasena) throws PersistenciaBazarException;
}
