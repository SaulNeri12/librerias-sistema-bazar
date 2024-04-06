
package proveedores;

import excepciones.DAOException;
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
     * @throws excepciones.DAOException En caso de error
     */
    public List<Proveedor> consultarTodos() throws DAOException;
    
    /**
     * Regresa todos los proveedores que coincidan con el nombre especificado.
     * @param nombre Nombre del proveeedor.
     * @return 
     * @throws excepciones.DAOException En caso de error
     */
    public List<Proveedor> consultarProveedoresPorNombre(String nombre) throws DAOException;
    
    /**
     * Busca y regresa un proveedor a partir de su ID en el sistema.
     * @param id ID del proveedor a buscar
     * @return 
     * @throws excepciones.DAOException En caso de error.
     */
    public Proveedor consultarProveedor(Long id) throws DAOException;
    
    /**
     * Busca y regresa un proveedor a partir de su numero telefonico.
     * @param telefono Telefono del proveedor a buscar.
     * @return 
     * @throws excepciones.DAOException En caso de error.
     */
    public Proveedor consultarProveedorPorNumeroTelefono(String telefono) throws DAOException;
    
    /**
     * Registra un proveedor en el sistema.
     * @param proveedor Informacion del proveedor a guardar.
     * @throws excepciones.DAOException En caso de que no se pueda registrar el proveedor.
     */
    public void registrarProveedor(Proveedor proveedor) throws DAOException;
    
    /**
     * Modifica la informacion de un proveedor en el sistema.
     * @param proveedor Proveedor a modificar.
     * @throws excepciones.DAOException En caso de que no se pueda actualizar el proveedor
     */
    public void actualizarProveedor(Proveedor proveedor) throws DAOException;
    
    /**
     * Elimina a un proveedor a partir de su ID.
     * @param id ID del proveedor a eliminar.
     * @throws excepciones.DAOException en caso de que no se puea eliminar el proveedor.
     */
    public void eliminarProveedor(Long id) throws DAOException;
}
