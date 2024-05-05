/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.DetalleVentaDTO;
import objetosNegocio.DireccionDTO;
import objetosNegocio.ProductoDTO;
import objetosNegocio.UsuarioDTO;
import objetosNegocio.VentaDTO;
import persistencia.PersistenciaBazar;
import persistencia.excepciones.PersistenciaBazarException;


/**
 *
 * @author saul
 */
public class PersistenciaBazarPrueba2 {
    
    
    
    public static void main(String[] args) {
        PersistenciaBazar persistencia = PersistenciaBazar.getInstance();
        
        
        //pruebasUsuarios(persistencia);
        pruebasProductos(persistencia);
        //pruebasVentas(persistencia);
        
        
    }
    
    public static void pruebasUsuarios(PersistenciaBazar persistencia) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre("Pedro");
        usuario.setApellido("Lopez");
        usuario.setTelefono("6442269619");
        usuario.setContrasena("admin12345");
        usuario.setPuesto(UsuarioDTO.Puesto.CAJERO);
        
        DireccionDTO direccion = new DireccionDTO();
        
        direccion.setCalle("Ali");
        direccion.setCiudad("Calle X");
        direccion.setCodigoPostal("857362");
        direccion.setColonia("Para Alla");
        direccion.setNumeroEdificio("6009");
        
        usuario.setDireccion(direccion);
        
        try {
            persistencia.registrarUsuario(usuario);
            System.out.println("usuario registrado...");
        } catch (PersistenciaBazarException ex) {
            Logger.getLogger(PersistenciaBazarPrueba2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
    
    public static void pruebasVentas(PersistenciaBazar persistencia) {
        
        /*
        try {
            List<VentaDTO> ventasTodas = persistencia.consultarVentasTodas();
            
            ventasTodas.forEach(v -> { System.out.println(v); });
            
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }*/
        
        
        // NOTE: ESTE USUARIO YA LO TENIA REGISTRADO, USA OTRO EN TU LUGAR
        UsuarioDTO usuario = null;
        try {
            usuario = persistencia.consultarUsuario(1l);
        } catch (PersistenciaBazarException ex) {
            Logger.getLogger(PersistenciaBazarPrueba2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        VentaDTO v = new VentaDTO();
        v.setNombreCliente("Pancho");
        v.setApellidoCliente("Villa");
        v.setMetodoPago(VentaDTO.MetodoPago.EFECTIVO);
        v.setUsuario(usuario);
        
        List<DetalleVentaDTO> detalles = new ArrayList<>();
        
        List<ProductoDTO> productosBD = null;
        
        try {
            productosBD = persistencia.consultarProductosTodos();
        } catch (PersistenciaBazarException ex) {
            Logger.getLogger(PersistenciaBazarPrueba2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        for (int i=0; i < productosBD.size(); i++) {
                DetalleVentaDTO d = new DetalleVentaDTO();
                
                d.setProducto(productosBD.get(i));
                d.setCantidad(1);
                d.setPrecioProducto(productosBD.get(i).getPrecio());
                //d.setVenta(v);

                detalles.add(d);
                //v.getProductosVendidos().add(d);
            }
        
        System.out.println(v.getProductosVendidos());
        
        try {
            v.setProductosVendidos(detalles);
        } catch (Exception e) {
            System.out.println(e.getClass() + " " + e.getCause() + " " + e.getMessage());
        }
        
        /*
        try {
            persistencia.registrarVenta(v);
            System.out.println("Venta registrada!!!");
        } catch (PersistenciaBazarException ex) {
            Logger.getLogger(PersistenciaBazarPrueba2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }*/
        
        
        VentaDTO ventaEncontrada;
        
        try {
            ventaEncontrada = persistencia.consultarVenta(2l);
            
            System.out.println("Venta encontrada: " + ventaEncontrada);
            for (DetalleVentaDTO dt: ventaEncontrada.getProductosVendidos()) {
                System.out.println("\t" + dt);
            }
            
        } catch (PersistenciaBazarException ex) {
            Logger.getLogger(PersistenciaBazarPrueba2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        
        ventaEncontrada.getProductosVendidos().remove(0);
        ventaEncontrada.getProductosVendidos().remove(0);
        ventaEncontrada.getProductosVendidos().remove(0);
        
        
        try {
            persistencia.actualizarVenta(ventaEncontrada);
            System.out.println("se actualizo la venta...");
        } catch (PersistenciaBazarException ex) {
            Logger.getLogger(PersistenciaBazarPrueba2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        
        try {
            List<VentaDTO> ventas = persistencia.consultarVentasPorPeriodo(LocalDateTime.of(2010, Month.MARCH, 1, 10, 10), LocalDateTime.of(2021, Month.MARCH, 1, 10, 10));
        
            for (VentaDTO ve: ventas) {
                System.out.println(ve);
            }
            
        } catch (PersistenciaBazarException ex) {
            Logger.getLogger(PersistenciaBazarPrueba2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        try {
            persistencia.eliminarVenta(1l);
            System.out.println("Venta eliminada!!!");
        } catch (PersistenciaBazarException ex) {
            Logger.getLogger(PersistenciaBazarPrueba2.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
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
        
        p.setNombre("Sabritas Original 75gr");
        p.setPrecio(17.f);
        
        try {
            persistencia.actualizarProducto(p);
            System.out.println("Se actualizo el precio de las sabritas");
        } catch (PersistenciaBazarException ex) {
            System.out.println(ex.getMessage());
        }
        
        p.setCodigoBarras(1022212412341l);
        p.setCodigoInterno("CAJP1000");
        p.setNombre("Cacahuates Japoneses Chicos");
        p.setPrecio(11);
        
        try {
            persistencia.actualizarProducto(p);
            System.out.println("Se actualizo el precio de los cacahuates");
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
