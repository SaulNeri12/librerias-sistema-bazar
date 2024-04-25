
package persistencia;

import dao.GestorProductos;
import dao.GestorUsuarios;
import dao.GestorVentas;
import java.util.List;
import objetosNegocio.ProductoDTO;
import objetosNegocio.UsuarioDTO;
import objetosNegocio.VentaDTO;
import persistencia.excepciones.PersistenciaBazarException;

import subsistemas.excepciones.DAOException;
import subsistemas.interfaces.*;

/*
    NOTE: Hacer las conversiones necesarias para hacer funcionar los metodos de esta
    clase, se marca error en muchas partes del codigo porque ocurre un error en los
    gestores DAO, el problema es el que no se convierten las clases adecuadamente,
    se quiere sacar objetos entidad en las consultas pero se les pone como DTO y 
    las entidades no tienen compatibilidad con los DTO.

    TODO: Adecua las conversiones entre objetos Entidad y DTO
        
        1.  [X] Anade un metodo """toDTO()""" en las clases entidad para convertirlas 
            a DTO sin tener que recurrir a crear una clase especificamente para eso.
            NOTE: LISTO (en todos menos CompraDTO y DetalleCompraDTO)

        2.  [ ] Se puede tener una clase que haga el proceso a la inversa (de DTO a Entidad)
            Esto sera asi porque el proyecto o libreria de los DTO y las Entidades 
            deben estar separados (por como lo menciono el profe), asi que no tendrian 
            por que estar relacionadas. Entonces, tendriamos que crear una clase 'ConvertidorDTO' 
            con sus metodos como 'obtenerEntidadProducto', 'obtenerEntidadProveedor', etc.

                ((( NOTE: POR PRUEBAS, VAMOS PONERLO EN ESTE PROYECTO EN UN PAQUETE DENTRO 
                    EL PAQUETE ENTIDADES LLAMADO 'convertidor' EN DONDE ESTA LA CLASE
                    QUE CONVIERTE DE 'DTO' A 'ENTIDAD'
                )))
            
            (Esta clase debe de ir en este mismo proyecto, en un paquete propio)

    NOTE: Antes de cambiar las clases Entidad a otro proyecto, primero debemos definir
          bien los campos que vamos a necesitar en cada entidad y DTO. Asi que NO HAY
          NECESIDAD DE PONER ENTIDADES EN SU PROPIO PROYECTO POR AHORA (Los DTOs si 
          podemos organizarlos en su propio proyecto desde ahora).

    [SUBSISTEMAS FUNCIONANDO] - despues de hacer las conversiones...

    Rellena con 'x' los corchetes cuando esten funcionando correctamente...

        1. [ ] GestorProductos
        2. [ ] GestorProveedores
        3. [ ] GestorInventario
        4. [ ] GestorUsuarios
        5. [ ] GestorVentas

*/

/**
 *
 * @author saul
 */
public class PersistenciaBazar implements IPersistenciaBazar {

    private final IGestorProductos  productos;
    private final IGestorUsuarios   usuarios;
    private final IGestorVentas     ventas;
    
    public PersistenciaBazar() {
        this.productos = GestorProductos.getInstance();
        this.usuarios = GestorUsuarios.getInstance();
        this.ventas = GestorVentas.getInstance();
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
    public UsuarioDTO iniciarSesionUsuario(String telefono, String contrasena) throws PersistenciaBazarException {
        try {
            UsuarioDTO usuarioEnSistema = usuarios.iniciarSesion(telefono, contrasena);
            
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
