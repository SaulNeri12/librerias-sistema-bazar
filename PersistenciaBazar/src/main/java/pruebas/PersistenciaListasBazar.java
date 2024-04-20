package pruebas;

import conexion.EntityManagerSingleton;
import dao.GestorProductos;
import dao.GestorProveedores;
import dao.GestorUsuarios;
import dao.GestorVentas;

import subsistemas.interfaces.*;
import subsistemas.excepciones.DAOException;


/**
 *
 * @author saul
 */
public class PersistenciaListasBazar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IGestorProductos productos = GestorProductos.getInstance();
        IGestorProveedores proveedores = GestorProveedores.getInstance();
        IGestorVentas ventas = GestorVentas.getInstance();
        IGestorUsuarios usuarios = GestorUsuarios.getInstance();

        // TODO: CUANDO SE HAGAN LAS CONVERSIONES EN LOS DAO, EMPIEZEN POR HACER
        //       BUSQUEDA, INSERCION, ACTUALIZACION Y ELMININACION CON LOS SUBSISTEMAS
        //       NECESARIOS, NO HABRAN UNA NUEVA CONEXION. PARA ESO SON LOS 
        //       DAO...
    }
}
