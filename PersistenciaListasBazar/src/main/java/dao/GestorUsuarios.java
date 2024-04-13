
package dao;

import excepciones.DAOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import objetosNegocio.UsuarioDTO;
import usuarios.IGestorUsuarios;

/**
 * Implementacion del subsistema de usuarios con listas.
 * @author saul
 */
public class GestorUsuarios implements IGestorUsuarios {
    private final List<UsuarioDTO> usuarios;
    
    /**
     * Crea una instancia del subsistema Usuarios
     */
    public GestorUsuarios() {
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
    
    @Override
    public UsuarioDTO consultarUsuario(Long id) {
        if (id == null) {
            return null;
        }
        
        Optional<UsuarioDTO> usuario = this.usuarios.stream()
                .filter(usr -> usr.getId().equals(id))
                .findFirst();
        
        if (usuario.isPresent()) {
            return usuario.get();
        }
        
        return null;
    }

    @Override
    public UsuarioDTO consultarUsuarioPorNumeroTelefono(String telefono) {
        if (telefono == null) {
            return null;
        }
        
        Optional<UsuarioDTO> usuario = this.usuarios.stream()
                .filter(usr -> usr.getTelefono().equals(telefono))
                .findFirst();
        
        if (usuario.isPresent()) {
            return usuario.get();
        }
        
        return null;
    }

    @Override
    public void registrarUsuario(UsuarioDTO usuario) throws DAOException {
        if (usuario == null) {
            throw new DAOException("El usuario especificado es null");
        }
        
        Optional<UsuarioDTO> usuarioEnSistema = this.usuarios.stream()
                .filter(usr -> usr.equals(usuario))
                .findFirst();
        
        if (usuarioEnSistema.isPresent()) {
            throw new DAOException("El usuario ya esta registrado en el sistema");
        }
        
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        
        usuario.setFechaContratacion(fechaHoraActual);
        
        this.usuarios.add(usuario);
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuario) throws DAOException {
        if (usuario == null) {
            throw new DAOException("El usuario especificado es null");
        }
        
        Optional<UsuarioDTO> usuarioEnSistema = this.usuarios.stream()
                .filter(usr -> usr.equals(usuario))
                .findFirst();
        
        if (!usuarioEnSistema.isPresent()) {
            throw new DAOException("No se encontro el usuario especificado");
        }
        
        int index = this.usuarios.indexOf(usuario);
        
        if (index < 0) {
            throw new DAOException("No se pudo actualizar la informacion del usuario debido a un error");
        }
        
        usuario.setFechaContratacion(usuarioEnSistema.get().getFechaContratacion());
        
        this.usuarios.set(index, usuario);
    }

    @Override
    public void eliminarUsuario(Long idUsuario) throws DAOException {
        if (idUsuario == null) {
            throw new DAOException("El ID de usuario dado es null");
        }
        
        Optional<UsuarioDTO> usuarioEnSistema = this.usuarios.stream()
                .filter(usr -> usr.getId().equals(idUsuario))
                .findFirst();
        
        if (usuarioEnSistema.isPresent()) {
            boolean eliminado = this.usuarios.remove(usuarioEnSistema.get());
        
            if (!eliminado) {
                throw new DAOException("No se pudo eliminar al usuario");
            }
        }
        
        throw new DAOException("No se encontro al usuario con el ID especificado");
    }

    @Override
    public UsuarioDTO iniciarSesion(String telefono, String contrasena) throws DAOException {
        if (telefono == null) {
            throw new DAOException("El telefono dado es null");
        }
        
        if (contrasena == null) {
            throw new DAOException("La contrasena dada es null");
        }
        
        Optional<UsuarioDTO> usuarioEnSistema = this.usuarios.stream()
                .filter(usr -> usr.getTelefono().equals(telefono))
                .findFirst();
        
        if (usuarioEnSistema.isPresent()) {
            String contrasenaUsuarioEnSistema = usuarioEnSistema.get().getContrasena();
        
            if (contrasenaUsuarioEnSistema.equals(contrasena)) {
                return usuarioEnSistema.get();
            }
            
            throw new DAOException("La contrasena es incorrecta");
        }
        
        throw new DAOException("No se encontro ningun usuario con el telefono dado");
    }
}
