package repository;

import dao.Commit;
import dao.Department;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CommitRepository implements CRUDRepository<Commit, String>{
    private final DBController controller;

    public CommitRepository(DBController controller){
        this.controller = controller;
    }

    @Override
    public List<Commit> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Commit> query = manager.createNamedQuery("Commit.findAll", Commit.class);
        List<Commit> commitList = query.getResultList();
        controller.close();
        return commitList;
    }

    @Override
    public Commit getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Commit commit = manager.find(Commit.class, id);
        controller.close();
        return commit;
    }

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

    public Commit saveInSession(Commit commit) throws Exception {
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
        }
    }

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
