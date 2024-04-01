
package usuarios;

import objetosNegocio.Usuario;

/**
 * Define las operaciones basicas para el subsistema de Usuarios
 * @author saul
 */
public interface IGestorUsuarios {
    
    /**
     * Busca y regresa un usuario con el ID especificado.
     * @param id ID del usuario a buscar.
     * @return Usuario si se encuentra, null en caso contrario
     */
    public Usuario consultarUsuario(Long id);
    
    /**
     * Busca al usuario con el telefono especificado y lo regresa.
     * @param telefono Telefono del usuario a buscar
     * @return Usuario si se encuentra, null en caso contrario
     */
    public Usuario consultarUsuarioPorNumeroTelefono(String telefono);
    
    /**
     * Registra un usuario en el sistema (base de datos)
     * @param usuario Usuario a registrar
     */
    public void registrarUsuario(Usuario usuario);
    
    /**
     * Modifica la informacion del usuario especificado en el sistema.
     * @param usuario Usuario a modificar
     */
    public void actualizarUsuario(Usuario usuario);
    
    /**
     * Elimina al usuario con el ID especificado en el sistema.
     * @param id ID del usuario a eliminar.
     */
    public void eliminarUsuario(Long id);
    
    /**
     * Verifica si las credenciales de usuario proporcionadas coinciden con
     * la presente en el sistema.
     * @param telefono Telefono del usuario.
     * @param contrasena Contrasena del usuario.
     * @return true Si las credenciales coinciden, false en caso contrario.
     */
    public boolean iniciarSesion(String telefono, String contrasena);
}











