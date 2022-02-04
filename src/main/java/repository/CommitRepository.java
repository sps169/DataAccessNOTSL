package repository;

import dao.Commit;
import dao.Department;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repositorio para la entidad commit. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class CommitRepository implements CRUDRepository<Commit, String>{
    private final DBController controller;

    /**
     * Constructor con dependencia de DBController
     * @param controller inyección de dependencia de DBController
     */
    public CommitRepository(DBController controller){
        this.controller = controller;
    }

    /**
     * Obtener todas las entidades commit
     * @return Lista con todos los commit
     */
    @Override
    public List<Commit> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Commit> query = manager.createNamedQuery("Commit.findAll", Commit.class);
        List<Commit> commitList = query.getResultList();
        controller.close();
        return commitList;
    }

    /**
     * Obtener entidad commit segun la id
     * @param id String id de commit
     * @return Commit
     * @throws Exception en caso de no encontrar el commit
     */
    @Override
    public Commit getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Commit commit = manager.find(Commit.class, id);
        controller.close();
        return commit;
    }

    /**
     * inserta un commit en la base de datos
     * @param commit a insertar
     * @return commit si ha insertado correctamente
     * @throws Exception si la insercion falla
     */
    @Override
    public Commit save(Commit commit) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.persist(commit);
            manager.getTransaction().commit();
            return commit;
        }catch (Exception ex) {
            throw new Exception("Error al insertar Commit "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
    }

    /**
     * actualiza un commit en la base de datos
     * @param commit a actualizar
     * @return commit si ha actualizado correctamente
     * @throws Exception si la actualizacion falla
     */
    @Override
    public Commit update(Commit commit) throws Exception {
        controller.open();
        try{
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.merge(commit);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al actualizar Commit con id" + commit.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return commit;
    }

    /**
     * elimina un commit en la base de datos
     * @param commit a eliminar
     * @return commit si ha eliminado correctamente
     * @throws Exception si la eliminacion falla
     */
    @Override
    public Commit delete(Commit commit) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            Commit found = manager.find(Commit.class, commit.getId());
            if(found !=null) manager.remove(found);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al borrar login con id" + commit.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return commit;
    }
}
