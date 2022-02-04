package repository;


import dao.Login;
import dao.Programmer;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class LoginRepository implements CRUDRepository<Login, String>{
    private final DBController controller;

    public LoginRepository(DBController controller) {
        this.controller = controller;
    }

    @Override
    public List<Login> findAll() {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Login> query = manager.createNamedQuery("Login.findAll", Login.class);
        List<Login> loginList = query.getResultList();
        controller.close();
        return loginList;
    }

    @Override
    public Login getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Login login = manager.find(Login.class, id);
        controller.close();
        if (login != null)
            return login;
        throw new Exception("No existe login con ID "+ id.toString());
    }

    @Override
    public Login save(Login login) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.persist(login);
            manager.getTransaction().commit();
            return login;
        }catch (Exception ex) {
            throw new Exception("Error al insertar login "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
    }

    public Login saveInSession(Login login) throws Exception {
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.persist(login);
            manager.getTransaction().commit();
            return login;
        }catch (Exception ex) {
            throw new Exception("Error al insertar Login "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
        }
    }

    @Override
    public Login update(Login login) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.merge(login);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al actualizar login con id" + login.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return login;
    }

    @Override
    public Login delete(Login login) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            Login found = manager.find(Login.class, login.getId());
            if (found != null) manager.remove(found);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al borrar login con id" + login.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return login;
    }

}
