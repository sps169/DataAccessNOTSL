package manager;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Controlador de Entidades de Hibernate JPA
 * Implementa la interfaz DBController
 */
@Getter
public class HibernateController implements DBController {
    private static DBController controller;

    // Creamos las EntityManagerFactory para manejar las entidades y transacciones
    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    private HibernateController() {
    }

    /**
     * obtener instancia singleton
     * @return instancia de DBController
     */
    public static DBController getInstance() {
        if (controller == null)
            controller = new HibernateController();
        return controller;
    }

    public void open() {
        if(entityManagerFactory == null || !entityManagerFactory.isOpen())
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
        if(manager == null || !manager.isOpen())
            manager = entityManagerFactory.createEntityManager();

        transaction = manager.getTransaction();
    }

    public void close() {
        manager.close();
        entityManagerFactory.close();
    }
}
