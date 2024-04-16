package dao;

import conexion.EntityManagerSingleton;
import entidades.Usuario;
import entidades.Venta;
import excepciones.DAOException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import objetosNegocio.VentaDTO;
import ventas.IGestorVentas;

/**
 * Implementacion del subsistema de ventas con listas.
 *
 * @author saul
 */
public class GestorVentas implements IGestorVentas {

    private static GestorVentas instance;
    private final EntityManager em;

    public GestorVentas(EntityManager em) {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();

    }

    public GestorVentas() {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();
    }

    public static GestorVentas getInstance() {
        if (instance == null)
        {
            instance = new GestorVentas(EntityManagerSingleton.getInstance().getEntityManager());
        }

        return instance;
    }

    /**
     * Consulta una venta por su id en la base de datos.
     *
     * @param id El id de la venta que se desea consultar.
     * @return La venta con el id dado, o null si no se encontro.
     * @throws DAOException Si ocurre un error al consultar la venta por su id.
     */
    @Override
    public VentaDTO consultarVenta(Long id) throws DAOException {
        if (id == null)
        {
            throw new DAOException("El ID de la venta dado es null");
        }

        try
        {
            TypedQuery<VentaDTO> consulta = em.createQuery("SELECT v FROM Venta v WHERE v.id = :id", VentaDTO.class);
            consulta.setParameter("id", id);
            return consulta.getSingleResult();
        } catch (Exception ex)
        {
            throw new DAOException("Error al consultar la venta por id");
        }
    }

    /**
     * Consulta las ventas registrados en la base de datos que coincidan con el
     * id dado.
     *
     * @param id Id de la venta a buscar.
     * @return Lista de ventas registrados en la base de datos que coinciden con
     * el nombre dado.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public List<VentaDTO> consultarVentasDeUsuario(Long id) throws DAOException {
        if (id == null)
        {
            throw new DAOException("El ID del usuario dado es null");
        }

        try
        {
            TypedQuery<VentaDTO> consulta = em.createQuery("SELECT v FROM Venta v WHERE v.usuario.id = :id", VentaDTO.class);
            consulta.setParameter("id", id);
            return consulta.getResultList();
        } catch (Exception ex)
        {
            throw new DAOException("Error al consultar las ventas");
        }
    }

    /**
     * Consulta las ventas registrados en la base de datos que coincidan con el
     * periodo dado.
     *
     * @param fechaInicio Fecha inicio a buscar.
     * @param fechaFin Fecha fin a buscar.
     * @return Lista de ventas registrados en la base de datos que coinciden el
     * periodo dado.
     * @throws DAOException Si ocurre un error al consultar las ventas.
     */
    @Override
    public List<VentaDTO> consultarVentasPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws DAOException {
        if (fechaInicio == null)
        {
            throw new DAOException("La fecha de inicio dada es null");
        }

        if (fechaFin == null)
        {
            throw new DAOException("La fecha fin dada es null");
        }

        try
        {

            TypedQuery<VentaDTO> consulta = em.createQuery("SELECT new objetosNegocio.VentaDTO(V.id, V.fechaVenta, v.montoToal) FROM Venta v WHERE v.fechaVenta BETWEEN :fechaInicio AND :fechaFin", VentaDTO.class);
            consulta.setParameter("fechaInicio", fechaInicio);
            consulta.setParameter("fechaFin", fechaFin);
            return consulta.getResultList();
        } catch (Exception ex)
        {
            throw new DAOException("Error al consultar las ventas por periodo");
        }

    }

    /**
     * Consulta todas las ventas registrados en la base de datos.
     *
     * @return Lista de ventas registrados en la base de datos.
     * @throws DAOException Si ocurre un error al consultar las ventas.
     */
    @Override
    public List<VentaDTO> consultarTodos() throws DAOException {

        try
        {
            TypedQuery<VentaDTO> consulta = em.createNamedQuery("ProveedorDTO.findAll", VentaDTO.class);
            return consulta.getResultList();
        } catch (Exception ex)
        {
            throw new DAOException("Error al consultar las ventas");
        }
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param venta La venta a registrar
     * @throws DAOExeption si la venta dada es null, si no se pudo registrar la
     * venta
     */
    @Override
    public void registrarVenta(VentaDTO venta) throws DAOException {
        if (venta == null)
        {
            throw new DAOException("La venta dada es null");
        }

        try
        {
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            throw new DAOException("Error al registrar la venta");
        }
    }

    /**
     * Actualiza los datos de una venta en el sistema.
     *
     * @param venta La venta con los datos actualizados.
     * @throws DAOException Si la venta dada es null, si no se encontro la venta
     * en el sistema o si no se pudo modificar los datos de la venta.
     */
    @Override
    public void actualizarVenta(VentaDTO venta) throws DAOException {
        try
        {
            Venta ventaEntity = em.find(Venta.class, venta.getId());
            if (ventaEntity == null)
            {
                throw new DAOException("La venta no se encuentra registrada");
            }
            ventaEntity.setNombreCliente(venta.getNombreCliente());
            ventaEntity.setApellidoCliente(venta.getApellidoCliente());
            ventaEntity.setMontoToal(venta.getMontoTotal());
            ventaEntity.setMetodoPago(Venta.MetodoPago.valueOf(venta.getMetodoPago().name()));

            if (venta.getUsuario().getId() != null)
            {
                Usuario usuario = em.find(Usuario.class, venta.getUsuario().getId());
                if (usuario == null)
                {
                    throw new DAOException("El usuario con ID " + venta.getUsuario().getId() + " no existe");
                }
                ventaEntity.setUsuario(usuario);
            }

            em.merge(ventaEntity);
            em.getTransaction().commit();

        } catch (Exception ex)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            throw new DAOException("Error al actualizar la venta");
        }
    }

    /**
     * Elimina una venta en la base de datos.
     *
     * @param idVenta El id de la venta que se desea eliminar.
     * @throws DAOException Si la venta dada es null, si no se pudo eliminar la
     * venta.
     */
    @Override
    public void eliminarVenta(Long idVenta) throws DAOException {
        if (idVenta == null)
        {
            throw new DAOException("El ID de la venta dado es null");
        }

        try
        {
            VentaDTO venta = consultarVenta(idVenta);
            em.getTransaction().begin();
            em.remove(venta);
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            throw new DAOException("Error al eliminar la venta");
        }
    }

}
