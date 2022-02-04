package repository;

import dao.Commit;
import dao.Department;
import dao.Issue;
import dao.Login;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * Repositorio para la entidad issue. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class IssueRepository implements CRUDRepository<Issue,String> {
    private final DBController controller;

    /**
     * Constructor con dependencia de DBController
     * @param controller inyección de dependencia de DBController
     */
    public IssueRepository(DBController controller){
        this.controller = controller;
    }

    /**
     * Obtener todas las entidades issue
     * @return Lista con todos los issue
     */
    @Override
    public List<Issue> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Issue> query = manager.createNamedQuery("Issue.findAll", Issue.class);
        List<Issue> issueList = query.getResultList();
        controller.close();
        return issueList;
    }

    /**
     * Obtener entidad issue segun la id
     * @param id String id de issue
     * @return Commit
     * @throws Exception en caso de no encontrar el issue
     */
    @Override
    public Issue getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Issue issue = manager.find(Issue.class, id);
        controller.close();
        return issue;
    }

    /**
     * inserta un issue en la base de datos
     * @param issue a insertar
     * @return issue si ha insertado correctamente
     * @throws Exception si la insercion falla
     */
    @Override
    public Issue save(Issue issue) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.persist(issue);
            manager.getTransaction().commit();
            return issue;
        }catch (Exception ex) {
            throw new Exception("Error al insertar Issue "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
    }

    /**
     * actualiza un issue en la base de datos
     * @param issue a actualizar
     * @return issue si ha actualizado correctamente
     * @throws Exception si la actualizacion falla
     */
    @Override
    public Issue update(Issue issue) throws Exception {
        controller.open();
        try{
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.merge(issue);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al actualizar Issue con id" + issue.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return issue;
    }

    /**
     * elimina un issue en la base de datos
     * @param issue a eliminar
     * @return issue si ha eliminado correctamente
     * @throws Exception si la eliminacion falla
     */
    @Override
    public Issue delete(Issue issue) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            Issue found = manager.find(Issue.class, issue.getId());
            if(found !=null) manager.remove(found);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al borrar Issue con id" + issue.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return issue;
    }
}
