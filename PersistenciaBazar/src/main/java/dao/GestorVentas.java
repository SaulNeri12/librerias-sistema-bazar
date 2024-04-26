package dao;

import conexion.EntityManagerSingleton;
import entidades.Usuario;
import entidades.Venta;
import static entidades.convertidor.ConvertidorBazarDTO.convertirVentaDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import objetosNegocio.VentaDTO;
import subsistemas.excepciones.DAOException;
import subsistemas.interfaces.IGestorVentas;

/**
 * Implementacion del subsistema de ventas con listas.
 *
 * @author saul
 */
public class GestorVentas implements IGestorVentas {

    private static GestorVentas instance;
    private final EntityManager em;

    private GestorVentas() {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();
    }

    public static GestorVentas getInstance() {
        if (instance == null) {
            instance = new GestorVentas();
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
        if (id == null) {
            throw new DAOException("El ID de la venta dado es null");
        }

        try {
            TypedQuery<Venta> consulta = em.createNamedQuery(
                    "consultaVentaID", Venta.class);
            consulta.setParameter("id", id);
            return consulta.getSingleResult().toDTO();
        } catch (Exception ex) {
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            throw new DAOException("Error al consultar la venta por id");
        }
    }

    /**
     * Consulta las ventas registrados en la base de datos que coincidan con el
     * id dado.
     *
     * @param id Id del usuario a buscar.
     * @return Lista de ventas registrados en la base de datos que coinciden con
     * el nombre dado.
     * @throws DAOException Si ocurre un error al consultar los proveedores.
     */
    @Override
    public List<VentaDTO> consultarVentasDeUsuario(Long id) throws DAOException {
        if (id == null) {
            throw new DAOException("El ID del usuario dado es null");
        }

        try {
            TypedQuery<Venta> consulta = em.createNamedQuery(
                    "consultaVentasUsuario", Venta.class);
            consulta.setParameter("id", id);
            List<Venta> ventas = consulta.getResultList();
            
            List<VentaDTO> ventaDTOs = new ArrayList<>();
            for (Venta venta : ventas) {
                ventaDTOs.add(venta.toDTO());
            }
            return ventaDTOs;
        } catch (Exception ex) {
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
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
        if (fechaInicio == null) {
            throw new DAOException("La fecha de inicio dada es null");
        }

        if (fechaFin == null) {
            throw new DAOException("La fecha de fin dada es null");
        }

        try {
            TypedQuery<Venta> consulta = em.createNamedQuery(
                    "consultaVentasPeriodo", Venta.class);
            consulta.setParameter("fechaInicio", fechaInicio);
            consulta.setParameter("fechaFin", fechaFin);
            List<Venta> ventas = consulta.getResultList();
            
            List<VentaDTO> ventaDTOs = new ArrayList<>();
            for (Venta venta : ventas) {
                ventaDTOs.add(venta.toDTO());
            }
            return ventaDTOs;
        } catch (Exception ex) {
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
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

        try {
            TypedQuery<Venta> consulta = em.createNamedQuery(
                    "consultaVentas", Venta.class);
            List<Venta> ventas = consulta.getResultList();
            List<VentaDTO> ventaDTOs = new ArrayList<>();
            for (Venta venta : ventas) {
                ventaDTOs.add(venta.toDTO());
            }
            return ventaDTOs;
        } catch (Exception ex) {
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            throw new DAOException("Error al consultar las ventas");
        }
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param venta La venta a registrar
     * @throws DAOException si la venta dada es null, si no se pudo registrar la
     * venta
     */
    @Override
    public void registrarVenta(VentaDTO venta) throws DAOException {
        if (venta == null) {
            throw new DAOException("La venta dada es null");
        }

        try {
            Venta ventaEntity = convertirVentaDTO(venta);

            em.getTransaction().begin();
            em.persist(ventaEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
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
        try {
            Venta ventaEntity = em.find(Venta.class, venta.getId());
            if (ventaEntity == null) {
                throw new DAOException("La venta no se encuentra registrada");
            }
            ventaEntity.setNombreCliente(venta.getNombreCliente());
            ventaEntity.setApellidoCliente(venta.getApellidoCliente());
            ventaEntity.setMontoToal(venta.getMontoTotal());
            ventaEntity.setMetodoPago(Venta.MetodoPago.valueOf(venta.getMetodoPago().name()));

            if (venta.getUsuario().getId() != null) {
                Usuario usuario = em.find(Usuario.class, venta.getUsuario().getId());
                if (usuario == null) {
                    throw new DAOException("El usuario con ID " + venta.getUsuario().getId() + " no existe");
                }
                ventaEntity.setUsuario(usuario);
            }

            em.merge(ventaEntity);
            em.getTransaction().commit();

        } catch (DAOException de) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, de.getMessage());
            throw new DAOException("Error al actualizar la venta");
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            throw new DAOException("Error al actualizar la venta");
        }
    }

    /**
     * Elimina una venta en la base de datos.
     *
     * @param id El id de la venta que se desea eliminar.
     * @throws DAOException Si la venta dada es null, si no se pudo eliminar la
     * venta.
     */
    @Override
    public void eliminarVenta(Long id) throws DAOException {
        if (id == null) {
            throw new DAOException("El ID de la venta dado es null");
        }

        try {
            VentaDTO venta = consultarVenta(id);

            if (venta == null) {
                throw new DAOException("No se encontro la venta a eliminar");
            }

            em.getTransaction().begin();
            em.remove(venta);
            em.getTransaction().commit();
        } catch (DAOException de) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, de.getMessage());
            throw new DAOException(de.getMessage());
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            throw new DAOException("Error al actualizar la venta");
        }
    }

}
