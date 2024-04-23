
package subsistemas.interfaces;

import subsistemas.excepciones.DAOException;
import objetosNegocio.UsuarioDTO;

/**
 * Define las operaciones basicas para el subsistema de Usuarios
 * @author saul
 */
public interface IGestorUsuarios {
    
    /**
     * Busca y regresa un usuario con el ID especificado.
     * @param id ID del usuario a buscar.
     * @return Usuario si se encuentra, null en caso contrario
     * @throws excepciones.DAOException En caso de error
     */
    public UsuarioDTO consultarUsuario(Long id) throws DAOException;
    
    /**
     * Busca al usuario con el telefono especificado y lo regresa.
     * @param telefono Telefono del usuario a buscar
     * @return Usuario si se encuentra, null en caso contrario
     * @throws excepciones.DAOException En caso de error.
     */
    public UsuarioDTO consultarUsuarioPorNumeroTelefono(String telefono) throws DAOException;
    
    /**
     * Registra un usuario en el sistema (base de datos)
     * @param usuario Usuario a registrar
     * @throws excepciones.DAOException En caso de que no se pueda registrar el usuario
     */
    public void registrarUsuario(UsuarioDTO usuario) throws DAOException;
    
    /**
     * Modifica la informacion del usuario especificado en el sistema.
     * @param usuario Usuario a modificar
     * @throws excepciones.DAOException En caso de que no se pueda actualizar los datos del usuario
     */
    public void actualizarUsuario(UsuarioDTO usuario) throws DAOException;
    
    /**
     * Elimina al usuario con el ID especificado en el sistema.
     * @param id ID del usuario a eliminar.
     * @throws excepciones.DAOException En caso de que no se pueda eliminar el usuario.
     */
    public void eliminarUsuario(Long id) throws DAOException;
    
    /**
     * Verifica si las credenciales de usuario proporcionadas coinciden con
     * la presente en el sistema y regresa al usuario con esas credenciales.
     * @param telefono Telefono del usuario.
     * @param contrasena Contrasena del usuario.
     * @return
     * @throws excepciones.DAOException En caso de cualquier error 
     * en el incio de sesion del usuaro, telefono o contrasena incorrectos, 
     * errores internos, etc.
     */
    public UsuarioDTO iniciarSesion(String telefono, String contrasena) throws DAOException;
}

