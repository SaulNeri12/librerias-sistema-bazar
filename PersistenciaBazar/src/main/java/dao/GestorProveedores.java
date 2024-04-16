
package dao;

import excepciones.DAOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import conexion.EntityManagerSingleton;
import entidades.Proveedor;
import objetosNegocio.ProveedorDTO;
import proveedores.IGestorProveedores;

/**
 * Implementacion del subsistema de Proveedores con listas.
 * 
 * @author saul
 */
public class GestorProveedores implements IGestorProveedores {

    
    private static GestorProveedores instancia;
    private final EntityManager em;

    public GestorProveedores() {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();
    }
    public GestorProveedores(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static GestorProveedores getInstancie() {
        if (instancia == null) {
            instancia = new GestorProveedores(EntityManagerSingleton.getInstance().getEntityManager());
        }
        return instancia;
    }

    /**
     * Consulta todos los proveedores registrados en la base de datos.
     * 
     * @return Lista de proveedores registrados en la base de datos.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public List<ProveedorDTO> consultarTodos() throws DAOException {
        try {
            TypedQuery<ProveedorDTO> query = em.createNamedQuery("ProveedorDTO.findAll", ProveedorDTO.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al consultar los proveedores");
        }
    }

    /**
     * Consulta los proveedores registrados en la base de datos que coincidan con el
     * nombre dado.
     * 
     * @param nombreProveedor Nombre del proveedor a buscar.
     * @return Lista de proveedores registrados en la base de datos que coinciden
     *         con el nombre dado.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public List<ProveedorDTO> consultarProveedoresPorNombre(String nombreProveedor) throws DAOException {
        try {
            TypedQuery<ProveedorDTO> query = em.createNamedQuery("SELECT p FROM proveedores p WHERE p.nombre = :nombre",
                    ProveedorDTO.class);
            query.setParameter("nombre", nombreProveedor);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al consultar los proveedores");
        }
    }

    /**
     * Consulta los proveedores registrados en la base de datos que coincidan con el
     * id dado.
     * 
     * @param idProveedor ID del proveedor a buscar.
     * @return Proveedor registrado en la base de datos que coincide con el ID dado.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public ProveedorDTO consultarProveedor(Long idProveedor) throws DAOException {
        try {
            TypedQuery<ProveedorDTO> query = em.createNamedQuery("SELECT p FROM proveedores p WHERE p.id = :id",ProveedorDTO.class);
            query.setParameter("id", idProveedor);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Error al consultar los proveedores");
        }
    }

    /**
     * Consulta los proveedores registrados en la base de datos que coincidan con el
     * numero de teléfono dado.
     * @param telefono Numero de teléfono del proveedor a buscar.
     * @return Proveedor registrado en la base de datos que coincide con el numero de teléfono dado.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public ProveedorDTO consultarProveedorPorNumeroTelefono(String telefono) throws DAOException {
        try {
            TypedQuery<ProveedorDTO> query = em.createNamedQuery("SELECT p FROM proveedores p WHERE p.telefono = :telefono",ProveedorDTO.class);
            query.setParameter("telefono", telefono);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Error al consultar los proveedores");
        }

    }

    @Override
    public void registrarProveedor(ProveedorDTO proveedor) throws DAOException {

        if (proveedor == null) {
            throw new DAOException("El proveedor dado es null");
        }

        try {
            em.getTransaction().begin();
            em.persist(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException("Error al registrar el proveedor");
        }
    }

    /**
     * Actualiza la información de un proveedor en la base de datos.
     * 
     * @param proveedor Proveedor con la información actualizada.
     * @throws DAOException Si ocurre un error al actualizar el proveedor.
     */
    @Override
    public void actualizarProveedor(ProveedorDTO proveedor) throws DAOException {
            
            try {
                Proveedor proveedorEntity = em.find(Proveedor.class , proveedor.getId());
                if (proveedorEntity==null) {
                    throw new DAOException("El proveedor no se encuentra registrado");
                }
                proveedorEntity.setNombre(proveedor.getNombre());
                proveedorEntity.setTelefono(proveedor.getTelefono());
                proveedorEntity.setEmail(proveedor.getEmail());
                proveedorEntity.setDescripcion(proveedor.getDescripcion());
                proveedorEntity.setFechaRegistro(proveedor.getFechaRegistro());
                em.getTransaction().begin();
                em.merge(proveedorEntity);
                em.getTransaction().commit();

            } catch (Exception e) {
                throw new DAOException("Error al actualizar el proveedor");
            }
        
    }

    /**
     * Elimina un proveedor de la base de datos.
     * @param idProveedor ID del proveedor a eliminar.
     * @throws DAOException Si ocurre un error al eliminar el proveedor.
     */
    @Override
    public void eliminarProveedor(Long idProveedor) throws DAOException {

        try {
            Proveedor proveedor = em.find(Proveedor.class, idProveedor);
            if (proveedor == null) {
                throw new DAOException("El proveedor no se encuentra registrado");
            }
            em.getTransaction().begin();
            em.remove(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException("Error al eliminar el proveedor");
        }
        
    }

}
