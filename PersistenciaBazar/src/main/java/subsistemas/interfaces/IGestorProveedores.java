
package subsistemas.interfaces;


import java.util.List;
import objetosNegocio.ProveedorDTO;
import subsistemas.excepciones.DAOException;

/**
 * Define las operaciones basicas para el subsistema de gestion de proveedores
 * @author saul
 */
public interface IGestorProveedores {
    
    /**
     * Regresa todos los proveedores registrados en el sistema.
     * @return
     * @throws subsistemas.excepciones.DAOException
     */
    public List<ProveedorDTO> consultarTodos() throws DAOException;
    
    /**
     * Regresa todos los proveedores que coincidan con el nombre especificado.
     * @param nombre Nombre del proveeedor.
     * @return
     * @throws subsistemas.excepciones.DAOException
     */
    public List<ProveedorDTO> consultarProveedoresPorNombre(String nombre) throws DAOException;
    
    /**
     * Busca y regresa un proveedor a partir de su ID en el sistema.
     * @param id ID del proveedor a buscar
     * @return
     * @throws subsistemas.excepciones.DAOException
     */
    public ProveedorDTO consultarProveedor(Long id) throws DAOException;
    
    /**
     * Busca y regresa un proveedor a partir de su numero telefonico.
     * @param telefono Telefono del proveedor a buscar.
     * @return
     * @throws subsistemas.excepciones.DAOException
     */
    public ProveedorDTO consultarProveedorPorNumeroTelefono(String telefono) throws DAOException;
    
    /**
     * Registra un proveedor en el sistema.
     * @param proveedor Informacion del proveedor a guardar.
     * @throws subsistemas.excepciones.DAOException
     */
    public void registrarProveedor(ProveedorDTO proveedor) throws DAOException;
   
    /**
     * Modifica la informacion de un proveedor en el sistema.
     * @param proveedor Proveedor a modificar.
     * @throws subsistemas.excepciones.DAOException
     */
    public void actualizarProveedor(ProveedorDTO proveedor) throws DAOException;
    
    /**
     * Elimina a un proveedor a partir de su ID.
     * @param id ID del proveedor a eliminar.
     * @throws subsistemas.excepciones.DAOException
     */
    public void eliminarProveedor(Long id) throws DAOException;
}
