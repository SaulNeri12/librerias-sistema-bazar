
package dao;

import conexion.EntityManagerSingleton;
import conversion.Conversion;
import entidades.Producto;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import objetosNegocio.ProductoDTO;
import objetosNegocio.ProveedorDTO;


import subsistemas.excepciones.DAOException;
import subsistemas.interfaces.IGestorProductos;

/**
 * Implementacion del subsistema de Productos con listas.
 * 
 * @author saul
 */
public class GestorProductos implements IGestorProductos {

    private static GestorProductos instancia;
    private final EntityManager em;

    public GestorProductos(EntityManager em) {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();

    }

    public GestorProductos() {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();
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
     * @throws subsistemas.excepciones.DAOException
     */
    @Override
    public List<ProductoDTO> consultarTodos() throws DAOException {
        try {
            TypedQuery<Producto> consulta = em.createQuery("SELECT p FROM Productos p", Producto.class);
            
            List<ProductoDTO> listaProductos = consulta.getResultList()
                    .stream()
                    .map(producto -> producto.toDTO())
                    .collect(Collectors.toList());
            
            return listaProductos;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new DAOException("Error al consultar todos los productos");
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
            throw new DAOException("Error al consultar productos por nombre");
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
            throw new DAOException("Error al consultar productos por proveedor");
        }
    }

    /**
     * Registra un nuevo producto en la base de datos.
     * @param producto
     * @throws excepciones.DAOException
     */
    @Override
    public void registrarProducto(ProductoDTO producto) throws DAOException {
        if (producto == null) {
            throw new DAOException("El producto dado es null");
        }
        Conversion conversion = new Conversion();
        Producto entidadProducto = conversion.convertirProductoDTOAEntidad(producto);

        try {
            em.getTransaction().begin();
            em.persist(entidadProducto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new DAOException("Error al registrar el producto");
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
            throw new DAOException("Error al consultar el producto por codigo interno");
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
            throw new DAOException("Error al consultar el producto por codigo de barras");
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
        if (producto == null) {
            throw new DAOException("El producto dado es null");
        }
        try {

            Conversion conversion = new Conversion();
            Producto productoEntity = conversion.convertirProductoDTOAEntidad(producto);
            em.getTransaction().begin();
            em.merge(productoEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new DAOException("Error al actualizar el producto");
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

        try{
            Producto producto = em.find(Producto.class, codigoInterno);
            if (producto != null) {
                em.getTransaction().begin();
                em.remove(producto);
                em.getTransaction().commit();
            } else {
                throw new DAOException("El producto no se encuentra en la base de datos");
                
            }

        }
        catch (Exception ex) {
            throw new DAOException("Error al eliminar el producto");
        }
    }
}
