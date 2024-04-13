
package ventas;

import excepciones.DAOException;
import java.time.LocalDate;
import java.util.List;
import objetosNegocio.VentaDTO;

/**
 * Define las operaciones basicas para gestionar ventas en el sistema.
 * @author saul
 */
public interface IGestorVentas {
    
    /**
     * Busca una venta a partir de su ID.
     * @param id numero identificador de la venta.
     * @return Venta si se encuentra, null en caso contrario.
     * @throws excepciones.DAOException En caso de error
     */
    public VentaDTO consultarVenta(Long id) throws DAOException;
    
    /**
     * Regresa una lista de las ventas hechas por el usuario con el ID especificado.
     * @param usuarioId ID del usuario
     * @return 
     * @throws excepciones.DAOException En caso de error.
     */
    public List<VentaDTO> consultarVentasDeUsuario(Long usuarioId) throws DAOException;
    
    /**
     * Regresa una lista de ventas en el periodo especificado.
     * @param inicio Inicio del periodo.
     * @param fin Fin del periodo
     * @return 
     * @throws excepciones.DAOException En caso de error.
     */
    public List<VentaDTO> consultarVentasPorPeriodo(LocalDate inicio, LocalDate fin) throws DAOException;
    
    /**
     * Regresa una lista con todas las ventas existentes.
     * @return 
     * @throws excepciones.DAOException En caso de error.
     */
    public List<VentaDTO> consultarTodos() throws DAOException;
    
    /**
     * Registra una nueva venta.
     * @param venta Venta a guardar.
     * @throws excepciones.DAOException En caso de que no se pueda registrar 
     * la venta.
     */
    public void registrarVenta(VentaDTO venta) throws DAOException;
    
    /**
     * Modifica la informacion de la venta en cuestion.
     * @param venta Venta a modificar.
     * @throws excepciones.DAOException En caso de que no se pueda actualizar
     * la venta.
     */
    public void actualizarVenta(VentaDTO venta) throws DAOException;
    
    /**
     * Elimina una venta con el ID de la venta especificado.
     * @param ventaId ID de la venta a eliminar.
     * @throws excepciones.DAOException En caso de que no se pueda eliminar 
     * la venta.
     */
    public void eliminarVenta(Long ventaId) throws DAOException;
}











