
package subsistemas.interfaces;

import subsistemas.excepciones.DAOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import objetosDTO.VentaDTO;

/**
 * Define las operaciones basicas para gestionar ventas en el sistema.
 * @author saul
 */
public interface IGestorVentas {
    
    /**
     * Busca una venta a partir de su ID.
     * @param id numero identificador de la venta.
     * @return Venta si se encuentra, null en caso contrario.
     * @throws subsistemas.excepciones.DAOException
     */
    public VentaDTO consultarVenta(Long id) throws DAOException;
    
    /**
     * Regresa una lista de las ventas hechas por el usuario con el ID especificado.
     * @param usuarioId ID del usuario
     * @return
     * @throws subsistemas.excepciones.DAOException
     */
    public List<VentaDTO> consultarVentasDeUsuario(Long usuarioId) throws DAOException;
    
    /**
     * Regresa una lista de ventas en el periodo especificado.
     * @param inicio Inicio del periodo.
     * @param fin Fin del periodo
     * @return
     * @throws subsistemas.excepciones.DAOException
     */
    public List<VentaDTO> consultarVentasPorPeriodo(LocalDateTime inicio, LocalDateTime fin) throws DAOException;
    
    /**
     * Regresa una lista con todas las ventas existentes.
     * @return
     * @throws subsistemas.excepciones.DAOException
     */
    public List<VentaDTO> consultarTodos() throws DAOException;
    
    /**
     * Registra una nueva venta.
     * @param venta Venta a guardar.
     * @throws subsistemas.excepciones.DAOException
     */
    public void registrarVenta(VentaDTO venta) throws DAOException;
    
    /**
     * Modifica la informacion de la venta en cuestion.
     * @param venta Venta a modificar.
     * @throws subsistemas.excepciones.DAOException
     */
    public void actualizarVenta(VentaDTO venta) throws DAOException;
    
    /**
     * Elimina una venta con el ID de la venta especificado.
     * @param ventaId ID de la venta a eliminar.
     * @throws subsistemas.excepciones.DAOException
     */
    public void eliminarVenta(Long ventaId) throws DAOException;
}











