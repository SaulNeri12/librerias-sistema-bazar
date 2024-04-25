
package subsistemas.interfaces;

import subsistemas.excepciones.DAOException;
import objetosNegocio.InventarioProductoDTO;
import objetosNegocio.ProductoDTO;

/**
 * Define las operaciones basicas para el manejo de inventario de productos.
 * @author saul
 */
public interface IGestorInventario {
    
    /** 
     * Obtiene el inventario del producto especificado.
     * @param producto Producto a buscar.
     * @return 
     * @throws DAOException en caso de error.
     */
    public InventarioProductoDTO obtenInventarioProducto(ProductoDTO producto) throws DAOException;
            
    /**
     * Registra la cantida unidades del producto especificado en el inventario.
     * @param producto Producto a actualizar su existencia.
     * @param cantidad Cantidad del producto a registrar.
     * @throws DAOException en caso de error.
     */
    public void registrarExistenciaProducto(ProductoDTO producto, int cantidad) throws DAOException;
    
    /**
     * Elimina todo registro y existencia del producto en el inventario
     * de productos.
     * @param producto Producto a eliminar.
     * @throws subsistemas.excepciones.DAOException
     */
    public void eliminarInventarioProducto(ProductoDTO producto) throws DAOException;
}
