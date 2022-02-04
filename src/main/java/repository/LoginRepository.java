package repository;


import dao.Login;
import dao.Programmer;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la entidad login. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class LoginRepository implements CRUDRepository<Login, String>{
    private final DBController controller;

    /**
     * Constructor con dependencia de DBController
     * @param controller inyección de dependencia de DBController
     */
    public LoginRepository(DBController controller) {
        this.controller = controller;
    }

    /**
     * Obtener todas las entidades login
     * @return Lista con todos los login
     */
    @Override
    public List<Login> findAll() {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Login> query = manager.createNamedQuery("Login.findAll", Login.class);
        List<Login> loginList = query.getResultList();
        controller.close();
        return loginList;
    }

    /**
     * Obtener entidad login segun la id
     * @param id String id de login
     * @return Commit
     * @throws Exception en caso de no encontrar el login
     */
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

    /**
     * inserta un login en la base de datos
     * @param login a insertar
     * @return login si ha insertado correctamente
     * @throws Exception si la insercion falla
     */
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
    
    /**
     * actualiza un login en la base de datos
     * @param login a actualizar
     * @return login si ha actualizado correctamente
     * @throws Exception si la actualizacion falla
     */
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

    /**
     * elimina un login en la base de datos
     * @param login a eliminar
     * @return login si ha eliminado correctamente
     * @throws Exception si la eliminacion falla
     */
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
