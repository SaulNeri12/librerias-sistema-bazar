/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package inventario;

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
     */
    public InventarioProducto obtenInventarioProducto(Producto producto);
            
    /**
     * Registra la cantida unidades del producto especificado en el inventario.
     * @param producto Producto a actualizar su existencia.
     * @param cantidad Cantidad del producto a registrar.
     */
    public void registrarExistenciaProducto(Producto producto, int cantidad);
    
    /**
     * Elimina todo registro y existencia del producto en el inventario
     * de productos.
     * @param producto Producto a eliminar.
     */
    public void eliminarInventarioProducto(Producto producto);
}
