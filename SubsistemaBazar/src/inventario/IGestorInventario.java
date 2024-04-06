/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package inventario;

import excepciones.DAOException;
import objetosNegocio.InventarioProducto;
import objetosNegocio.Producto;

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
    public InventarioProducto obtenInventarioProducto(Producto producto) throws DAOException;
            
    /**
     * Registra la cantida unidades del producto especificado en el inventario.
     * @param producto Producto a actualizar su existencia.
     * @param cantidad Cantidad del producto a registrar.
     * @throws DAOException en caso de error.
     */
    public void registrarExistenciaProducto(Producto producto, int cantidad) throws DAOException;
    
    /**
     * Elimina todo registro y existencia del producto en el inventario
     * de productos.
     * @param producto Producto a eliminar.
     * @throws excepciones.DAOException en caso de error.
     */
    public void eliminarInventarioProducto(Producto producto) throws DAOException;
}
