
package dao;

import excepciones.DAOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.text.html.parser.Entity;

import objetosNegocio.ProductoDTO;
import objetosNegocio.ProveedorDTO;
import productos.IGestorProductos;
import conexion.EntityManagerSingleton;
import entidades.Producto;

/**
 * Implementacion del subsistema de Productos con listas.
 * 
 * @author saul
 */
public class GestorProductos implements IGestorProductos {

    private List<ProductoDTO> productos;
    private static GestorProductos instancia;
    private final EntityManager em;

    public GestorProductos(EntityManager em) {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();

    }

    public GestorProductos() {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();
        this.productos = new ArrayList<>();

        // NOTE: DATOS DE PRUEBA
        Random random = new Random();

        ProveedorDTO proveedorGamesa = new ProveedorDTO();
        proveedorGamesa.setId(random.nextLong() & Long.MAX_VALUE);
        proveedorGamesa.setNombre("GAMESA");
        proveedorGamesa.setDescripcion("Venta amplia variedad de galletas y productos de panadería");
        proveedorGamesa.setEmail("grupogamesa1921@gamesa.co.mx");
        proveedorGamesa.setTelefono("6441292556");

        ProveedorDTO proveedorRopa = new ProveedorDTO();
        proveedorRopa.setId(random.nextLong() & Long.MAX_VALUE);
        proveedorRopa.setNombre("CottonRey Textiles S.A. de C.V");
        proveedorRopa.setDescripcion(
                "Vende una amplia gama de productos textiles, incluyendo ropa casual, ropa interior, ropa de cama, y accesorios de moda.");
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

    public static GestorProductos getInstance() {
        if (instancia == null) {
            instancia = new GestorProductos(EntityManagerSingleton.getInstance().getEntityManager());
        }

        return instancia;
    }

    /**
     * Consulta todos los productos registrados en el sistema.
     * 
     * @return Una lista con todos los productos registrados en el sistema.
     */
    @Override
    public List<ProductoDTO> consultarTodos() throws DAOException {
        try {
            TypedQuery<ProductoDTO> consulta = em.createQuery("SELECT p FROM Producto p", ProductoDTO.class);
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new DAOException("Error al consultar todos los productos", ex);
        }
    }

    /**
     * Consulta los productos cuyo nombre contenga una cadena dada en la base de
     * datos.
     * 
     * @param nombreProducto La cadena que se desea buscar en el nombre de los
     *                       productos.
     * @return Una lista con los productos cuyo nombre contiene la cadena dada.
     * @throws DAOException Si ocurre un error al consultar los productos por
     *                      nombre.
     */
    @Override
    public List<ProductoDTO> consultarProductosPorNombre(String nombreProducto) throws DAOException {
        try {
            TypedQuery<ProductoDTO> consulta = em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre",
                    ProductoDTO.class);
            consulta.setParameter("nombre", "%" + nombreProducto + "%");
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new DAOException("Error al consultar productos por nombre", ex);
        }
    }

    /**
     * Consulta los productos que son proveidos por un proveedor dado en la base de
     * datos
     * 
     * @param proveedor El proveedor del cual se desean consultar los productos.
     * @return Una lista con los productos que son proveidos por el proveedor dado.
     * @throws DAOException Si ocurre un error al consultar los productos por
     *                      proveedor.
     */
    @Override
    public List<ProductoDTO> consultarProductosPorProveedor(ProveedorDTO proveedor) throws DAOException {
        try {
            TypedQuery<ProductoDTO> consulta = em.createQuery(
                    "SELECT p FROM Producto p WHERE :proveedor MEMBER OF p.proveedores",
                    ProductoDTO.class);
            consulta.setParameter("proveedor", proveedor);
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new DAOException("Error al consultar productos por proveedor", ex);
        }
    }

    /**
     * Registra un nuevo producto en la base de datos.
     * 
     */
    @Override
    public void registrarProducto(ProductoDTO producto) throws DAOException {
        if (producto == null) {
            throw new DAOException("El producto dado es null");
        }

        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new DAOException("Error al registrar el producto", ex);
        }
    }

    /**
     * Consulta un producto por su codigo interno en la base de datos.
     * 
     * @param codigoInterno El codigo interno del producto que se desea consultar.
     * @return El producto con el codigo interno dado, o null si no se encontro.
     * @throws DAOException Si ocurre un error al consultar el producto por su
     *                      codigo interno.
     */
    @Override
    public ProductoDTO consultarProducto(String codigoInterno) throws DAOException {

        if (codigoInterno == null) {
            throw new DAOException("El codigo interno dado es null");
        }

        try {
            TypedQuery<ProductoDTO> consulta = em.createQuery(
                    "SELECT p FROM Producto p WHERE p.codigo = :codigo_interno",
                    ProductoDTO.class);
            consulta.setParameter("codigo", codigoInterno);
            return consulta.getSingleResult();
        } catch (Exception ex) {
            throw new DAOException("Error al consultar el producto por codigo interno", ex);
        }
    }

    /**
     * Consulta un producto por su codigo de barras en la base de datos.
     * 
     * @param codigoBarras El codigo de barras del producto que se desea consultar.
     * @return El producto con el codigo de barras dado, o null si no se encontro.
     * @throws DAOException Si ocurre un error al consultar el producto por su
     *                      codigo de barras.
     */
    @Override
    public ProductoDTO consultarProducto(Long codigoBarras) throws DAOException {

        if (codigoBarras == null) {
            throw new DAOException("El codigo de barras dado es null");
        }

        try {
            TypedQuery<ProductoDTO> consulta = em.createQuery(
                    "SELECT p FROM Producto p WHERE p.codigoBarras = :codigo_barras",
                    ProductoDTO.class);
            consulta.setParameter("codigo_barras", codigoBarras);
            return consulta.getSingleResult();
        } catch (Exception ex) {
            throw new DAOException("Error al consultar el producto por codigo de barras", ex);
        }
    }

    /**
     * Actualiza un producto en la base de datos.
     * 
     * @param producto El producto con los datos actualizados.
     * @throws DAOException Si el producto dado es null, si no se encontro el
     *                      producto en el sistema o si no se pudo modificar los
     *                      datos del producto.
     */

    @Override
    public void actualizarProducto(ProductoDTO producto) throws DAOException {
        try {

            Producto productoEntity = em.find(Producto.class, producto.getId());

            if (productoEntity == null) {
                throw new DAOException("El producto no existe en la base de datos");
            }

            productoEntity.setCodigoInterno(producto.getCodigo());
            productoEntity.setNombre(producto.getNombre());
            productoEntity.setPrecio(producto.getPrecio());
            productoEntity.setFechaRegistro(producto.getFechaRegistro());

            em.getTransaction().begin();
            em.merge(productoEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new DAOException("Error al actualizar el producto", ex);
        }
    }

    /**
     * Elimina un producto de la base de datos.
     * @param codigoInterno El codigo interno del producto que se desea eliminar.
     * @throws DAOException Si ocurre un error al eliminar el producto.
     */
    @Override
    public void eliminarProducto(String codigoInterno) throws DAOException {
        
        if (codigoInterno == null) {
            throw new DAOException("El codigo interno del producto dado es null");
        }

        try {
            ProductoDTO producto = consultarProducto(codigoInterno);
            em.getTransaction().begin();
            em.remove(producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new DAOException("Error al eliminar el producto", ex);
        }
    }
}
