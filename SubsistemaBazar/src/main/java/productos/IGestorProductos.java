
package productos;

import excepciones.DAOException;
import java.util.List;
import objetosNegocio.Producto;
import objetosNegocio.Proveedor;

/**
 * Define las operaciones basicas para el registro, modificacion y consulta
 * de productos.
 * @author saul
 */
public interface IGestorProductos {
    
    /**
     * Regresa una lista con todos los productos registrados en el sistema
     * @return 
     * @throws excepciones.DAOException en caso de error
     */
    public List<Producto> consultarTodos() throws DAOException;
    
    /**
     * Regresa una lista con productos en donde sus nombres coinciden con 
     * pasado en el nombre dado como parametro.
     * @param nombreProducto Nombre del producto a buscar
     * @return 
     * @throws excepciones.DAOException En caso de error
     */
    public List<Producto> consultarProductosPorNombre(String nombreProducto) throws DAOException;
    
    /**
     * Regresa una lista de productos que son suministrados por el proveedor
     * pasado como parametro
     * @param proveedor Proveedor de los productos a buscar
     * @return 
     * @throws excepciones.DAOException 
     */
    public List<Producto> consultarProductosPorProveedor(Proveedor proveedor) throws DAOException;
    
    /**
     * Registra un producto en el sistema.
     * @param producto 
     * @throws excepciones.DAOException en caso de que no se pueda registrar el producto
     */
    public void registrarProducto(Producto producto) throws DAOException;
    
    /**
     * Regresa el producto con el codigo especificado.
     * @param codigoProducto Codigo de producto (sistema)
     * @return Producto si se encuentra, null en caso contrario.
     * @throws excepciones.DAOException En caso de error.
     */
    public Producto consultarProducto(String codigoProducto) throws DAOException;
    
    /**
     * Obtiene un producto con el id del producto (codigo barras)
     * @param productoId ID del producto (codigo barras)
     * @return Producto si se encuentra, null en caso contrario.
     * @throws excepciones.DAOException en caso de error.
     */
    public Producto consultarProducto(Long productoId) throws DAOException;
    
    /**
     * Actualiza la informacion del producto dado.
     * @param producto Producto que se modificara
     * @throws excepciones.DAOException En caso de que no se pueda actualizar el producto o error.
     */
    public void actualizarProducto(Producto producto) throws DAOException;
    
    /**
     * Elimina el producto del sistema usando su codigo interno (sistema).
     * @param codigoProducto Codigo interno del producto (codigo sistema)
     * @throws excepciones.DAOException En caso de que no se pueda eliminar el producto.
     */
    public void eliminarProducto(String codigoProducto) throws DAOException;
}










