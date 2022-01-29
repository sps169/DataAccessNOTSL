package repository;

import dao.Commit;
import dao.Project;
import dao.Repository;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositoryRepository implements CRUDRepository<Repository,String>{
    private final DBController controller;

    public RepositoryRepository(DBController controller){
        this.controller = controller;
    }

    @Override
    public List<Repository> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Repository> query = manager.createNamedQuery("Repository.findAll", Repository.class);
        List<Repository> repositoryList = query.getResultList();
        controller.close();
        return repositoryList;
    }

    @Override
    public Repository getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Repository repository = manager.find(Repository.class, id);
        controller.close();
        return repository;
    }

    @Override
    public Repository save(Repository repository) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            repository.setId(null);
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

    @Override
    public Repository delete(Repository repository) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            Commit found = manager.find(Commit.class, repository.getId());
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
