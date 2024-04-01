
package ventas;

import java.time.LocalDate;
import java.util.List;
import objetosNegocio.Venta;

/**
 * Define las operaciones basicas para gestionar ventas en el sistema.
 * @author saul
 */
public interface IGestorVentas {
    
    /**
     * Busca una venta a partir de su ID.
     * @param id numero identificador de la venta.
     * @return Venta si se encuentra, null en caso contrario.
     */
    public Venta consultarVenta(Long id);
    
    /**
     * Regresa una lista de las ventas hechas por el usuario con el ID especificado.
     * @param usuarioId ID del usuario
     * @return 
     */
    public List<Venta> consultarVentasDeUsuario(Long usuarioId);
    
    /**
     * Regresa una lista de ventas en el periodo especificado.
     * @param inicio Inicio del periodo.
     * @param fin Fin del periodo
     * @return 
     */
    public List<Venta> consultarVentasPorPeriodo(LocalDate inicio, LocalDate fin);
    
    /**
     * Regresa una lista con todas las ventas existentes.
     * @return 
     */
    public List<Venta> consultarTodos();
    
    /**
     * Registra una nueva venta.
     * @param venta Venta a guardar.
     */
    public void registrarVenta(Venta venta);
    
    /**
     * Modifica la informacion de la venta en cuestion.
     * @param venta Venta a modificar.
     */
    public void actualizarVenta(Venta venta);
    
    /**
     * Elimina una venta con el ID de la venta especificado.
     * @param ventaId ID de la venta a eliminar.
     */
    public void eliminarVenta(Long ventaId);
}











