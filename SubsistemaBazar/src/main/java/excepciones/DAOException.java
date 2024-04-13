package excepciones;

/**
 * Excepcion que ocurre en el acceso a datos en los subsistemas.
 * @author saul
 */
public class DAOException extends Exception {
    public DAOException(String mensaje) {
        super(mensaje);
    }
}
