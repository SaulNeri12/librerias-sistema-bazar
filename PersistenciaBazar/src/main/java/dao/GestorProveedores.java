package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import conexion.EntityManagerSingleton;
import entidades.Proveedor;
import entidades.convertidor.ConvertidorBazarDTO;
import java.util.ArrayList;
import objetosNegocio.ProveedorDTO;
import subsistemas.excepciones.DAOException;

import subsistemas.interfaces.IGestorProveedores;

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

    public static GestorProveedores getInstance() {
        if (instancia == null) {
            instancia = new GestorProveedores(EntityManagerSingleton.getInstance().getEntityManager());
        }
        return instancia;
    }

    /**
     * Consulta todos los proveedores registrados en la base de datos. Debido a
     * que es una consulta, se devuelven DTOs en lugar de entidades.
     *
     * @return Lista de proveedores registrados en la base de datos.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public List<ProveedorDTO> consultarTodos() throws DAOException {
        try {
            TypedQuery<Proveedor> consulta = em.createQuery("SELECT p FROM Proveedor p", Proveedor.class);
            List<Proveedor> proveedores = consulta.getResultList();
            if (proveedores.isEmpty()) {
                throw new DAOException("No se encontraron proveedores en la base de datos");
            }
            // Convertir las entidades Proveedor a DTOs
            List<ProveedorDTO> proveedoresDTO = new ArrayList<>();
            ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
            for (Proveedor proveedor : proveedores) {
                proveedoresDTO.add(convertidor.convertirProveedorAProveedorDTO(proveedor));
            }

            return proveedoresDTO;
        } catch (Exception ex) {
            throw new DAOException("Error al consultar todos los proveedores");
        }
    }

    /**
     * Consulta los proveedores registrados en la base de datos que coincidan
     * con el nombre dado.
     *
     * @param nombreProveedor Nombre del proveedor a buscar.
     * @return Lista de proveedores registrados en la base de datos que
     * coinciden con el nombre dado.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public List<ProveedorDTO> consultarProveedoresPorNombre(String nombreProveedor) throws DAOException {
        try {
            TypedQuery<Proveedor> query = em.createQuery("SELECT p FROM Proveedor p WHERE p.nombre LIKE :nombre",
                    Proveedor.class);
            query.setParameter("nombre", "%" + nombreProveedor + "%");
            List<Proveedor> proveedores = query.getResultList();
            if (proveedores.isEmpty()) {
                throw new DAOException("No se encontraron proveedores con el nombre proporcionado");
            }
            // Convertir las entidades Proveedor a DTOs
            List<ProveedorDTO> proveedoresDTO = new ArrayList<>();
            ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
            for (Proveedor proveedor : proveedores) {
                proveedoresDTO.add(convertidor.convertirProveedorAProveedorDTO(proveedor));
            }

            return proveedoresDTO;
        } catch (Exception e) {
            throw new DAOException("Error al consultar los proveedores por nombre");
        }
    }

    /**
     * Consulta los proveedores registrados en la base de datos que coincidan
     * con el id dado.
     *
     * @param idProveedor ID del proveedor a buscar.
     * @return Proveedor registrado en la base de datos que coincide con el ID
     * dado.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public ProveedorDTO consultarProveedor(Long idProveedor) throws DAOException {
        try {
            Proveedor proveedor = em.find(Proveedor.class, idProveedor);
            if (proveedor != null) {
                ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
                return convertidor.convertirProveedorAProveedorDTO(proveedor);
            } else {
                throw new DAOException("No se encontró ningún proveedor con el ID proporcionado");
            }
        } catch (Exception e) {
            throw new DAOException("Error al consultar el proveedor por ID");
        }
    }

    /**
     * Consulta los proveedores registrados en la base de datos que coincidan
     * con el numero de teléfono dado.
     *
     * @param telefono Numero de teléfono del proveedor a buscar.
     * @return Proveedor registrado en la base de datos que coincide con el
     * numero de teléfono dado.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public ProveedorDTO consultarProveedorPorNumeroTelefono(String telefono) throws DAOException {
        try {
            TypedQuery<Proveedor> query = em.createQuery("SELECT p FROM Proveedor p WHERE p.telefono = :telefono", Proveedor.class);
            query.setParameter("telefono", telefono);
            List<Proveedor> proveedores = query.getResultList();

            if (proveedores.isEmpty()) {
                throw new DAOException("No se encontró ningún proveedor con el número de teléfono proporcionado");
            }

            ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
            return convertidor.convertirProveedorAProveedorDTO(proveedores.get(0));
        } catch (Exception e) {
            throw new DAOException("Error al consultar el proveedor por número de teléfono");
        }
    }

    /**
     * Método para registrar un proveedor en la base de datos. Recibe un objeto
     * de tipo ProveedorDTO, lo convierte a Entidad utilizando una clase de
     * utilidad y lo registra en la base de datos.
     *
     * @param proveedor
     * @throws DAOException
     */
    @Override
    public void registrarProveedor(ProveedorDTO proveedor) throws DAOException {

        if (proveedor == null) {
            throw new DAOException("El proveedor dado es null");
        }
        ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
        Proveedor entidadProveedor = convertidor.convertirProveedorDTO(proveedor);

        try {
            em.getTransaction().begin();
            em.persist(entidadProveedor);
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
        if (proveedor == null) {
            throw new DAOException("El proveedor dado es null");
        }

        ConvertidorBazarDTO convertidor = new ConvertidorBazarDTO();
        Proveedor entidadProveedor = convertidor.convertirProveedorDTO(proveedor);

        try {
            em.getTransaction().begin();
            entidadProveedor = em.merge(entidadProveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException("Error al actualizar el proveedor");
        }
    }

    /**
     * Elimina un proveedor de la base de datos.
     *
     * @param idProveedor ID del proveedor a eliminar.
     * @throws DAOException Si ocurre un error al eliminar el proveedor.
     */
    @Override
    public void eliminarProveedor(Long idProveedor) throws DAOException {
        if (idProveedor == null) {
            throw new DAOException("El ID del proveedor dado es null");
        }

        Proveedor proveedor = em.find(Proveedor.class, idProveedor);
        if (proveedor == null) {
            throw new DAOException("El proveedor con ID " + idProveedor + " no existe en la base de datos");
        }

        try {
            em.getTransaction().begin();
            em.remove(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException("Error al eliminar el proveedor");
        }
    }

}
