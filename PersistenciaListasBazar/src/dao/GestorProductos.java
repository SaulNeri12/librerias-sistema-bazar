
package dao;

import excepciones.DAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import objetosNegocio.Producto;
import objetosNegocio.Proveedor;
import productos.IGestorProductos;

/**
 * Implementacion del subsistema de Productos con listas.
 * @author saul
 */
public class GestorProductos implements IGestorProductos {

    private List<Producto> productos;
    
    public GestorProductos() {
        this.productos = new ArrayList<>();
    }
    
    @Override
    public List<Producto> consultarTodos() throws DAOException {
        List<Producto> productosTodos = this.productos.stream().collect(Collectors.toList());
        
        return productosTodos;
    }

    @Override
    public List<Producto> consultarProductosPorNombre(String nombreProducto) throws DAOException {
        
        if (nombreProducto == null) {
            throw new DAOException("El nombre del producto a buscar dado es null");
        }
        
        List<Producto> productosTodos = this.productos.stream()
                .filter(producto -> producto.getNombre().toLowerCase().contains(nombreProducto.toLowerCase()))
                .collect(Collectors.toList());
        
        return productosTodos;
    }

    @Override
    public List<Producto> consultarProductosPorProveedor(Proveedor proveedor) throws DAOException {
        
        if (proveedor == null) {
            throw new DAOException("El proveedor dado es null");
        }
        
        Predicate<Producto> contieneAlProveedor = prdct -> prdct.obtenerProveedores().stream().anyMatch(prov -> prov.getId().equals(prov.getId()));
        
        List<Producto> productosTodos = this.productos.stream()
                .filter(contieneAlProveedor)
                .collect(Collectors.toList());
        
        return productosTodos;
    }

    @Override
    public void registrarProducto(Producto producto) throws DAOException {
        if (producto == null) {
            throw new DAOException("El producto dado es null");
        }
        
        Optional<Producto> productoEnCatalogo = this.productos.stream()
                .filter(prdct -> producto.getId().equals(producto.getId()) || producto.getCodigo().equals(producto.getCodigo()))
                .findFirst();
        
        if (productoEnCatalogo.isPresent()) {
            throw new DAOException("El producto dado ya se encuentra registrado");
        }
        
        boolean agregado = this.productos.add(producto);
        
        if (!agregado) {
            throw new DAOException("No se pudo registrar el producto debido a un error");
        }
    }

    @Override
    public Producto consultarProducto(String codigoInterno) throws DAOException {
        if (codigoInterno == null) {
            throw new DAOException("El codigo interno dado es null");
        }
        
        Optional<Producto> productoEnCatalogo = this.productos.stream()
                .filter(prdct -> prdct.getCodigo().equals(codigoInterno))
                .findFirst();
        
        if (!productoEnCatalogo.isPresent()) {
            return null;
        }
        
        return productoEnCatalogo.get();
    }

    @Override
    public Producto consultarProducto(Long codigoBarras) throws DAOException {
        if (codigoBarras == null) {
            throw new DAOException("El codigo de barras dado es null");
        }
        
        Optional<Producto> productoEnCatalogo = this.productos.stream()
                .filter(p -> p.getId().equals(codigoBarras))
                .findFirst();
        
        if (!productoEnCatalogo.isPresent()) {
            return null;
        }
        
        return productoEnCatalogo.get();
    }

    @Override
    public void actualizarProducto(Producto producto) throws DAOException {
        if (producto == null) {
            throw new DAOException("El producto dado es null");
        }
        
        Optional<Producto> productoEnCatalogo = this.productos.stream()
                .filter(p -> p.getId().equals(producto.getId()) || p.getCodigo().equals(producto.getCodigo()))
                .findFirst();
        
        if (!productoEnCatalogo.isPresent()) {
            throw new DAOException("El producto especificado no esta registrado");
        }
        
        int index = this.productos.indexOf(productoEnCatalogo.get());
        
        if (index < 0) {
            throw new DAOException("No se pudo actualizar el producto debido a un error");
        }
        
        this.productos.set(index, producto);
    }

    @Override
    public void eliminarProducto(String codigoInterno) throws DAOException {
        if (codigoInterno == null) {
            throw new DAOException("El codigo interno dado es null");
        }
        
        Optional<Producto> productoEnCatalogo = this.productos.stream()
                .filter(p -> p.getCodigo().equals(codigoInterno))
                .findFirst();
        
        if (!productoEnCatalogo.isPresent()) {
            throw new DAOException("El producto a eliminar no esta registrado");
        }
        
        boolean eliminado = this.productos.remove(productoEnCatalogo.get());
        
        if (!eliminado) {
            throw new DAOException("El producto no se pudo eliminar debido a un error");
        }
    }
}
