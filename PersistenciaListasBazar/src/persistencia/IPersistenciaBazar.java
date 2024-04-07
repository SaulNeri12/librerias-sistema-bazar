
package persistencia;

import java.util.List;
import objetosNegocio.Producto;
import objetosNegocio.Usuario;
import objetosNegocio.Venta;
import persistencia.excepciones.PersistenciaBazarException;

/**
 * Contiene todas las operaciones necesarias para el sistema de ventas.
 * @author saul
 */
public interface IPersistenciaBazar {
    
    public Producto consultarProductoPorCodigo(String codigoInterno) throws PersistenciaBazarException;;
    
    public Producto consultarProductoPorCodigoBarras(Long codigoBarras) throws PersistenciaBazarException;;
    
    public List<Producto> consultarProductosTodos() throws PersistenciaBazarException;
    
    public List<Producto> consultarProductosPorNombre(String nombreProducto) throws PersistenciaBazarException;
    
    public void registrarVenta(Venta venta) throws PersistenciaBazarException;
    
    public Usuario iniciarSesionUsuario(Usuario usuario) throws PersistenciaBazarException;
}
