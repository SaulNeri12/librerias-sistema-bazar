package dao;

import conexion.EntityManagerSingleton;
import entidades.Usuario;
import static entidades.convertidor.ConvertidorBazarDTO.convertirUsuarioDTO;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import objetosNegocio.UsuarioDTO;
import subsistemas.excepciones.DAOException;
import subsistemas.interfaces.IGestorUsuarios;

/**
 * Implementacion del subsistema de usuarios
 *
 * @author saul
 */
public class GestorUsuarios implements IGestorUsuarios {

    private static GestorUsuarios instance;
    private final EntityManager em;

    /**
     * Crea una instancia del subsistema Usuarios
     */
    private GestorUsuarios() {
        this.em = EntityManagerSingleton.getInstance().getEntityManager();
    }

    /**
     * Regresa la instancia del gestor de usuarios
     *
     * @return La unica instancia del gestor de usuarios de la aplicacion
     */
    public static GestorUsuarios getInstance() {
        if (instance == null) {
            instance = new GestorUsuarios();
        }

        return instance;
    }

    /**
     * Consulta un usuario por su id en la base de datos.
     *
     * @param id El id del usuario que se desea consultar.
     * @return El usuario con el id dado, o null si no se encontro.
     * @throws DAOException Si el id es null, menor a 1, o ocurre un error al
     * ejecutar la consulta en la base de datos
     */
    @Override
    public UsuarioDTO consultarUsuario(Long id) throws DAOException {
        if (id == null) {
            throw new DAOException("El id del usuario dado es null");
        }

        try {
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                return usuario.toDTO();
            }
            return null;
        } catch (Exception ex) {
            /*
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            */
            
            if (ex.getClass() == DAOException.class) {
                throw new DAOException(ex.getMessage());
            }
            
            throw new DAOException("Error al consultar el usuario por id");
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
        
        if (telefono == null) {
            throw new DAOException("El telefono del usuario dado es null");
        }

        try {
            TypedQuery<Usuario> consulta = em.createNamedQuery(
                    "consultaUsuarioNumTelefono", Usuario.class);
            consulta.setParameter("telefono", telefono);
            return consulta.getSingleResult().toDTO();
        } catch (NoResultException nre) {
            
            /*
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, nre.getMessage() + "AQUO!!!!");
            */
            return null;
        }catch (Exception ex) {
            /*
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            */
            
            if (ex.getClass() == DAOException.class) {
                throw new DAOException(ex.getMessage());
            }
            
            throw new DAOException("Error al consultar el usuario por telefono");
        }
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @throws DAOException si ocurre un error al registrar el usuario
     */
    @Override
    public void registrarUsuario(UsuarioDTO usuario) throws DAOException {
        if (usuario == null) {
            throw new DAOException("El usuario especificado es null");
        }

        if (usuario.getNombre() == null || usuario.getNombre().isBlank()
                || usuario.getApellido() == null || usuario.getApellido().isBlank()) {
            throw new DAOException("El usuario debe tener un nombre valido");
        }
        if (usuario.getContrasena() == null || usuario.getContrasena().equals("")) {
            throw new DAOException("Introduzca una contraseña valida");
        }

        if (usuario.getDireccion() == null) {
            throw new DAOException("El usuario debe tener una direccion");
        }

        try {
            em.getTransaction().begin();

            LocalDateTime fechaHoraActual = LocalDateTime.now();
            usuario.setFechaContratacion(fechaHoraActual);

            Usuario usuarioEntity = convertirUsuarioDTO(usuario);
            em.persist(usuarioEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            /*
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            */
            
            if (ex.getClass() == DAOException.class) {
                throw new DAOException(ex.getMessage());
            }
            
            throw new DAOException("Error al registrar el usuario");
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
        
        if (usuario == null) {
            throw new DAOException("El usuario no existe en la base de datos");
        }

        Usuario entity = em.find(Usuario.class, usuario.getId());
        if (entity == null) {
            throw new DAOException("No se encontro el usuario a actualizar");
        }

        try {
            Usuario usuarioEntity = convertirUsuarioDTO(usuario);

            em.getTransaction().begin();
            em.merge(usuarioEntity);
            em.getTransaction().commit();

        } catch (Exception ex) {
            /*
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            */
            if (ex.getClass() == DAOException.class) {
                throw new DAOException(ex.getMessage());
            }
            
            throw new DAOException("Error al actualizar el usuario");
        }
    }

    /**
     * Elimina un usuario en la base de datos.
     *
     * @param id El id del usuario que se desea eliminar.
     * @throws DAOException Si el usuario dado es null, si no se pudo eliminar
     * el usuario.
     */
    @Override
    public void eliminarUsuario(Long id) throws DAOException {
        if (id == null) {
            throw new DAOException("El ID de usuario dado es null");
        }

        try {
            TypedQuery<Usuario> consulta = em.createNamedQuery(
                    "consultaUsuarioID", Usuario.class);
            consulta.setParameter("id", id);
            em.getTransaction().begin();
            em.remove(consulta.getSingleResult());
            em.getTransaction().commit();
        } catch (NoResultException nre) {
            /*
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, nre.getMessage());
            */
            throw new DAOException("No se encontro al usuario en la base de datos");
        } catch (Exception ex) {
            /*
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            */
            
            if (ex.getClass() == DAOException.class) {
                throw new DAOException(ex.getMessage());
            }
            
            throw new DAOException("Ocurrio un error al eliminar al usuario");
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
        
        if (telefono == null) {
            throw new DAOException("El telefono dado es null");
        }

        if (contrasena == null || contrasena.isBlank()) {
            throw new DAOException("La contraseña dada es null");
        }

        try {
            
            TypedQuery<Usuario> query = em.createNamedQuery("inicioSesion", Usuario.class);
            query.setParameter("telefono", telefono);
            
            Usuario usuarioEntity = query.getSingleResult();
            
            if (!usuarioEntity.getContrasena().equals(contrasena)) {
                throw new DAOException("La contraseña es incorrecta");
            }
            
            return usuarioEntity.toDTO();
        } catch (NoResultException nre) {
            /*
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, nre.getMessage());
            */
            throw new DAOException("No existe un usuario con el telefono proporcionado");
        } catch (Exception ex) {
            
            /*
            Logger.getLogger(GestorUsuarios.class.getName()).log(
                    Level.SEVERE, ex.getMessage());
            */
            
            if (ex.getClass() == DAOException.class) {
                throw new DAOException(ex.getMessage());
            }
            
            throw new DAOException("Error al intentar iniciar sesion");
        }
    }
}
