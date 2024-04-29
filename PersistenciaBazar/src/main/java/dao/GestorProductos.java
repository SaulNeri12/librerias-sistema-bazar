package dao;

import conexion.EntityManagerSingleton;
import entidades.Producto;
import entidades.convertidor.ConvertidorBazarDTO;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            TypedQuery<Producto> consulta = em.createQuery("SELECT p FROM Producto p", Producto.class);
            List<Producto> productos = consulta.getResultList();

            // Convertir las entidades Producto a DTOs
            List<ProductoDTO> productosDTO = new ArrayList<>();
            ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
            
            if (productos.isEmpty()) {
                return null;
            }
            
            for (Producto producto : productos) {
                productosDTO.add(convertidor.convertirProductoAProductoDTO(producto));
            }
            
            // TODO: Si no hay ningun elemento que se encontro en la consulta
            // devolver NULL...

            return productosDTO;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            
            //System.out.println(ex.getClass());
            
            if (ex.getClass() == DAOException.class) {
                throw new DAOException(ex.getMessage());
            }
            
            throw new DAOException("Error al consultar todos los productos");
        }
    }

    /**
     * Consulta los productos cuyo nombre contenga una cadena dada en la base de
     * datos.
     *
     * @param nombreProducto La cadena que se desea buscar en el nombre de los
     * productos.
     * @return Una lista con los productos cuyo nombre contiene la cadena dada.
     * @throws DAOException Si ocurre un error al consultar los productos por
     * nombre.
     */
    @Override
    public List<ProductoDTO> consultarProductosPorNombre(String nombreProducto) throws DAOException {
        try {
            TypedQuery<Producto> consulta = em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE LOWER(:nombre)", Producto.class);
            consulta.setParameter("nombre", "%" + nombreProducto.toLowerCase() + "%");
            List<Producto> productos = consulta.getResultList();

            // Convertir las entidades Producto a DTOs
            List<ProductoDTO> productosDTO = new ArrayList<>();
            ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
            
            if (productos.isEmpty()) {
                return null;
            }
            
            
            for (Producto producto : productos) {
                productosDTO.add(convertidor.convertirProductoAProductoDTO(producto));
            }

            return productosDTO;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new DAOException("Error al consultar productos por nombre");
        }
    }

    /**
     * Consulta los productos que son proveidos por un proveedor dado en la base
     * de datos
     *
     * @param proveedor El proveedor del cual se desean consultar los productos.
     * @return Una lista con los productos que son proveidos por el proveedor
     * dado.
     * @throws DAOException Si ocurre un error al consultar los productos por
     * proveedor.
     * @deprecated 
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
     *
     * @param producto
     * @throws excepciones.DAOException
     */
    @Override
    public void registrarProducto(ProductoDTO producto) throws DAOException {
        if (producto == null) {
            throw new DAOException("El producto dado es null");
        }
        
        Producto entidadProducto = ConvertidorBazarDTO.convertirProductoDTO(producto);

        entidadProducto.setFechaRegistro(LocalDateTime.now());
        
        try {
            
            if (consultarProducto(entidadProducto.getCodigoInterno()) != null) {
                throw new DAOException("El producto ya se encuentra registrado");
            }

            List<ProductoDTO> productosMismoNombre = consultarProductosPorNombre(
                    entidadProducto
                    .getNombre()
                    .toLowerCase()
            );
            
            if (productosMismoNombre != null && !productosMismoNombre.isEmpty()) {
                throw new DAOException("Ya existe un producto con el mismo nombre");
            }
            
            em.getTransaction().begin();
            em.persist(entidadProducto);
            em.getTransaction().commit();
        } catch (NullPointerException ex) {
            throw new DAOException("El producto no se registro por falta de campos");
        } catch (Exception ex) {
            
            if (ex.getClass() == SQLIntegrityConstraintViolationException.class) {
                throw new DAOException("El producto que se intenta registrar, ya fue registrado anteriormente");
            }
            
            throw new DAOException("Error al registrar el producto");
        }
    }

    /**
     * Consulta un producto por su codigo interno en la base de datos.
     *
     * @param codigoInterno El codigo interno del producto que se desea
     * consultar.
     * @return El producto con el codigo interno dado, o null si no se encontro.
     * @throws DAOException Si ocurre un error al consultar el producto por su
     * codigo interno.
     */
    @Override
    public ProductoDTO consultarProducto(String codigoInterno) throws DAOException {
        
        if (codigoInterno == null) {
            throw new DAOException("El código interno dado es null");
        }

        try {
            TypedQuery<Producto> consulta = em.createQuery(
                    "SELECT p FROM Producto p WHERE p.codigoInterno = :codigoInterno", Producto.class);
            consulta.setParameter("codigoInterno", codigoInterno);
            Producto producto = consulta.getSingleResult();
            
            // Convertir la entidad Producto a un DTO
            ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
            return convertidor.convertirProductoAProductoDTO(producto);
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new DAOException("Error al consultar el producto por código interno");
        }
    }

    /**
     * Consulta un producto por su codigo de barras en la base de datos.
     *
     * @param codigoBarras El codigo de barras del producto que se desea
     * consultar.
     * @return El producto con el codigo de barras dado, o null si no se
     * encontro.
     * @throws DAOException Si ocurre un error al consultar el producto por su
     * codigo de barras.
     */
    @Override
    public ProductoDTO consultarProducto(Long codigoBarras) throws DAOException {
        
        if (codigoBarras == null) {
            throw new DAOException("El código de barras dado es null");
        }

        try {
            TypedQuery<Producto> consulta = em.createQuery(
                    "SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras", Producto.class);
            consulta.setParameter("codigoBarras", codigoBarras);
            Producto producto = consulta.getSingleResult();

            // TODO: Verificar que el resultado de 'producto' no sea null,
            // si llegara a ser null puede haber un error cuando se llama al 
            // metodo de conversion, cuando se intente acceder a un producto
            // que es null...
            // Si el producto no se encontro, devolver NULL
            
            // Convertir la entidad Producto a un DTO
            ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
            return convertidor.convertirProductoAProductoDTO(producto);
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new DAOException("Error al consultar el producto por código de barras");
        }
    }

    /**
     * Actualiza un producto en la base de datos.
     *
     * @param producto El producto con los datos actualizados.
     * @throws DAOException Si el producto dado es null, si no se encontro el
     * producto en el sistema o si no se pudo modificar los datos del producto.
     */
    @Override
    public void actualizarProducto(ProductoDTO producto) throws DAOException {
        if (producto == null) {
            throw new DAOException("El producto dado es null");
        }
        try {
            
            ProductoDTO productoEncontrado = this.consultarProducto(producto.getCodigoInterno());
            
            if (productoEncontrado == null) {
                throw new DAOException("No se encontro el producto a actualizar");
            }
            
            Producto entidadProducto = ConvertidorBazarDTO.convertirProductoDTO(productoEncontrado);
            entidadProducto.setFechaRegistro(productoEncontrado.getFechaRegistro());
            em.getTransaction().begin();
            em.merge(entidadProducto);
            em.getTransaction().commit();
        } catch (NullPointerException ex) {
            throw new DAOException("El producto no se actualizo por falta de IDs");
        } catch (Exception ex) {
            
            if (ex.getClass() == DAOException.class) {
                throw new DAOException(ex.getMessage());
            }
            
            throw new DAOException("Error al actualizar el producto");
        }

    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param codigoInterno El codigo interno del producto que se desea
     * eliminar.
     * @throws DAOException Si ocurre un error al eliminar el producto.
     */
    @Override
    public void eliminarProducto(String codigoInterno) throws DAOException {
        if (codigoInterno == null) {
            throw new DAOException("El código interno del producto dado es null");
        }

        try {
            // Consultar el producto por su código interno
            ProductoDTO productoDTO = consultarProducto(codigoInterno);

            if (productoDTO != null) {
                // Convertir ProductoDTO a Producto utilizando el convertidor
                Producto entidadProducto = ConvertidorBazarDTO.convertirProductoDTO(productoDTO);

                // Iniciar la transacción y eliminar el producto
                em.getTransaction().begin();
                em.remove(em.merge(entidadProducto)); // Merge y remove en una línea
                em.getTransaction().commit();
            } else {
                throw new DAOException("El producto no se encuentra en la base de datos");
            }
        } catch (NullPointerException ex) {
            throw new DAOException("El producto no se elimino por falta de IDs");
        } catch (Exception ex) {
            
            if (ex.getClass() == DAOException.class) {
                throw new DAOException(ex.getMessage());
            }
            
            throw new DAOException("Error al eliminar el producto: ");
        }
    }

}
