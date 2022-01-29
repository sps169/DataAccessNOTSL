package repository;


import dao.Login;
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
        System.out.println("Executing find all logins:");
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
    public Login save(Login o) throws Exception {
        System.out.println("Executing save login:" + o);
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            o.setId(null);
            manager.persist(o);
            manager.getTransaction().commit();
            return o;
        }catch (Exception ex) {
            throw new Exception("Error al insertar login "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
    }

    @Override
    public Login update(Login o) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.merge(o);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al actualizar login con id" + o.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return o;
    }

    @Override
    public Login delete(Login o) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            Login login = manager.find(Login.class, o.getId());
            if (login != null)
                manager.remove(login);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al borrar login con id" + o.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return o;
    }

}
