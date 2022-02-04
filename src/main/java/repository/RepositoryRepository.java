package repository;

import dao.Commit;
import dao.Project;
import dao.Repository;
import manager.DBController;

import dao.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la entidad repository. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class RepositoryRepository implements CRUDRepository<Repository,String>{
    private final DBController controller;

    /**
     * Constructor con dependencia de DBController
     * @param controller inyección de dependencia de DBController
     */
    public RepositoryRepository(DBController controller){
        this.controller = controller;
    }

    /**
     * Obtener todas las entidades repository
     * @return Lista con todos los repository
     */
    @Override
    public List<Repository> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Repository> query = manager.createNamedQuery("Repository.findAll", Repository.class);
        List<Repository> repositoryList = query.getResultList();
        controller.close();
        return repositoryList;
    }

    /**
     * Obtener entidad repository segun la id
     * @param id String id de repository
     * @return Commit
     * @throws Exception en caso de no encontrar el repository
     */
    @Override
    public Repository getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Repository repository = manager.find(Repository.class, id);
        controller.close();
        return repository;
    }

    /**
     * inserta un repository en la base de datos
     * @param repository a insertar
     * @return repository si ha insertado correctamente
     * @throws Exception si la insercion falla
     */
    @Override
    public Repository save(Repository repository) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.persist(repository);
            manager.getTransaction().commit();
            return repository;
        }catch (Exception ex) {
            throw new Exception("Error al insertar Repository "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
    }

    /**
     * actualiza un repository en la base de datos
     * @param repository a actualizar
     * @return repository si ha actualizado correctamente
     * @throws Exception si la actualizacion falla
     */
    @Override
    public Repository update(Repository repository) throws Exception {
        controller.open();
        try{
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.merge(repository);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al actualizar Repository con id" + repository.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return repository;
    }

    /**
     * elimina un repository en la base de datos
     * @param repository a eliminar
     * @return repository si ha eliminado correctamente
     * @throws Exception si la eliminacion falla
     */
    @Override
    public Repository delete(Repository repository) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            Repository found = manager.find(Repository.class, repository.getId());
            if(found !=null) manager.remove(found);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al borrar Repository con id" + repository.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return repository;
    }
}
