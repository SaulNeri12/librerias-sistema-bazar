/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.ProductoDTO;
import objetosNegocio.VentaDTO;
import persistencia.excepciones.PersistenciaBazarException;
import persistenciaBazar.PersistenciaBazar;



/**
 *
 * @author saul
 */
public class PersistenciaBazarPrueba2 {
    
    
    public static void main(String[] args) {
        PersistenciaBazar persistencia = PersistenciaBazar.getInstance();
        
        
        pruebasProductos(persistencia);
        
        
        
        
    }
    
    public static void pruebasVentas(PersistenciaBazar persistencia) {
        /*
        try {
            List<VentaDTO> ventasTodas = persistencia.consultarVentasTodas();
            
            ventasTodas.forEach(v -> { System.out.println(v); });
            
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        VentaDTO v = new VentaDTO();
        v.setNombreCliente("Pancho");
        v.setApellidoCliente("Villa");
        v.setMetodoPago(VentaDTO.MetodoPago.EFECTIVO);
        v.setUsuario(usuario);
        */
        
    }
    
    public static void pruebasProductos(PersistenciaBazar persistencia) {
        // consultar productos
        try {
            List<ProductoDTO> productosTodos = persistencia.consultarProductosTodos();
            
            productosTodos.forEach(p -> { System.out.println(p); });
            
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }
        
        ProductoDTO p = new ProductoDTO();
        p.setCodigoBarras(100205021055220l);
        p.setCodigoInterno("SAB001");
        p.setNombre("Papas Sabritas");
        p.setPrecio(17.50f);
        
        try {
            persistencia.registrarProducto(p);
            System.out.println("Se registraron las sabritas...");
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }
        
        p.setPrecio(17.f);
        
        try {
            persistencia.actualizarProducto(p);
            System.out.println("Se actualizo el precio de las sabritas");
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }
        
        /*
        try {
            persistencia.eliminarProducto(p.getCodigoInterno());
            System.out.println("Se eliminaron las sabritas...");
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }*/
        
        
        // insertar mas datos...
        
        p.setCodigoBarras(10020521212100l);
        p.setCodigoInterno("COCA0600");
        p.setNombre("Coca Cola 600ml");
        p.setPrecio(19.f);
        
        try {
            persistencia.registrarProducto(p);
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }
        
        p.setCodigoBarras(100205128182100l);
        p.setCodigoInterno("RUFF001");
        p.setNombre("Ruffles Original");
        p.setPrecio(18.f);
        
        try {
            persistencia.registrarProducto(p);
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }
        
        p.setCodigoBarras(1022334128182100l);
        p.setCodigoInterno("RUFF007");
        p.setNombre("Ruffles Habaneras");
        p.setPrecio(18.f);
        
        try {
            persistencia.registrarProducto(p);
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
