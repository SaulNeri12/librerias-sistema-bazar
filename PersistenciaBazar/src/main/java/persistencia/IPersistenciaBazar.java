
package persistencia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import objetosDTO.ProductoDTO;
import objetosDTO.UsuarioDTO;
import objetosDTO.VentaDTO;
import persistencia.excepciones.PersistenciaBazarException;

/**
 * Contiene todas las operaciones necesarias para el sistema de ventas.
 * @author saul
 */
public interface IPersistenciaBazar {
    
    public ProductoDTO consultarProductoPorCodigoInterno(String codigoInterno) throws PersistenciaBazarException;;
    
    public ProductoDTO consultarProductoPorCodigoBarras(Long codigoBarras) throws PersistenciaBazarException;;
    
    public List<ProductoDTO> consultarProductosTodos() throws PersistenciaBazarException;
    
    public List<ProductoDTO> consultarProductosPorNombre(String nombreProducto) throws PersistenciaBazarException;
    
    public void registrarProducto(ProductoDTO producto) throws PersistenciaBazarException;
    
    public void actualizarProducto(ProductoDTO producto) throws PersistenciaBazarException;
    
    public void eliminarProducto(String codigoInterno) throws PersistenciaBazarException;
    
    
    
    
   
    public VentaDTO consultarVenta(Long idVenta) throws PersistenciaBazarException;
    
    public List<VentaDTO> consultarVentasTodas() throws PersistenciaBazarException;
    
    public List<VentaDTO> consultarVentasPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaBazarException;
    
    public List<VentaDTO> consultarVentasDeUsuario(Long idUsuario) throws PersistenciaBazarException;
    
    public void registrarVenta(VentaDTO venta) throws PersistenciaBazarException;
    
    public void actualizarVenta(VentaDTO venta) throws PersistenciaBazarException;
    
    public void eliminarVenta(Long idVenta) throws PersistenciaBazarException;
    
    
    
    
    public UsuarioDTO consultarUsuario(Long idUsuario) throws PersistenciaBazarException;
    
    public UsuarioDTO consultarUsuarioPorNumeroTelefono(String telefono) throws PersistenciaBazarException;;
    
    public void registrarUsuario(UsuarioDTO usuario) throws PersistenciaBazarException;
    
    public void actualizarUsuario(UsuarioDTO usuario) throws PersistenciaBazarException;
    
    public void eliminarUsuario(Long idUsuario) throws PersistenciaBazarException;
    
    public UsuarioDTO iniciarSesionUsuario(String telefono, String contrasena) throws PersistenciaBazarException;
}
