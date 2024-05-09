/*
 * DetalleVentaDTO.java
 */
package objetosDTO;

/**
 * Contiene la informacion de venta de un producto, perteneciente a una venta.
 *
 * @author rramirez
 */
public class DetalleVentaDTO {

    private Integer cantidad;

    private ProductoDTO producto;

    private float precioProducto;

    public DetalleVentaDTO() {

    }

    /**
     * Crea una instancia de un detalle de venta, producto comprado.
     *
     * @param precioProducto Precio del producto en ese momento.
     * @param cantidad Cantidad de unidades vendidas.
     * @param producto ProductoDTO vendido.
     */
    public DetalleVentaDTO(float precioProducto, Integer cantidad, ProductoDTO producto) {
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public void setPrecioProducto(float precio) {
        this.precioProducto = precio;
    }

    public float getPrecioProducto() {
        return this.precioProducto;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "cantidad=" + cantidad + ", producto=" + producto + '}';
    }

}
