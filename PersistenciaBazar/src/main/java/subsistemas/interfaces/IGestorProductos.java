
package subsistemas.interfaces;

import subsistemas.excepciones.DAOException;
import java.util.List;
import objetosNegocio.ProductoDTO;
import objetosNegocio.ProveedorDTO;

/**
 * Define las operaciones basicas para el registro, modificacion y consulta
 * de productos.
 * @author saul
 */
public interface IGestorProductos {
    
    /**
     * Regresa una lista con todos los productos registrados en el sistema
     * @return 
     * @throws subsistemas.excepciones.DAOException
     */
    public List<ProductoDTO> consultarTodos() throws DAOException;
    
    /**
     * Regresa una lista con productos en donde sus nombres coinciden con 
     * pasado en el nombre dado como parametro.
     * @param nombreProducto Nombre del producto a buscar
     * @return
     * @throws subsistemas.excepciones.DAOException
     */
    public List<ProductoDTO> consultarProductosPorNombre(String nombreProducto) throws DAOException;
    
    /**
     * Regresa una lista de productos que son suministrados por el proveedor
     * pasado como parametro
     * @param proveedor Proveedor de los productos a buscar
     * @return 
     * @throws subsistemas.excepciones.DAOException 
     */
    public List<ProductoDTO> consultarProductosPorProveedor(ProveedorDTO proveedor) throws DAOException;
    
    /**
     * Registra un producto en el sistema.
     * @param producto
     * @throws subsistemas.excepciones.DAOException
     */
    public void registrarProducto(ProductoDTO producto) throws DAOException;
    
    /**
     * Regresa el producto con el codigo especificado.
     * @param codigoProducto Codigo de producto (sistema)
     * @return Producto si se encuentra, null en caso contrario.
     * @throws subsistemas.excepciones.DAOException
     */
    public ProductoDTO consultarProducto(String codigoProducto) throws DAOException;
    
    /**
     * Obtiene un producto con el id del producto (codigo barras)
     * @param productoId ID del producto (codigo barras)
     * @return Producto si se encuentra, null en caso contrario.
     * @throws subsistemas.excepciones.DAOException
     */
    public ProductoDTO consultarProducto(Long productoId) throws DAOException;
    
    /**
     * Actualiza la informacion del producto dado.
     * @param producto Producto que se modificara
     * @throws subsistemas.excepciones.DAOException
     */
    public void actualizarProducto(ProductoDTO producto) throws DAOException;
    
    /**
     * Elimina el producto del sistema usando su codigo interno (sistema).
     * @param codigoProducto Codigo interno del producto (codigo sistema)
     * @throws subsistemas.excepciones.DAOException
     */
    public void eliminarProducto(String codigoProducto) throws DAOException;
}










