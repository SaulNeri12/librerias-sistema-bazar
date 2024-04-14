package pruebas;

import conexion.EntityManagerSingleton;
import dao.GestorProductos;
import dao.GestorProveedores;
import dao.GestorUsuarios;
import dao.GestorVentas;
import entidades.Compra;
import entidades.DetalleCompra;
import entidades.Direccion;
import entidades.Producto;
import entidades.Proveedor;
import entidades.Usuario;
import excepciones.DAOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import objetosNegocio.DetalleVentaDTO;
import objetosNegocio.ProductoDTO;
import objetosNegocio.ProveedorDTO;
import objetosNegocio.UsuarioDTO;
import objetosNegocio.VentaDTO;
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
        // Prueba de insercion a algunas tablas
        EntityManagerSingleton ems = EntityManagerSingleton.getInstance();
        EntityManager em = ems.getEntityManager();
        em.getTransaction().begin();
        Usuario user1 = new Usuario();

        user1.setNombres("John");
        user1.setApellidoPaterno("Doe");
        user1.setApellidoMaterno("Smith");
        user1.setContrasenha("password123");
        user1.setPuesto(Usuario.Puesto.CAJERO);
        user1.setTelefono("555-555-5555");
        user1.setFechaContratacion(new Date());

        Direccion direccion1 = new Direccion();
        direccion1.setCalle("123 Main St");
        direccion1.setCiudad("Anytown");
        direccion1.setCodigoPostal("12345");
        user1.setDireccion(direccion1);

        em.persist(user1);

        // Inicializar un objeto LocalDateTime
        LocalDateTime fecha = LocalDateTime.now();
        Producto product1 = new Producto();
        product1.setCodigoInterno("PROD123");
        product1.setNombre("Product 1");
        product1.setPrecio(19.99f);
        product1.setFechaRegistro(fecha);

        em.persist(product1);

        Compra compra1 = new Compra();
        compra1.setMontoTotal(100.0f);

        Proveedor proveedor1 = new Proveedor();
        proveedor1.setNombre("Acme Corp");
        compra1.setProveedor(proveedor1);
        em.persist(proveedor1);
        em.persist(compra1);

        DetalleCompra detalle1 = new DetalleCompra();
        detalle1.setCantidad(5);
        detalle1.setCompra(compra1);
        detalle1.setProducto(product1);

        em.persist(detalle1);

        em.getTransaction().commit();

        //Fin de la prueba
        
        IGestorProductos productos = new GestorProductos();
        IGestorProveedores proveedores = new GestorProveedores();
        IGestorVentas ventas = new GestorVentas();
        IGestorUsuarios usuarios = new GestorUsuarios();

        Random random = new Random();

        ProveedorDTO primerProveedor = new ProveedorDTO();
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
            ProveedorDTO encontrado = proveedores.consultarProveedor(proveedorId);
            System.out.println("ENCONTRADO: " + encontrado);
        } catch (DAOException ex) {
            System.out.println("No se encontro al proveedor con ID: " + proveedorId.toString());
        }

        ProductoDTO primerProducto = new ProductoDTO();
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
        ProductoDTO segundoProducto = new ProductoDTO();
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

        ProductoDTO tercerProducto = new ProductoDTO();
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

        UsuarioDTO usuario = null;
        try {
            usuario = usuarios.consultarUsuarioPorNumeroTelefono("1000000000");
            System.out.println("USUARIO : " + usuario);
        } catch (DAOException ex) {
            Logger.getLogger(PersistenciaListasBazar.class.getName()).log(Level.SEVERE, null, ex);
        }

        VentaDTO primeraVenta = new VentaDTO();
        primeraVenta.setId(101001l);
        primeraVenta.setNombreCliente("Miguel");
        primeraVenta.setApellidoCliente("Perez");
        primeraVenta.setUsuario(usuario);
        primeraVenta.setMetodoPago(VentaDTO.MetodoPago.EFECTIVO);

        List<DetalleVentaDTO> productosVendidos = new ArrayList<>();

        DetalleVentaDTO detalle = new DetalleVentaDTO();

        ProductoDTO productoObjetivo = null;

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
            VentaDTO ventaObjetivo = ventas.consultarVenta(primeraVenta.getId());
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
            VentaDTO ventaObjetivo = ventas.consultarVenta(primeraVenta.getId());

            if (ventaObjetivo == null) {
                System.out.println("SE ELIMINO CORRECTAMENTE!!!: " + ventaObjetivo);
            }

            //System.out.println("NO SE ELIMINO " + ventaObjetivo);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }

        // SUBSISTEMA VENTAS FUNCIONA...
        UsuarioDTO usuarioLogeado;

        String telefonoUsuario = "1000000000";
        String contrasenaUsuario = "admin";

        try {
            usuarioLogeado = usuarios.iniciarSesion(telefonoUsuario, contrasenaUsuario);
            System.out.println("USUARIO LOGGEADO: " + usuarioLogeado);
        } catch (DAOException ex) {
            System.out.println("### ERROR: " + ex.getMessage());
        }

        UsuarioDTO nuevoUsuario = new UsuarioDTO();
        id = random.nextLong() & Long.MAX_VALUE;
        nuevoUsuario.setId(id);
        nuevoUsuario.setNombre("Saul Armando Neri Escarcega");
        nuevoUsuario.setPuesto(UsuarioDTO.Puesto.CAJERO);
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
            UsuarioDTO usuarioObjetivo = usuarios.consultarUsuarioPorNumeroTelefono(telefonoUsuario);

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
