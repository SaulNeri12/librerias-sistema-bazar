
package dao;

import excepciones.DAOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import objetosNegocio.ProductoDTO;
import productos.IGestorProductos;

/**
 * Implementacion del subsistema de Productos con listas.
 * @author saul
 */
public class GestorProductos implements IGestorProductos {

    private List<ProductoDTO> productos;
    
    public GestorProductos() {
        this.productos = new ArrayList<>();
        
        // NOTE: DATOS DE PRUEBA
        Random random = new Random();
        
        Proveedor proveedorGamesa = new Proveedor();
        proveedorGamesa.setId(random.nextLong() & Long.MAX_VALUE);
        proveedorGamesa.setNombre("GAMESA");
        proveedorGamesa.setDescripcion("Venta amplia variedad de galletas y productos de panadería");
        proveedorGamesa.setEmail("grupogamesa1921@gamesa.co.mx");
        proveedorGamesa.setTelefono("6441292556");
        
        Proveedor proveedorRopa = new Proveedor();
        proveedorRopa.setId(random.nextLong() & Long.MAX_VALUE);
        proveedorRopa.setNombre("CottonRey Textiles S.A. de C.V");
        proveedorRopa.setDescripcion("Vende una amplia gama de productos textiles, incluyendo ropa casual, ropa interior, ropa de cama, y accesorios de moda.");
        proveedorRopa.setEmail("contacto@cottonreytextiles.com.mx");
        proveedorRopa.setTelefono("55 1234 5678");
        
        // DATOS DE PRUEBA...
        ProductoDTO producto1 = new ProductoDTO();
        producto1.setId(random.nextLong() & Long.MAX_VALUE);
        producto1.setCodigo("GL1111");
        producto1.setNombre("Galletas Marías");
        producto1.setPrecio(17.50f);
        producto1.agregarProveedor(proveedorGamesa);

        ProductoDTO producto2 = new ProductoDTO();
        producto2.setId(random.nextLong() & Long.MAX_VALUE);
        producto2.setCodigo("PE2222");
        producto2.setNombre("Playera estampada de calavera");
        producto2.setPrecio(120.00f);
        producto2.agregarProveedor(proveedorRopa);

        ProductoDTO producto3 = new ProductoDTO();
        producto3.setId(random.nextLong() & Long.MAX_VALUE);
        producto3.setCodigo("BTM3333");
        producto3.setNombre("Bufanda tejida a mano");
        producto3.setPrecio(80.00f);
        producto3.agregarProveedor(proveedorRopa);

        ProductoDTO producto4 = new ProductoDTO();
        producto4.setId(random.nextLong() & Long.MAX_VALUE);
        producto4.setCodigo("JM4444");
        producto4.setNombre("Jeans de mezclilla desgastados");
        producto4.setPrecio(250.00f);
        producto4.agregarProveedor(proveedorRopa);

        ProductoDTO producto5 = new ProductoDTO();
        producto5.setId(random.nextLong() & Long.MAX_VALUE);
        producto5.setCodigo("CHMAI5555");
        producto5.setNombre("Chamarra acolchada para el invierno");
        producto5.setPrecio(350.00f);
        producto5.agregarProveedor(proveedorRopa);

        ProductoDTO producto6 = new ProductoDTO();
        producto6.setId(random.nextLong() & Long.MAX_VALUE);
        producto6.setCodigo("VB6666");
        producto6.setNombre("Vestido bordado estilo oaxaqueño");
        producto6.setPrecio(180.00f);
        producto6.agregarProveedor(proveedorRopa);

        ProductoDTO producto7 = new ProductoDTO();
        producto7.setId(random.nextLong() & Long.MAX_VALUE);
        producto7.setCodigo("CFML7777");
        producto7.setNombre("Camisa formal de manga larga");
        producto7.setPrecio(150.00f);
        producto7.agregarProveedor(proveedorRopa);

        ProductoDTO producto8 = new ProductoDTO();
        producto8.setId(random.nextLong() & Long.MAX_VALUE);
        producto8.setCodigo("GBMX8888");
        producto8.setNombre("Gorras con bordados típicos mexicanos");
        producto8.setPrecio(50.00f);
        producto8.agregarProveedor(proveedorRopa);

        ProductoDTO producto9 = new ProductoDTO();
        producto9.setId(random.nextLong() & Long.MAX_VALUE);
        producto9.setCodigo("CRIH9999");
        producto9.setNombre("Conjunto de ropa interior para hombre");
        producto9.setPrecio(100.00f);
        producto9.agregarProveedor(proveedorRopa);

        ProductoDTO producto10 = new ProductoDTO();
        producto10.setId(random.nextLong() & Long.MAX_VALUE);
        producto10.setCodigo("TBM1010");
        producto10.setNombre("Traje de baño de dos piezas para mujer");
        producto10.setPrecio(200.00f);
        producto10.agregarProveedor(proveedorRopa);
        
        this.productos.add(producto1);
        this.productos.add(producto2);
        this.productos.add(producto3);
        this.productos.add(producto4);
        this.productos.add(producto5);
        this.productos.add(producto6);
        this.productos.add(producto7);
        this.productos.add(producto8);
        this.productos.add(producto9);
        this.productos.add(producto10);
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
                .filter(prdct -> prdct.getId().equals(producto.getId()) || prdct.getCodigo().equals(producto.getCodigo()))
                .findFirst();
        
        if (productoEnCatalogo.isPresent()) {
            throw new DAOException("El producto dado ya se encuentra registrado");
        }
        
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        
        producto.setFechaRegistro(fechaHoraActual);
        
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
        
        producto.setFechaRegistro(productoEnCatalogo.get().getFechaRegistro());
        
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
