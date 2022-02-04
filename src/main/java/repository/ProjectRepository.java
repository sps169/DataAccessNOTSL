package repository;

import dao.Commit;
import dao.Programmer;
import dao.Project;
import dao.Repository;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repositorio para la entidad project. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class ProjectRepository implements CRUDRepository<Project,String> {
    private final DBController controller;

    /**
     * Constructor con dependencia de DBController
     * @param controller inyección de dependencia de DBController
     */
    public ProjectRepository(DBController controller){
        this.controller = controller;
    }

    /**
     * Obtener todas las entidades project
     * @return Lista con todos los project
     */
    @Override
    public List<Project> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Project> query = manager.createNamedQuery("Project.findAll", Project.class);
        List<Project> projectList = query.getResultList();
        controller.close();
        return projectList;
    }

    /**
     * Obtener entidad project segun la id
     * @param id String id de project
     * @return Commit
     * @throws Exception en caso de no encontrar el project
     */
    @Override
    public Project getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Project project = manager.find(Project.class, id);
        controller.close();
        return project;
    }

    /**
     * inserta un project en la base de datos
     * @param project a insertar
     * @return project si ha insertado correctamente
     * @throws Exception si la insercion falla
     */
    @Override
    public Project save(Project project) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.persist(project);
            manager.getTransaction().commit();
            return project;
        }catch (Exception ex) {
            throw new Exception("Error al insertar Project "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
    }
    
    /**
     * actualiza un project en la base de datos
     * @param project a actualizar
     * @return project si ha actualizado correctamente
     * @throws Exception si la actualizacion falla
     */
    @Override
    public Project update(Project project) throws Exception {
        controller.open();
        try{
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.merge(project);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al actualizar Project con id" + project.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return project;
    }

    /**
     * elimina un project en la base de datos
     * @param project a eliminar
     * @return project si ha eliminado correctamente
     * @throws Exception si la eliminacion falla
     */
    @Override
    public Project delete(Project project) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            Project found = manager.find(Project.class, project.getId());
            if(found !=null) manager.remove(found);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al borrar Project con id" + project.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return project;
    }
}
