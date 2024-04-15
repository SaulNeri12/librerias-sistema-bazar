package dao;

import conexion.EntityManagerSingleton;
import entidades.Usuario;
import excepciones.DAOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import objetosNegocio.UsuarioDTO;
import usuarios.IGestorUsuarios;

/**
 * Implementacion del subsistema de usuarios con listas.
 *
 * @author saul
 */
public class GestorUsuarios implements IGestorUsuarios {

    private List<UsuarioDTO> usuarios;

    private static GestorUsuarios instance;
    private final EntityManager em;

    public GestorUsuarios(EntityManager em) {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();
    }

    /**
     * Crea una instancia del subsistema Usuarios
     */
    public GestorUsuarios() {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();

        this.usuarios = new ArrayList<>();

        UsuarioDTO admin = new UsuarioDTO();

        admin.setId(1l);
        admin.setNombre("ADMINISTRADOR");
        admin.setPuesto(UsuarioDTO.Puesto.ADMIN);
        admin.setTelefono("1000000000");
        admin.setContrasena("admin");

        this.usuarios.add(admin);

        UsuarioDTO nuevoUsuario = new UsuarioDTO();
        nuevoUsuario.setId(1l);
        nuevoUsuario.setNombre("Saul Armando Neri Escarcega");
        nuevoUsuario.setPuesto(UsuarioDTO.Puesto.CAJERO);
        nuevoUsuario.setTelefono("6442269619");
        nuevoUsuario.setContrasena("saulneri12");

        this.usuarios.add(nuevoUsuario);
    }

    public static GestorUsuarios getInstance() {
        if (instance == null)
        {
            instance = new GestorUsuarios(EntityManagerSingleton.getInstance().getEntityManager());
        }

        return instance;
    }

    /**
     * Consulta un usuario por su id en la base de datos.
     *
     * @param id El id del usuario que se desea consultar.
     * @return El usuario con el id dado, o null si no se encontro.
     * @throws DAOException Si ocurre un error al consultar el usuario por su
     * id.
     */
    @Override
    public UsuarioDTO consultarUsuario(Long id) throws DAOException {
        if (id == null)
        {
            throw new DAOException("El id del usuario dado es null");
        }

        try
        {
            TypedQuery<UsuarioDTO> consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :id", UsuarioDTO.class);
            consulta.setParameter("id", id);
            return consulta.getSingleResult();
        } catch (Exception ex)
        {
            throw new DAOException("Error al consultar el usuario por id", ex);
        }
    }

    /**
     * Consulta un usuario por su telefono en la base de datos.
     *
     * @param telefono El telefono del usuario que se desea consultar.
     * @return El usuario con el telefono dado, o null si no se encontro.
     * @throws DAOException Si ocurre un error al consultar el usuario por su
     * telefono.
     */
    @Override
    public UsuarioDTO consultarUsuarioPorNumeroTelefono(String telefono) throws DAOException {
        if (telefono == null)
        {
            throw new DAOException("El telefono del usuario dado es null");
        }

        try
        {
            TypedQuery<UsuarioDTO> consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.telefono = :telefono", UsuarioDTO.class);
            consulta.setParameter("telefono", telefono);
            return consulta.getSingleResult();
        } catch (Exception ex)
        {
            throw new DAOException("Error al consultar el usuario por telefono", ex);
        }
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     */
    @Override
    public void registrarUsuario(UsuarioDTO usuario) throws DAOException {
        if (usuario == null)
        {
            throw new DAOException("El usuario especificado es null");
        }

        try
        {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            throw new DAOException("Error al registrar el usuario", ex);
        }
    }

    /**
     * Actualiza un usuario en la base de datos.
     *
     * @param usuario El usuario con los datos actualizados.
     * @throws DAOException Si el usuario dado es null, si no se encontro el
     * usuario en el sistema o si no se pudo modificar los datos del usuario.
     */
    @Override
    public void actualizarUsuario(UsuarioDTO usuario) throws DAOException {
        try
        {
            Usuario usuarioEntity = em.find(Usuario.class, usuario.getId());

            if (usuario == null)
            {
                throw new DAOException("El usuario no existe en la base de datos");
            }

            usuarioEntity.setNombres(usuario.getNombre());
            usuarioEntity.setFechaContratacion(usuario.getFechaContratacion());
            usuarioEntity.setPuesto(Usuario.Puesto.valueOf(usuario.getPuesto().name()));
            usuarioEntity.setTelefono(usuario.getTelefono());
            usuarioEntity.setContrasenha(usuario.getContrasena());

            em.getTransaction().begin();
            em.merge(usuarioEntity);
            em.getTransaction().commit();

        } catch (Exception ex)
        {
            throw new DAOException("Error al actualizar el producto", ex);
        }
    }

    /**
     * Elimina un usuario en la base de datos.
     *
     * @param idUsuario El id del usuario que se desea eliminar.
     * @throws DAOException Si el usuario dado es null, si no se pudo eliminar
     * el usuario.
     */
    @Override
    public void eliminarUsuario(Long idUsuario) throws DAOException {
        if (idUsuario == null)
        {
            throw new DAOException("El ID de usuario dado es null");
        }

        try
        {
            UsuarioDTO usuario = consultarUsuario(idUsuario);
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            throw new DAOException("Error al eliminar el usuario");
        }
    }

    /**
     * Inicia sesion por su telefono y contraseña en la base de datos.
     *
     * @param telefono El telefono del usuario que se desea iniciar sesion.
     * @param contrasena La contraseña del usuario que se desea iniciar sesion.
     * @return UsuarioDTO conteniendo los datos del usuario si la autenticación
     * es exitosa.
     * @throws DAOException Si el teléfono o la contraseña son null, o si ocurre
     * cualquier otro error durante la consulta en la base de datos. Esto
     */
    @Override
    public UsuarioDTO iniciarSesion(String telefono, String contrasena) throws DAOException {
        if (telefono == null)
        {
            throw new DAOException("El telefono dado es null");
        }

        if (contrasena == null)
        {
            throw new DAOException("La contrasena dada es null");
        }

        try
        {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.telefono = :telefono AND u.contrasenha = :contrasena", Usuario.class);
            query.setParameter("telefono", telefono);
            query.setParameter("contrasena", contrasena);

            Usuario usuarioEntity = query.getSingleResult();

            // Convertir el Usuario a UsuarioDTO si la autenticación es exitosa
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuarioEntity.getId());
            usuarioDTO.setNombre(usuarioEntity.getNombres());
            usuarioDTO.setFechaContratacion(usuarioEntity.getFechaContratacion());
            usuarioDTO.setPuesto(UsuarioDTO.Puesto.valueOf(usuarioEntity.getPuesto().name()));
            usuarioDTO.setTelefono(usuarioEntity.getTelefono());
            usuarioDTO.setContrasena(usuarioEntity.getContrasenha());

            return usuarioDTO;
        } catch (Exception ex)
        {
            throw new DAOException("Error al intentar iniciar sesion", ex);
        }
    }
}
