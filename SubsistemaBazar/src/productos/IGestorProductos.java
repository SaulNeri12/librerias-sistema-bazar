
package productos;

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
     */
    public List<Producto> consultarTodos();
    
    /**
     * Regresa una lista con productos en donde sus nombres coinciden con 
     * pasado en el nombre dado como parametro.
     * @param nombreProducto Nombre del producto a buscar
     * @return 
     */
    public List<Producto> consultarProductosPorNombre(String nombreProducto);
    
    /**
     * Regresa una lista de productos que son suministrados por el proveedor
     * pasado como parametro
     * @param proveedor Proveedor de los productos a buscar
     * @return 
     */
    public List<Producto> consultarProductosPorProveedor(Proveedor proveedor);
    
    /**
     * Registra un producto en el sistema.
     * @param producto 
     */
    public void registrarProducto(Producto producto);
    
    /**
     * Regresa el producto con el codigo especificado.
     * @param codigoProducto Codigo de producto (sistema)
     * @return Producto si se encuentra, null en caso contrario.
     */
    public Producto consultarProducto(String codigoProducto);
    
    /**
     * Obtiene un producto con el id del producto (codigo barras)
     * @param productoId ID del producto (codigo barras)
     * @return Producto si se encuentra, null en caso contrario.
     */
    public Producto consultarProducto(Long productoId);
    
    /**
     * Actualiza la informacion del producto dado.
     * @param producto Producto que se modificara
     */
    public void actualizarProducto(Producto producto);
    
    /**
     * Elimina el producto del sistema usando su codigo interno (sistema).
     * @param codigoProducto Codigo interno del producto (codigo sistema)
     */
    public void eliminarProducto(String codigoProducto);
}










