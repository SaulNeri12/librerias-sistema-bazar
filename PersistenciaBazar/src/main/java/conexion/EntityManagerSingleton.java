/*
 * EntityManagerSingleton.java
 */
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Clase que regresa la instancia de EntityManager que sera utilizada para
 * conectar a la aplicación con la base de datos
 *
 * @author Juventino López García
 */
public class EntityManagerSingleton {

    private static EntityManagerSingleton instancia;
    private EntityManager em;

    private EntityManagerSingleton() {
        em = Persistence.createEntityManagerFactory("PersistenciaBazar").createEntityManager();
    }

    /**
     * Obtiene la instancia de la clase EntityManagerSingleton que contiene el
     * objeto de EntityManager
     *
     * @return Una instancia de esta clase
     */
    public static EntityManagerSingleton getInstance() {
        if (instancia == null) {
            instancia = new EntityManagerSingleton();
        }
        return instancia;
    }

    /**
     * Regresa el EntityManager a utilizar para obtener y modificar información
     * de la base de datos
     *
     * @return Una instancia de EntityManager 
     */
    public EntityManager getEntityManager() {
        return em;
    }
}
