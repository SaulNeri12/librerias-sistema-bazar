
package dao;

import excepciones.DAOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import objetosNegocio.DetalleVenta;
import objetosNegocio.Venta;
import ventas.IGestorVentas;

/**
 * Implementacion del subsistema de ventas con listas.
 * @author saul
 */
public class GestorVentas implements IGestorVentas {
    private final List<Venta> ventas;
    
    public GestorVentas() {
        this.ventas = new ArrayList<>();
    }
    
    @Override
    public Venta consultarVenta(Long idVenta) throws DAOException {
        if (idVenta == null) {
            throw new DAOException("El ID de la venta dado es null");
        }
        
         Optional<Venta> venta = this.ventas.stream()
                .filter(v -> v.getId().equals(idVenta))
                .findFirst();
        
        if (venta.isPresent()) {
            return venta.get();
        }
        
        return null;
    }

    @Override
    public List<Venta> consultarVentasDeUsuario(Long idUsuario) throws DAOException {
        if (idUsuario == null) {
            throw new DAOException("El ID del usuario dado es null");
        }
        
         List<Venta> ventas = this.ventas.stream()
                .filter(v -> v.getUsuario().getId().equals(idUsuario))
                 .collect(Collectors.toList());
        
        if (!ventas.isEmpty()) {
            return ventas;
        }
        
        return null;
    }

    @Override
    public List<Venta> consultarVentasPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws DAOException {
        if (fechaInicio == null) {
            throw new DAOException("La fecha de inicio dada es null");
        }
        
        if (fechaFin == null) {
            throw new DAOException("La fecha fin dada es null");
        }
        
        // NOTE: Solo funciona para fechas de un dia a otro, no para horas...
        // TODO: Se tendra que corregir despues...
        Predicate<Venta> ventaEntrePeriodo = v ->  v.getFechaVenta().toLocalDate().isAfter(fechaInicio) && v.getFechaVenta().toLocalDate().isBefore(fechaFin);
        
         List<Venta> ventas = this.ventas.stream()
                .filter(ventaEntrePeriodo)
                 .collect(Collectors.toList());
        
        if (!ventas.isEmpty()) {
            return ventas;
        }
        
        return null;
    }

    @Override
    public List<Venta> consultarTodos() throws DAOException {
        
        List<Venta> ventas = this.ventas.stream().collect(Collectors.toList());

        return ventas;
    }

    @Override
    public void registrarVenta(Venta venta) throws DAOException {
        if (venta == null) {
            throw new DAOException("La venta dada es null");
        }
        
        Optional<Venta> ventaEnSistema = this.ventas.stream().filter(v -> v.getId().equals(venta.getId())).findFirst();
        
        if (!ventaEnSistema.isPresent()) {
            
            Float totalVenta = 0.0f;
            
            for (DetalleVenta productoVendido: venta.getProductosVendidos()) {
                totalVenta += productoVendido.getPrecioProducto() * productoVendido.getCantidad();
            }
            
            venta.setMontoTotal(totalVenta);
            
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            
            venta.setFechaVenta(fechaHoraActual);
            
            boolean agregado = this.ventas.add(venta);
            
            if (!agregado) {
                throw new DAOException("No se pudo registrar la venta debido a un error");
            }
        } else {
            throw new DAOException("Ya existe una venta con el ID dado");
        }
    }

    @Override
    public void actualizarVenta(Venta venta) throws DAOException {
        if (venta == null) {
            throw new DAOException("La venta dada es null");
        }
        
        Optional<Venta> ventaEnSistema = this.ventas.stream().filter(v -> v.equals(venta)).findFirst();
        
        if (!ventaEnSistema.isPresent()) {
            throw new DAOException("No se encontro la venta especificada");
        }
        
        Float totalVenta = 0.0f;

        if (venta.getProductosVendidos().isEmpty() || venta.getProductosVendidos() == null) {
            throw new DAOException("Ningun producto presente en la venta");
        }
        
        for (DetalleVenta productoVendido: venta.getProductosVendidos()) {
            totalVenta += productoVendido.getPrecioProducto() * productoVendido.getCantidad();
        }

        venta.setMontoTotal(totalVenta);

        LocalDateTime fechaHoraActual = LocalDateTime.now();
        venta.setFechaVenta(ventaEnSistema.get().getFechaVenta());
        
        int index = this.ventas.indexOf(ventaEnSistema.get());

        if (index < 0) {
            throw new DAOException("No se pudo modificar los datos de la venta debido a un error");
        }

        this.ventas.set(index, venta);
    }

    @Override
    public void eliminarVenta(Long idVenta) throws DAOException {
        if (idVenta == null) {
            throw new DAOException("El ID de la venta dado es null");
        }
        
        Optional<Venta> ventaEnSistema = this.ventas.stream().filter(v -> v.getId().equals(idVenta)).findFirst();
        
        if (!ventaEnSistema.isPresent()) {
            throw new DAOException("No se encontro la venta especificada");
        }

        boolean eliminado = this.ventas.remove(ventaEnSistema.get());
        
        if (!eliminado) {
            throw new DAOException("No se pudo eliminar la venta debido a un error");
        }
    }
}
