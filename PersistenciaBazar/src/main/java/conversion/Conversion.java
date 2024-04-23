package conversion;

/**
 * Clase que permite convertir una entidad a un objetoDAO y viceversa.
 */
public class Conversion {
    Producto producto;
    ProductoDTO productoDTO;
    Proveedor proveedor;
    ProveedorDTO proveedorDTO;

    public Conversion() {
    }

    public ProductoDTO convertirProductoDTOAEntidad(Producto producto) {
        productoDTO = new ProductoDTO();
        productoDTO.setCodigoBarras(producto.getCodigoBarras());
        productoDTO.setCodigoInterno(producto.getCodigoInterno());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setFechaRegistro(producto.getFechaRegistro());
        return productoDTO;
    }

    public Producto convertirProductoEntidadADTO(ProductoDTO productoDTO) {
        producto = new Producto();
        producto.setCodigoBarras(productoDTO.getCodigoBarras());
        producto.setCodigoInterno(productoDTO.getCodigoInterno());
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setFechaRegistro(productoDTO.getFechaRegistro());
        return producto;
    }

    public ProveedorDTO convertirProveedorDTOAEntidad(Proveedor proveedor) {
        proveedorDTO = new ProveedorDTO();
        proveedorDTO.setId(proveedor.getId());
        proveedorDTO.setNombre(proveedor.getNombre());
        proveedorDTO.setTelefono(proveedor.getTelefono());
        proveedorDTO.setEmail(proveedor.getEmail());
        proveedorDTO.setDescripcion(proveedor.getDescripcion());
        proveedorDTO.setFechaRegistro(proveedor.getFechaRegistro());
        proveedorDTO.setDireccion(proveedor.getDireccion());
        return proveedorDTO;
    }

    public Proveedor convertirProveedorEntidadADTO(ProveedorDTO proveedorDTO) {
        proveedor = new Proveedor();
        proveedor.setId(proveedorDTO.getId());
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setTelefono(proveedorDTO.getTelefono());
        proveedor.setEmail(proveedorDTO.getEmail());
        proveedor.setDescripcion(proveedorDTO.getDescripcion());
        proveedor.setFechaRegistro(proveedorDTO.getFechaRegistro());
        proveedor.setDireccion(proveedorDTO.getDireccion());
        return proveedor;
    }
}
