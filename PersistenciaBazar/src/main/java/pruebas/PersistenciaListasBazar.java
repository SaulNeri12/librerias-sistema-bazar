package pruebas;

import conexion.EntityManagerSingleton;
import dao.GestorProductos;
import dao.GestorProveedores;
import dao.GestorUsuarios;
import dao.GestorVentas;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.DireccionDTO;
import objetosNegocio.ProductoDTO;
import objetosNegocio.ProveedorDTO;

import subsistemas.interfaces.*;
import subsistemas.excepciones.DAOException;

/**
 *
 * @author saul
 */
public class PersistenciaListasBazar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        GestorProveedores gestorProveedores = GestorProveedores.getInstance();

        // Prueba insertarProveedor
        pruebaInsertarProveedor(gestorProveedores);
        // Prueba consultar
        pruebaConsultarTodos(gestorProveedores);
        pruebaConsultarProveedorPorId(gestorProveedores);
        pruebaConsultarProveedorPorTelefono(gestorProveedores);
        pruebaConsultarProveedoresPorNombre(gestorProveedores);
        // Pruebas para actualizarProveedor
        pruebaActualizarProveedor(gestorProveedores);

        // Pruebas para eliminarProveedor
        pruebaEliminarProveedor(gestorProveedores);

        System.out.println("------------------------------------------");
        List<ProveedorDTO> proveedores;
        try {
            proveedores = gestorProveedores.consultarTodos();
            for (ProveedorDTO p : proveedores) {
                System.out.println(p.toString());
            }
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            gestorProveedores.eliminarProveedor(2l);
            System.out.println("Proveedor 2 eliminado!!!");
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            ProveedorDTO p = gestorProveedores.consultarProveedor(2l);
            if (p != null) {
                System.out.println("Encontrado!!!");
            } else {
                System.out.println("encontrado: " + p);
            }
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        */
        
        IGestorProductos gestorProductos = GestorProductos.getInstance();
        
        ProductoDTO p = new ProductoDTO();
        p.setCodigoBarras(1939194019411942l);
        p.setCodigoInterno("CAM1010");
        p.setNombre("Camiseta Negra");
        p.setPrecio(89.50f);
        
        try {
            gestorProductos.registrarProducto(p);
            System.out.println("Registrado con exito!!!");
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            p = gestorProductos.consultarProducto("CAM1010");
            System.out.println("Encontrado Por codigo interno: " + p);
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            List<ProductoDTO> ps = gestorProductos.consultarProductosPorNombre("Camiseta Negra");
            
            if (ps == null) {
                System.out.println("no hay resultadoa para camisa negra");
            } else {
                System.out.println("Encontrados: ");
                for (ProductoDTO pr: ps) {
                    System.out.println(pr);
                }
                System.out.println("---------------------------");
            }
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        p.setPrecio(119.99f);
        
        try {
            gestorProductos.actualizarProducto(p);
            System.out.println("ACTUALIZADO!!!");
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            gestorProductos.eliminarProducto(p.getCodigoInterno());
            System.out.println("ELIMINAU!!!");
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void pruebaInsertarProveedor(GestorProveedores gestorProveedores) {
        try {
          
            // Crear un nuevo proveedor
            ProveedorDTO proveedor = new ProveedorDTO();
            proveedor.setNombre("Proveedor de prueba");
            proveedor.setTelefono("1234567890");
            proveedor.setEmail("proveedor@ejemplo.com");
            proveedor.setDescripcion("Proveedor de prueba para pruebas unitarias");
            proveedor.setFechaRegistro(LocalDateTime.now());

            // Crear una nueva dirección
            DireccionDTO direccion = new DireccionDTO();
            direccion.setCiudad("Ciudad de prueba");
            direccion.setNumeroEdificio("123");
            direccion.setCalle("Calle de prueba");
            direccion.setColonia("Colonia de prueba");
            direccion.setCodigoPostal("12345");

            // Asignar la dirección al proveedor
            proveedor.setDireccion(direccion);

            // Insertar el proveedor
            gestorProveedores.registrarProveedor(proveedor);
            System.out.println("Proveedor insertado correctamente.");
            
            
                    
            
        } catch (DAOException e) {
            System.out.println("Error al insertar el proveedor: " + e.getMessage());
        }
    }

    public static void pruebaConsultarTodos(GestorProveedores gestorProveedores) {
        try {
            // Consultar todos los proveedores
            List<ProveedorDTO> proveedores = gestorProveedores.consultarTodos();

            // Mostrar los proveedores consultados
            System.out.println("Proveedores registrados en el sistema:");
            for (ProveedorDTO proveedor : proveedores) {
                System.out.println("ID: " + proveedor.getId() + ", Nombre: " + proveedor.getNombre());
            }
        } catch (DAOException e) {
            System.out.println("Error al consultar todos los proveedores: " + e.getMessage());
        }
    }

    public static void pruebaConsultarProveedorPorId(GestorProveedores gestorProveedores) {
        try {
            Long idProveedor = 3L;
            ProveedorDTO proveedor = gestorProveedores.consultarProveedor(idProveedor);

            if (proveedor != null) {
                System.out.println("Proveedor encontrado con ID '" + idProveedor + "':");
                System.out.println("ID: " + proveedor.getId() + ", Nombre: " + proveedor.getNombre());
            } else {
                System.out.println("No se encontró ningún proveedor con el ID '" + idProveedor + "'");
            }
        } catch (DAOException e) {
            System.out.println("Error al consultar proveedor por ID: " + e.getMessage());
        }
    }

    public static void pruebaConsultarProveedorPorTelefono(GestorProveedores gestorProveedores) {
        try {
            String telefonoProveedor = "987654321";
            ProveedorDTO proveedor = gestorProveedores.consultarProveedorPorNumeroTelefono(telefonoProveedor);

            if (proveedor != null) {
                System.out.println("Proveedor encontrado con teléfono '" + telefonoProveedor + "':");
                System.out.println("ID: " + proveedor.getId() + ", Nombre: " + proveedor.getNombre() + ", Teléfono: " + proveedor.getTelefono());
            } else {
                System.out.println("No se encontró ningún proveedor con el teléfono '" + telefonoProveedor + "'");
            }
        } catch (DAOException e) {
            System.out.println("Error al consultar proveedor por teléfono: " + e.getMessage());
        }
    }

    public static void pruebaConsultarProveedoresPorNombre(GestorProveedores gestorProveedores) {
        try {
            String nombreProveedor = "Proveedor de prueba";
            List<ProveedorDTO> proveedores = gestorProveedores.consultarProveedoresPorNombre(nombreProveedor);

            System.out.println("Proveedores cuyo nombre contiene '" + nombreProveedor + "':");
            for (ProveedorDTO proveedor : proveedores) {
                System.out.println("ID: " + proveedor.getId() + ", Nombre: " + proveedor.getNombre());
            }
        } catch (DAOException e) {
            System.out.println("Error al consultar proveedores por nombre: " + e.getMessage());
        }
    }

    public static void pruebaActualizarProveedor(GestorProveedores gestorProveedores) {
        try {
            ProveedorDTO proveedorDTO = gestorProveedores.consultarProveedorPorNumeroTelefono("123456789");
            if (proveedorDTO != null) {
                // Modificamos algunos datos del proveedor
                proveedorDTO.setNombre("Nuevo Nombre");
                proveedorDTO.setEmail("nuevo_email@example.com");

                // Actualizamos el proveedor en la base de datos
                gestorProveedores.actualizarProveedor(proveedorDTO);

                System.out.println("Proveedor actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún proveedor para actualizar.");
            }
        } catch (DAOException e) {
            System.out.println("Error al actualizar el proveedor: " + e.getMessage());
        }
    }

    public static void pruebaEliminarProveedor(GestorProveedores gestorProveedores) {
        try {
            // Supongamos que queremos eliminar un proveedor con ID 1
            Long idProveedor = 7L;

            // Intentamos eliminar el proveedor
            gestorProveedores.eliminarProveedor(idProveedor);

            System.out.println("Proveedor eliminado correctamente.");
        } catch (DAOException e) {
            System.out.println("Error al eliminar el proveedor: " + e.getMessage());
        }
    }
}
