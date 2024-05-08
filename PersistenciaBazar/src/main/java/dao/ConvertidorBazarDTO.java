package dao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import objetosNegocio.DetalleVentaDTO;
import objetosNegocio.DireccionDTO;
import objetosNegocio.InventarioProductoDTO;
import objetosNegocio.ProductoDTO;
import objetosNegocio.ProveedorDTO;
import objetosNegocio.UsuarioDTO;
import objetosNegocio.VentaDTO;

/**
 * Convierte objetos negocio DTO a objetos negocio Entidad
 *
 * @author saul
 */
public class ConvertidorBazarDTO {

     public ProductoDTO convertirDocumentoAProductoDTO(Document productoDoc) {
        ProductoDTO productoDTO = new ProductoDTO();

        // Obtener los campos del documento y asignarlos al DTO
        productoDTO.setCodigoBarras(productoDoc.getLong("codigoBarras"));
        productoDTO.setCodigoInterno(productoDoc.getString("codigoInterno"));
        productoDTO.setNombre(productoDoc.getString("nombre"));
        productoDTO.setPrecio(productoDoc.getDouble("precio"));
        
        // Convertir la fecha de registro de Date a LocalDateTime
        Date fechaRegistroDate = productoDoc.getDate("fechaRegistro");
        LocalDateTime fechaRegistro = fechaRegistroDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        productoDTO.setFechaRegistro(fechaRegistro);

        return productoDTO;
    }
    
}
