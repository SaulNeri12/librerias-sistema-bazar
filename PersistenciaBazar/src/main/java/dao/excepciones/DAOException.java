package dao.excepciones;

/**
 * Excepcion que ocurre en el acceso a datos en los subsistemas.
 * 
 * Extiende de Exception para permitir la propagacion de la excepcion y
 * proporciona detalles sobre la causa de la excepcion.
 * 
 * @author saul
 */
public class DAOException extends Exception {
    public DAOException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    public DAOException(String mensaje) {
        super(mensaje);
    }
}
