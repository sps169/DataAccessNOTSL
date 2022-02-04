package repository;

import dao.Programmer;
import dao.Project;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repositorio para la entidad programmer. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class ProgrammerRepository implements CRUDRepository<Programmer,String> {
    private final DBController controller;

    /**
     * Constructor con dependencia de DBController
     * @param controller inyección de dependencia de DBController
     */
    public ProgrammerRepository(DBController controller){
        this.controller = controller;
    }

    /**
     * Obtener todas las entidades programmer
     * @return Lista con todos los programmer
     */
    @Override
    public List<Programmer> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Programmer> query = manager.createNamedQuery("Programmer.findAll", Programmer.class);
        List<Programmer> programmerList = query.getResultList();
        controller.close();
        return programmerList;
    }

    /**
     * Obtener entidad programmer segun la id
     * @param id String id de programmer
     * @return Commit
     * @throws Exception en caso de no encontrar el programmer
     */
    @Override
    public Programmer getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Programmer programmer = manager.find(Programmer.class, id);
        controller.close();
        return programmer;
    }

    /**
     * inserta un programmer en la base de datos
     * @param programmer a insertar
     * @return programmer si ha insertado correctamente
     * @throws Exception si la insercion falla
     */
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

    /**
     * actualiza un programmer en la base de datos
     * @param programmer a actualizar
     * @return programmer si ha actualizado correctamente
     * @throws Exception si la actualizacion falla
     */
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

    /**
     * elimina un programmer en la base de datos
     * @param programmer a eliminar
     * @return programmer si ha eliminado correctamente
     * @throws Exception si la eliminacion falla
     */
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
