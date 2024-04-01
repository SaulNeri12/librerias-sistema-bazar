
package proveedores;

import java.util.List;
import objetosNegocio.Proveedor;

/**
 * Define las operaciones basicas para el subsistema de gestion de proveedores
 * @author saul
 */
public interface IGestorProveedores {
    
    /**
     * Regresa todos los proveedores registrados en el sistema.
     * @return 
     */
    public List<Proveedor> consultarTodos();
    
    /**
     * Regresa todos los proveedores que coincidan con el nombre especificado.
     * @param nombre Nombre del proveeedor.
     * @return 
     */
    public List<Proveedor> consultarProveedoresPorNombre(String nombre);
    
    /**
     * Busca y regresa un proveedor a partir de su ID en el sistema.
     * @param id ID del proveedor a buscar
     * @return 
     */
    public Proveedor consultarProveedor(Long id);
    
    /**
     * Busca y regresa un proveedor a partir de su numero telefonico.
     * @param telefono Telefono del proveedor a buscar.
     * @return 
     */
    public Proveedor consultarProveedorPorNumeroTelefono(String telefono);
    
    /**
     * Registra un proveedor en el sistema.
     * @param proveedor Informacion del proveedor a guardar.
     */
    public void registrarProveedor(Proveedor proveedor);
    
    /**
     * Modifica la informacion de un proveedor en el sistema.
     * @param proveedor Proveedor a modificar.
     */
    public void actualizarProveedor(Proveedor proveedor);
    
    /**
     * Elimina a un proveedor a partir de su ID.
     * @param id ID del proveedor a eliminar.
     */
    public void eliminarProveedor(Long id);
}
