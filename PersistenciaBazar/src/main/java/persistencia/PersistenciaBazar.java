
package persistencia;

import conexion.EntityManagerSingleton;
import dao.GestorProductos;
import dao.GestorUsuarios;
import dao.GestorVentas;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosDTO.ProductoDTO;
import objetosDTO.UsuarioDTO;
import objetosDTO.VentaDTO;
import persistencia.excepciones.PersistenciaBazarException;

import subsistemas.excepciones.DAOException;
import subsistemas.interfaces.*;

/**
 *
 * @author saul
 */
public class PersistenciaBazar implements IPersistenciaBazar {

    private static PersistenciaBazar instancia;
    
    private final IGestorProductos  productos;
    private final IGestorUsuarios   usuarios;
    private final IGestorVentas     ventas;
    
    public PersistenciaBazar() {
        this.productos = GestorProductos.getInstance();
        this.usuarios = GestorUsuarios.getInstance();
        this.ventas = GestorVentas.getInstance();
    }

    public static PersistenciaBazar getInstance() {
        if (instancia == null) {
            instancia = new PersistenciaBazar();
        }

        return instancia;
    }
    
    @Override
    public List<ProductoDTO> consultarProductosTodos() throws PersistenciaBazarException {
        try {
            List<ProductoDTO> productosTodos = productos.consultarTodos();
            
            if (productosTodos == null) {
                throw new PersistenciaBazarException("No se encontraron productos registrados en el catalogo");
            }
            /*
            if (productosTodos.isEmpty()) {
                throw new PersistenciaBazarException("No se encontraron productos registrados en el catalogo");
            }*/
            
            return productosTodos;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public List<ProductoDTO> consultarProductosPorNombre(String nombreProducto) throws PersistenciaBazarException {
        try {
            List<ProductoDTO> productosTodos = productos.consultarProductosPorNombre(nombreProducto);
            
            if (productosTodos == null) {
                throw new PersistenciaBazarException("No se encontraron productos registrados con el nombre dado");
            }
            
            if (productosTodos.isEmpty()) {
                throw new PersistenciaBazarException("No se encontro ningun producto que coincida con la busqueda");
            }
            
            return productosTodos;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public void registrarVenta(VentaDTO venta) throws PersistenciaBazarException {
        try {
            ventas.registrarVenta(venta);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public UsuarioDTO iniciarSesionUsuario(String telefono, String contrasena) throws PersistenciaBazarException {
        try {
            UsuarioDTO usuarioEnSistema = usuarios.iniciarSesion(telefono, contrasena);
            
            if (usuarioEnSistema == null) {
                throw new PersistenciaBazarException("No se encontro al usuario con el numero de telefono dado");
            }
            
            return usuarioEnSistema;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public ProductoDTO consultarProductoPorCodigoInterno(String codigoInterno) throws PersistenciaBazarException {
        try {
            ProductoDTO producto = this.productos.consultarProducto(codigoInterno);
            
            if (producto == null) {
                throw new DAOException("No se encontro el producto con el codigo especificado");
            }
            
            return producto;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public ProductoDTO consultarProductoPorCodigoBarras(Long codigoBarras) throws PersistenciaBazarException {
        try {
            ProductoDTO producto = this.productos.consultarProducto(codigoBarras);
            
            if (producto == null) {
                throw new DAOException("No se encontro el producto con el codigo de barras especificado");
            }
            
            return producto;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public void registrarProducto(ProductoDTO producto) throws PersistenciaBazarException {
        try {
            this.productos.registrarProducto(producto);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public void actualizarProducto(ProductoDTO producto) throws PersistenciaBazarException {
        try {
            this.productos.actualizarProducto(producto);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public void eliminarProducto(String codigoInterno) throws PersistenciaBazarException {
        try {
            this.productos.eliminarProducto(codigoInterno);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public VentaDTO consultarVenta(Long idVenta) throws PersistenciaBazarException {
        try {
            VentaDTO venta = this.ventas.consultarVenta(idVenta);
            
            if (venta == null) {
                throw new DAOException("No se encontro la venta con el ID dado");
            }
            
            return venta;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public List<VentaDTO> consultarVentasTodas() throws PersistenciaBazarException {
        try {
            List<VentaDTO> ventas = this.ventas.consultarTodos();
            
            if (ventas == null) {
                throw new DAOException("No se encontraron ventas registradas");
            }
            
            return ventas;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public List<VentaDTO> consultarVentasPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaBazarException {
        try {
            List<VentaDTO> ventas = this.ventas.consultarVentasPorPeriodo(fechaInicio, fechaFin);
            
            if (ventas == null) {
                throw new DAOException("No se encontraron ventas hechas en ese periodo de tiempo");
            }
            
            return ventas;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public List<VentaDTO> consultarVentasDeUsuario(Long idUsuario) throws PersistenciaBazarException {
         try {
            UsuarioDTO usuario = this.usuarios.consultarUsuario(idUsuario);
            
            if (usuario == null) {
                throw new DAOException("El usuario no existe");
            } 
             
            List<VentaDTO> ventas = this.ventas.consultarVentasDeUsuario(idUsuario);
            
            if (ventas == null) {
                throw new DAOException("No se encontraron ventas hechas por el usuario especificado");
            }
            
            return ventas;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public void actualizarVenta(VentaDTO venta) throws PersistenciaBazarException {
        try {
            this.ventas.actualizarVenta(venta);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public void eliminarVenta(Long idVenta) throws PersistenciaBazarException {
        try {
            this.ventas.eliminarVenta(idVenta);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public UsuarioDTO consultarUsuario(Long idUsuario) throws PersistenciaBazarException {
        try {
            UsuarioDTO usuario = usuarios.consultarUsuario(idUsuario);
            
            if (usuario == null) {
                throw new DAOException("No se encontro al usuario con dicho ID");
            }
            
            return usuario;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public UsuarioDTO consultarUsuarioPorNumeroTelefono(String telefono) throws PersistenciaBazarException {
        try {
            UsuarioDTO usuario = usuarios.consultarUsuarioPorNumeroTelefono(telefono);
            
            if (usuario == null) {
                throw new DAOException("No se encontro al usuario con dicho ID");
            }
            
            return usuario;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public void registrarUsuario(UsuarioDTO usuario) throws PersistenciaBazarException {
        try {
            usuarios.registrarUsuario(usuario);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuario) throws PersistenciaBazarException {
        try {
            usuarios.actualizarUsuario(usuario);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public void eliminarUsuario(Long idUsuario) throws PersistenciaBazarException {
        try {
            usuarios.eliminarUsuario(idUsuario);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }
    
}
