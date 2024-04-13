
package persistencia;

import dao.GestorProductos;
import dao.GestorUsuarios;
import dao.GestorVentas;
import excepciones.DAOException;
import java.util.List;
import objetosNegocio.ProductoDTO;
import objetosNegocio.UsuarioDTO;
import objetosNegocio.VentaDTO;
import persistencia.excepciones.PersistenciaBazarException;
import productos.IGestorProductos;
import usuarios.IGestorUsuarios;
import ventas.IGestorVentas;

/**
 *
 * @author saul
 */
public class PersistenciaBazarListas implements IPersistenciaBazar {

    private final IGestorProductos  productos;
    private final IGestorUsuarios   usuarios;
    private final IGestorVentas     ventas;
    
    public PersistenciaBazarListas() {
        this.productos = new GestorProductos();
        this.usuarios = new GestorUsuarios();
        this.ventas = new GestorVentas();
    }
    
    @Override
    public List<ProductoDTO> consultarProductosTodos() throws PersistenciaBazarException {
        try {
            List<ProductoDTO> productosTodos = productos.consultarTodos();
            
            if (productosTodos == null) {
                throw new PersistenciaBazarException("Ha ocurrido un error al consultar los productos, intente de nuevo mas tarde");
            }
            
            if (productosTodos.isEmpty()) {
                throw new PersistenciaBazarException("No se encontraron productos registrados en el catalogo");
            }
            
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
                throw new PersistenciaBazarException("Ha ocurrido un error al consultar los productos, intente de nuevo mas tarde");
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
    public UsuarioDTO iniciarSesionUsuario(UsuarioDTO usuario) throws PersistenciaBazarException {
        try {
            UsuarioDTO usuarioEnSistema = usuarios.iniciarSesion(usuario.getTelefono(), usuario.getContrasena());
            
            if (usuarioEnSistema == null) {
                throw new PersistenciaBazarException("Ha ocurrido un problema al intentar iniciar sesion, intente de nuevo mas tarde");
            }
            
            return usuarioEnSistema;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public ProductoDTO consultarProductoPorCodigo(String codigoInterno) throws PersistenciaBazarException {
        try {
            ProductoDTO producto = this.productos.consultarProducto(codigoInterno);
            
            return producto;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException("Ha ocurrido un error al buscar el producto, intente de nuevo mas tarde");
        }
    }

    @Override
    public ProductoDTO consultarProductoPorCodigoBarras(Long codigoBarras) throws PersistenciaBazarException {
        try {
            ProductoDTO producto = this.productos.consultarProducto(codigoBarras);
            
            return producto;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException("Ha ocurrido un error al buscar el producto, intente de nuevo mas tarde");
        }
    }
    
}
