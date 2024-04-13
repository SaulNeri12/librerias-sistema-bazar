
package persistencia;

import dao.GestorProductos;
import dao.GestorUsuarios;
import dao.GestorVentas;
import excepciones.DAOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.Producto;
import objetosNegocio.Usuario;
import objetosNegocio.Venta;
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
    public List<Producto> consultarProductosTodos() throws PersistenciaBazarException {
        try {
            List<Producto> productosTodos = productos.consultarTodos();
            
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
    public List<Producto> consultarProductosPorNombre(String nombreProducto) throws PersistenciaBazarException {
        try {
            List<Producto> productosTodos = productos.consultarProductosPorNombre(nombreProducto);
            
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
    public void registrarVenta(Venta venta) throws PersistenciaBazarException {
        try {
            ventas.registrarVenta(venta);
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public Usuario iniciarSesionUsuario(Usuario usuario) throws PersistenciaBazarException {
        try {
            Usuario usuarioEnSistema = usuarios.iniciarSesion(usuario.getTelefono(), usuario.getContrasena());
            
            if (usuarioEnSistema == null) {
                throw new PersistenciaBazarException("Ha ocurrido un problema al intentar iniciar sesion, intente de nuevo mas tarde");
            }
            
            return usuarioEnSistema;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException(ex.getMessage());
        }
    }

    @Override
    public Producto consultarProductoPorCodigo(String codigoInterno) throws PersistenciaBazarException {
        try {
            Producto producto = this.productos.consultarProducto(codigoInterno);
            
            return producto;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException("Ha ocurrido un error al buscar el producto, intente de nuevo mas tarde");
        }
    }

    @Override
    public Producto consultarProductoPorCodigoBarras(Long codigoBarras) throws PersistenciaBazarException {
        try {
            Producto producto = this.productos.consultarProducto(codigoBarras);
            
            return producto;
        } catch (DAOException ex) {
            throw new PersistenciaBazarException("Ha ocurrido un error al buscar el producto, intente de nuevo mas tarde");
        }
    }
    
}
