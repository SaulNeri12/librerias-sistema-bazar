package entidades.convertidor;

import entidades.DetalleVenta;
import entidades.Direccion;
import entidades.InventarioProducto;
import entidades.Producto;
import entidades.Proveedor;
import entidades.Usuario;
import entidades.Venta;
import java.util.List;
import java.util.stream.Collectors;
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

    public static Direccion convertirDireccionDTO(DireccionDTO direccion) {
        Direccion d = new Direccion();
        d.setCalle(direccion.getCalle());
        d.setCiudad(direccion.getCiudad());
        d.setCodigoPostal(direccion.getCodigoPostal());
        d.setColonia(direccion.getColonia());
        d.setNumeroEdificio(direccion.getNumeroEdificio());
        return d;
    }

    public static Usuario convertirUsuarioDTO(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setId(usuarioDTO.getId());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setContrasenha(usuarioDTO.getContrasena());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setPuesto(Usuario.Puesto.valueOf(usuarioDTO.getPuesto().name()));
        usuario.setDireccion(ConvertidorBazarDTO.convertirDireccionDTO(usuarioDTO.getDireccion()));
        usuario.setFechaContratacion(usuarioDTO.getFechaContratacion());

        return usuario;
    }

    public static Producto convertirProductoDTO(ProductoDTO productoDTO) {
        Producto p = new Producto();

        p.setCodigoBarras(productoDTO.getCodigoBarras());
        p.setCodigoInterno(productoDTO.getCodigoInterno());
        p.setNombre(productoDTO.getNombre());
        p.setPrecio(productoDTO.getPrecio());
        p.setFechaRegistro(productoDTO.getFechaRegistro());

        return p;
    }

    public static Proveedor convertirProveedorDTO(ProveedorDTO proveedorDTO) {
        Proveedor p = new Proveedor();

        p.setId(proveedorDTO.getId());
        p.setNombre(proveedorDTO.getNombre());
        p.setEmail(proveedorDTO.getEmail());
        p.setDescripcion(proveedorDTO.getDescripcion());
        p.setFechaRegistro(proveedorDTO.getFechaRegistro());
        p.setTelefono(proveedorDTO.getTelefono());
        p.setDireccion(ConvertidorBazarDTO.convertirDireccionDTO(proveedorDTO.getDireccion()));

        return p;
    }

    public static InventarioProducto convertirInventarioProductoDTO(InventarioProductoDTO productoDTO) {
        InventarioProducto i = new InventarioProducto();

        i.setCantidad(productoDTO.getCantidad());
        i.setProducto(ConvertidorBazarDTO.convertirProductoDTO(productoDTO.getProducto()));

        return i;
    }

    public static DetalleVenta convertirDetalleVentaDTO(DetalleVentaDTO detalleDTO) {
        DetalleVenta d = new DetalleVenta();

        d.setCantidad(detalleDTO.getCantidad());
        d.setPrecioProducto(detalleDTO.getPrecioProducto());
        d.setProducto(ConvertidorBazarDTO.convertirProductoDTO(detalleDTO.getProducto()));
        d.setVenta(ConvertidorBazarDTO.convertirVentaDTO(detalleDTO.getVenta()));

        return d;
    }

    public static Venta convertirVentaDTO(VentaDTO ventaDTO) {
        Venta v = new Venta();

        v.setId(ventaDTO.getId());
        v.setNombreCliente(ventaDTO.getNombreCliente());
        v.setApellidoCliente(ventaDTO.getApellidoCliente());
        v.setUsuario(ConvertidorBazarDTO.convertirUsuarioDTO(ventaDTO.getUsuario()));
        v.setMetodoPago(Venta.MetodoPago.valueOf(ventaDTO.getMetodoPago().name()));
        v.setFechaVenta(ventaDTO.getFechaVenta());

        List<DetalleVenta> productosVendidos = ventaDTO.getProductosVendidos()
                .stream()
                .map(p -> ConvertidorBazarDTO.convertirDetalleVentaDTO(p)).collect(Collectors.toList());

        v.setDetalleVentas(productosVendidos);

        return v;
    }

    public ProductoDTO convertirProductoAProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setCodigoBarras(producto.getCodigoBarras());
        productoDTO.setCodigoInterno(producto.getCodigoInterno());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setFechaRegistro(producto.getFechaRegistro());
        return productoDTO;
    }

    public static DireccionDTO convertirDireccionADireccionDTO(Direccion direccion) {
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setCiudad(direccion.getCiudad());
        direccionDTO.setNumeroEdificio(direccion.getNumeroEdificio());
        direccionDTO.setCalle(direccion.getCalle());
        direccionDTO.setColonia(direccion.getColonia());
        direccionDTO.setCodigoPostal(direccion.getCodigoPostal());
        return direccionDTO;
    }

    public static ProveedorDTO convertirProveedorAProveedorDTO(Proveedor proveedor) {
        ProveedorDTO proveedorDTO = new ProveedorDTO();
        proveedorDTO.setId(proveedor.getId());
        proveedorDTO.setNombre(proveedor.getNombre());
        proveedorDTO.setEmail(proveedor.getEmail());
        proveedorDTO.setDescripcion(proveedor.getDescripcion());
        proveedorDTO.setFechaRegistro(proveedor.getFechaRegistro());
        proveedorDTO.setTelefono(proveedor.getTelefono());
        proveedorDTO.setDireccion(ConvertidorBazarDTO.convertirDireccionADireccionDTO(proveedor.getDireccion()));
        return proveedorDTO;
    }
}
