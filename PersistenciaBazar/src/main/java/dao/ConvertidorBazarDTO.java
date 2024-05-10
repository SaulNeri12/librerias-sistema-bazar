package dao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

        // Convertir la fecha de registro de String a LocalDateTime
        String fechaRegistroStr = productoDoc.getString("fechaRegistro");
        if (fechaRegistroStr != null) {
            LocalDateTime fechaRegistro = LocalDateTime.parse(fechaRegistroStr, DateTimeFormatter.ISO_DATE_TIME);
            productoDTO.setFechaRegistro(fechaRegistro);
        } else {
            productoDTO.setFechaRegistro(LocalDateTime.now());
        }

        return productoDTO;
    }

    public Document convertirProductoDTOADocumento(ProductoDTO producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto dado es null");
        }

        // Realizar las validaciones necesarias
        validarCamposProducto(producto);

        // Crear un documento MongoDB con los campos del producto
        Document documentoProducto = new Document()
                .append("codigoBarras", producto.getCodigoBarras())
                .append("codigoInterno", producto.getCodigoInterno())
                .append("nombre", producto.getNombre())
                .append("precio", producto.getPrecio())
                .append("fechaRegistro", producto.getFechaRegistro().toString());

        return documentoProducto;
    }

    // Método para validar los campos del producto
    private void validarCamposProducto(ProductoDTO producto) {
        if (producto.getCodigoBarras() == null || producto.getCodigoInterno() == null || producto.getNombre() == null
                || producto.getPrecio() <= 0 || producto.getFechaRegistro() == null) {
            throw new IllegalArgumentException("El producto tiene campos nulos o inválidos");
        }
    }

}
