package repository;

import dao.Programmer;
import dao.Project;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProgrammerRepository implements CRUDRepository<Programmer,String> {
    private final DBController controller;

    public ProgrammerRepository(DBController controller){
        this.controller = controller;
    }

    @Override
    public List<Programmer> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Programmer> query = manager.createNamedQuery("Programmer.findAll", Programmer.class);
        List<Programmer> programmerList = query.getResultList();
        controller.close();
        return programmerList;
    }

    @Override
    public Programmer getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Programmer programmer = manager.find(Programmer.class, id);
        controller.close();
        return programmer;
    }

    @Override
    public Programmer save(Programmer programmer) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.persist(programmer);
            manager.getTransaction().commit();
            return programmer;
        }catch (Exception ex) {
            throw new Exception("Error al insertar Programmer "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
    }

    public Programmer saveInSession(Programmer programmer) throws Exception {
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.persist(programmer);
            manager.getTransaction().commit();
            return programmer;
        }catch (Exception ex) {
            throw new Exception("Error al insertar Programmer "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
        }
    }

    @Override
    public Programmer update(Programmer programmer) throws Exception {
        controller.open();
        try{
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.merge(programmer);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al actualizar Programmer con id" + programmer.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return programmer;
    }

    @Override
    public Programmer delete(Programmer programmer) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            Programmer found = manager.find(Programmer.class, programmer.getId());
            if(found !=null) manager.remove(found);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al borrar Programmer con id" + programmer.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return programmer;
    }
}
