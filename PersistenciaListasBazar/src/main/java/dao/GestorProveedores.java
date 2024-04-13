
package dao;

import excepciones.DAOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import objetosNegocio.ProveedorDTO;
import proveedores.IGestorProveedores;

/**
 * Implementacion del subsistema de Proveedores con listas.
 * @author saul
 */
public class GestorProveedores implements IGestorProveedores {

    private final List<ProveedorDTO> proveedores;
    
    public GestorProveedores() {
        this.proveedores = new ArrayList<>();
    }
    
    @Override
    public List<ProveedorDTO> consultarTodos() throws DAOException {
        List<ProveedorDTO> proveedoresTodos = this.proveedores.stream().collect(Collectors.toList());
        
        return proveedoresTodos;
    }

    @Override
    public List<ProveedorDTO> consultarProveedoresPorNombre(String nombreProveedor) throws DAOException {
        
        if (nombreProveedor == null) {
            throw new DAOException("El nombre del proveedor dado es null");
        }
        
        List<ProveedorDTO> proveedoresTodos = this.proveedores.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombreProveedor))
                .collect(Collectors.toList());
        
        return proveedoresTodos;
    }

    @Override
    public ProveedorDTO consultarProveedor(Long idProveedor) throws DAOException {
        
        if (idProveedor == null) {
            throw new DAOException("El ID del proveedor dado es null");
        }
        
        Optional<ProveedorDTO> proveedorEnSistema = this.proveedores.stream()
                .filter(p -> p.getId().equals(idProveedor))
                .findFirst();
        
        if (!proveedorEnSistema.isPresent()) {
            return null;
        }
        
        return proveedorEnSistema.get();
    }

    @Override
    public ProveedorDTO consultarProveedorPorNumeroTelefono(String telefono) throws DAOException {
        
        if (telefono == null) {
            throw new DAOException("El telefono del proveedor dado es null");
        }
        
        Optional<ProveedorDTO> proveedorEnSistema = this.proveedores.stream()
                .filter(p -> p.getTelefono().equals(telefono))
                .findFirst();
        
        if (!proveedorEnSistema.isPresent()) {
            return null;
        }
        
        return proveedorEnSistema.get();
    }

    @Override
    public void registrarProveedor(ProveedorDTO proveedor) throws DAOException {
        
        if (proveedor == null) {
            throw new DAOException("El proveedor dado es null");
        }
        
        Optional<ProveedorDTO> proveedorEnSistema = this.proveedores.stream()
                .filter(p -> p.getId().equals(proveedor.getId()) || p.getNombre().equals(proveedor.getNombre()) || p.getEmail().equals(proveedor.getEmail()))
                .findFirst();
        
        if (proveedorEnSistema.isPresent()) {
            throw new DAOException("El proveedor ya se encuentra registrado");
        }
        
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        
        proveedor.setFechaRegistro(fechaHoraActual);
        
        boolean agregado = this.proveedores.add(proveedor);
        
        if (!agregado) {
            throw new DAOException("No se pudo registrar al proveedor debido a un error");
        }
    }

    @Override
    public void actualizarProveedor(ProveedorDTO proveedor) throws DAOException {
        
        if (proveedor == null) {
            throw new DAOException("El proveedor dado es null");
        }
        
        Optional<ProveedorDTO> proveedorEnSistema = this.proveedores.stream()
                .filter(p -> p.getId().equals(proveedor.getId()) || p.getNombre().equals(proveedor.getNombre()) || p.getEmail().equals(proveedor.getEmail()))
                .findFirst();
        
        if (!proveedorEnSistema.isPresent()) {
            throw new DAOException("El proveedor no se encuentra registrado");
        }
        
        int index = this.proveedores.indexOf(proveedorEnSistema.get());
        
        if (index < 0) {
            throw new DAOException("No se pudo modificar los datos del proveedor debido a un error");
        }
        
        this.proveedores.set(index, proveedor);
    }

    @Override
    public void eliminarProveedor(Long idProveedor) throws DAOException {
        
        if (idProveedor == null) {
            throw new DAOException("El ID del proveedor dado es null");
        }
        
        Optional<ProveedorDTO> proveedorEnSistema = this.proveedores.stream()
                .filter(p -> p.getId().equals(idProveedor))
                .findFirst();
        
        if (!proveedorEnSistema.isPresent()) {
            throw new DAOException("El proveedor a eliminar no se encuentra registrado");
        }
        
        boolean eliminado = this.proveedores.remove(proveedorEnSistema.get());
        
        if (!eliminado) {
            throw new DAOException("No se pudo eliminar al proveedor debido a un error");
        }
    }
    
}
