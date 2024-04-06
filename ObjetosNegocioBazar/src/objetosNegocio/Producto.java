/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/

/**
 * Contiene la informacion de un producto en el catalogo de productos.
 * @author rramirez
*/
public class Producto implements Serializable {
//public class Producto {

    /*
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    */
    private Long id;
    private String codigo;
    private String nombre;
    private float precio;
    private LocalDateTime fechaRegistro;
    private List<Proveedor> proveedores;

    public Producto() {
        proveedores = new ArrayList<>();
    }

    /**
     * Crea una instancia con la informacion de un producto en el catalogo de
     * productos.
     * @param codigo Codigo interno del producto (no codigo de barras o ID)
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param fechaRegistro Fecha de registro del producto en el sistema.
     * @param proveedores Proveedores que venden dicho producto.
     */
    public Producto(String codigo, String nombre, float precio, LocalDateTime fechaRegistro, List<Proveedor> proveedores) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
        this.proveedores = proveedores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public void agregarProveedor(Proveedor proveedor) {
        this.proveedores.add(proveedor);
    }
    
    public void quitarProveedor(Proveedor proveedor) {
        this.proveedores.removeIf(p -> Objects.equals(p.getId(), proveedor.getId()) 
                || p.getNombre().toLowerCase().equals(proveedor.getNombre().toLowerCase())
                || p.getEmail().equals(proveedor.getEmail())
        );
    }
    
    public List<Proveedor> obtenerProveedores() {
        return this.proveedores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto))
        {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", fecha_registro=" + fechaRegistro + '}';
    }

}
