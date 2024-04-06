/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package persistencialistasbazar;

import dao.GestorProductos;
import dao.GestorProveedores;
import excepciones.DAOException;
import java.util.Random;
import objetosNegocio.Producto;
import objetosNegocio.Proveedor;
import productos.IGestorProductos;
import proveedores.IGestorProveedores;

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
    }   
}
