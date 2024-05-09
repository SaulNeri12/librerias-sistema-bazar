/*
 * CompraDTO.java
 */
package objetosDTO;

import java.io.Serializable;

/**
 * Contiene la informacion de una compra a un proveedor.
 *
 * @author rramirez
 */
public class CompraDTO implements Serializable {

    private Integer cantidad;
    private float precioProducto;
    private float montoTotal;

    private ProductoDTO producto;

    private ProveedorDTO proveedor;

    public CompraDTO() {

    }

    /**
     * Crea una instancia de una clase de compra a un proveedor.
     *
     * @param cantidad Cantidad de productos comprados.
     * @param precioProducto Precio del producto en ese momento.
     * @param producto ProductoDTO en cuestion.
     * @param proveedor ProveedorDTO al cual se le compra el producto.
     */
    public CompraDTO(Integer cantidad, float precioProducto, ProductoDTO producto, ProveedorDTO proveedor) {
        this.cantidad = cantidad;
        this.precioProducto = precioProducto;
        this.producto = producto;
        this.proveedor = proveedor;

        this.calcularMontoTotal();
    }

    private void calcularMontoTotal() {
        float total = this.cantidad * this.precioProducto;
        this.montoTotal = total;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        this.calcularMontoTotal();
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precio) {
        this.precioProducto = precio;
        this.calcularMontoTotal();
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public float getMontoTotal() {
        return this.montoTotal;
    }

    @Override
    public String toString() {
        return "Compra{" + "cantidad=" + cantidad + ", precio=" + precioProducto + ", producto=" + producto + ", proveedor=" + proveedor + '}';
    }

}
