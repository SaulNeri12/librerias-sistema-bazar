
package persistencialistasbazar;

import dao.GestorProductos;
import dao.GestorProveedores;
import dao.GestorUsuarios;
import dao.GestorVentas;
import excepciones.DAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.DetalleVenta;
import objetosNegocio.Producto;
import objetosNegocio.Proveedor;
import objetosNegocio.Usuario;
import objetosNegocio.Venta;
import productos.IGestorProductos;
import proveedores.IGestorProveedores;
import usuarios.IGestorUsuarios;
import ventas.IGestorVentas;

/**
 *
 * @author saul
 */
public class PersistenciaListasBazar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IGestorProductos productos = new GestorProductos();
        IGestorProveedores proveedores = new GestorProveedores();
        IGestorVentas ventas = new GestorVentas();
        IGestorUsuarios usuarios = new GestorUsuarios();
        
        Random random = new Random();
        
        Proveedor primerProveedor = new Proveedor();
        primerProveedor.setId(random.nextLong() & Long.MAX_VALUE);
        primerProveedor.setNombre("Fruteria 'El Guero'");
        primerProveedor.setEmail("fruteriaelguero@gmail.com");
        primerProveedor.setTelefono("6655235123");
        primerProveedor.setDescripcion("Productos de variedad especialmente frutas y verduras");
        
        System.out.println(primerProveedor);
        
        try {
            proveedores.registrarProveedor(primerProveedor);
        } catch (DAOException ex) {
            System.out.println("### No se pudo registrar al proveedor con ID=" + primerProveedor.getId().toString());
        }
        
        Long proveedorId = primerProveedor.getId();
        
        try {
            Proveedor encontrado = proveedores.consultarProveedor(proveedorId);
            System.out.println("ENCONTRADO: " + encontrado);
        } catch (DAOException ex) {
            System.out.println("No se encontro al proveedor con ID: " + proveedorId.toString());
        }
            
        
        Producto primerProducto = new Producto();
        primerProducto.setId(random.nextLong() & Long.MAX_VALUE);
        primerProducto.setCodigo("AX1111");
        primerProducto.setNombre("Galletas Marias");
        primerProducto.setPrecio(17.50f);
        primerProducto.agregarProveedor(primerProveedor);

        try {
            productos.registrarProducto(primerProducto);
            System.out.println("producto registrado=" + primerProducto);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        String codigoPrimerProducto = primerProducto.getCodigo();
        Producto segundoProducto = new Producto();
        segundoProducto.setId(random.nextLong() & Long.MAX_VALUE);
        segundoProducto.setCodigo(codigoPrimerProducto);
        segundoProducto.setNombre("Chips Habaneras");
        segundoProducto.setPrecio(17.00f);
        segundoProducto.agregarProveedor(primerProveedor);
        
        try {
            productos.registrarProducto(segundoProducto);
            System.out.println("producto registrado=" + segundoProducto);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        segundoProducto.setPrecio(20.0f);
        
        try {
            productos.actualizarProducto(segundoProducto);
            System.out.println("Precio nuevo de " + segundoProducto.getNombre() + " es de " + segundoProducto.getPrecio());
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        try {
            segundoProducto = productos.consultarProducto(segundoProducto.getCodigo());
            System.out.println("PRIMER PRODUCTO EN SEGUNDO: " + segundoProducto);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        
        Long id = random.nextLong() & Long.MAX_VALUE;
        
        Producto tercerProducto = new Producto();
        tercerProducto.setId(id);
        tercerProducto.setCodigo("GL0101");
        tercerProducto.setNombre("Galletas Chookies");
        tercerProducto.setPrecio(18.00f);
        tercerProducto.agregarProveedor(primerProveedor);
        
        System.out.println(tercerProducto.getCodigo());
        System.out.println(tercerProducto.getId());
        System.out.println(segundoProducto.getCodigo());
        System.out.println(primerProducto.getCodigo());
        
        try {
            productos.registrarProducto(tercerProducto);
            //tercerProducto = productos.consultarProducto(tercerProducto.getCodigo());
            System.out.println("PRODUCTO REGISTRADO (TERCERO: " + tercerProducto);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        try {
            System.out.println(productos.consultarTodos());
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        try {
            productos.eliminarProducto(codigoPrimerProducto);
            System.out.println("ELIMINADO!!!");
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        try {
            System.out.println(productos.consultarTodos());
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        Usuario usuario = null;
        try {
            usuario = usuarios.consultarUsuarioPorNumeroTelefono("1000000000");
            System.out.println("USUARIO : " + usuario);
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Venta primeraVenta = new Venta();
        primeraVenta.setId(101001l);
        primeraVenta.setNombreCliente("Miguel");
        primeraVenta.setApellidoCliente("Perez");
        primeraVenta.setUsuario(usuario);
        primeraVenta.setMetodoPago(Venta.MetodoPago.EFECTIVO);
        
        List<DetalleVenta> productosVendidos = new ArrayList<>();
        
        DetalleVenta detalle = new DetalleVenta();
        id = random.nextLong() & Long.MAX_VALUE;
        detalle.setId(id);
        
        Producto productoObjetivo = null;
        
        try {
            productoObjetivo = productos.consultarProducto("GL0101");
            //detalle.setPrecioProducto();
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        detalle.setPrecioProducto(productoObjetivo.getPrecio());
        detalle.setProducto(productoObjetivo);
        detalle.setCantidad(2);
        
        productosVendidos.add(detalle);
        
        primeraVenta.setProductosVendidos(productosVendidos);
        
        try {
            ventas.registrarVenta(primeraVenta);
            System.out.println("Venta registrada!!! " + primeraVenta);
        } catch (DAOException ex) {
            System.out.println("### ERROR " + ex.getMessage());
        }
        
        productosVendidos.clear();
        
        detalle.setPrecioProducto(productoObjetivo.getPrecio());
        detalle.setProducto(productoObjetivo);
        detalle.setCantidad(5);
        
        productosVendidos.add(detalle);
        
        primeraVenta.setProductosVendidos(productosVendidos);
        
        try {
            ventas.actualizarVenta(primeraVenta);
            System.out.println("Se actualizo la venta");
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        try {
            Venta ventaObjetivo = ventas.consultarVenta(primeraVenta.getId());
            System.out.println("VENTA ACTUALIZADA: " + ventaObjetivo);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        
        try {
            ventas.eliminarVenta(primeraVenta.getId());
            System.out.println("SE ELIMINO LA VENTA!!!");
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        try {
            Venta ventaObjetivo = ventas.consultarVenta(primeraVenta.getId());
            
            if (ventaObjetivo == null) {
                System.out.println("SE ELIMINO CORRECTAMENTE!!!: " + ventaObjetivo);
            }
            
            //System.out.println("NO SE ELIMINO " + ventaObjetivo);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        // SUBSISTEMA VENTAS FUNCIONA...
        
        Usuario usuarioLogeado;
        
        String telefonoUsuario = "1000000000";
        String contrasenaUsuario = "admin";
        
        try {
            usuarioLogeado = usuarios.iniciarSesion(telefonoUsuario, contrasenaUsuario);   
            System.out.println("USUARIO LOGGEADO: " + usuarioLogeado);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        Usuario nuevoUsuario = new Usuario();
        id = random.nextLong() & Long.MAX_VALUE;
        nuevoUsuario.setId(id);
        nuevoUsuario.setNombre("Saul Armando Neri Escarcega");
        nuevoUsuario.setPuesto(Usuario.Puesto.CAJERO);
        nuevoUsuario.setTelefono("6441269619");
        nuevoUsuario.setContrasena("12345");
        
        try {
            usuarios.registrarUsuario(nuevoUsuario);
            
            System.out.println("USUARIO REGISTRADO: " + nuevoUsuario);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        //telefonoUsuario = "21929129219";
        telefonoUsuario = nuevoUsuario.getTelefono();
        
        try {
            Usuario usuarioObjetivo = usuarios.consultarUsuarioPorNumeroTelefono(telefonoUsuario);
            
            if (usuarioObjetivo == null) {
                System.out.println("NO SE ENCONTRO EL USUARIO CON EL TELEFONO: " + telefonoUsuario);
            } else {
                System.out.println("USUARIO REGISTRADO: " + usuarioObjetivo);
            }
            
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        nuevoUsuario.setContrasena("perrozhet");
        
        try {
            usuarios.actualizarUsuario(nuevoUsuario);
            System.out.println("CONTRASENA USUARIO ACTUALIZADA");
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
        telefonoUsuario = nuevoUsuario.getTelefono();
        contrasenaUsuario = nuevoUsuario.getContrasena();
        
        try {
            usuarioLogeado = usuarios.iniciarSesion(telefonoUsuario, contrasenaUsuario);   
            System.out.println("USUARIO 2 LOGEADO: " + usuarioLogeado);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }
        
    }   
}
