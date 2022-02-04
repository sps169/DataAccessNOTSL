package repository;

import dao.Commit;
import dao.Department;
import dao.Issue;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repositorio para la entidad department. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class DepartmentRepository implements CRUDRepository<Department,String> {
    private final DBController controller;

    /**
     * Constructor con dependencia de DBController
     * @param controller inyección de dependencia de DBController
     */
    public DepartmentRepository(DBController controller){
        this.controller = controller;
    }

    /**
     * Obtener todas las entidades department
     * @return Lista con todos los department
     */
    @Override
    public List<Department> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Department> query = manager.createNamedQuery("Department.findAll", Department.class);
        List<Department> departmentList = query.getResultList();
        controller.close();
        return departmentList;
    }

    /**
     * Obtener entidad department segun la id
     * @param id String id de department
     * @return Commit
     * @throws Exception en caso de no encontrar el department
     */
    @Override
    public Department getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Department department = manager.find(Department.class, id);
        controller.close();
        return department;
    }

    /**
     * inserta un department en la base de datos
     * @param department a insertar
     * @return department si ha insertado correctamente
     * @throws Exception si la insercion falla
     */
    @Override
    public Department save(Department department) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.persist(department);
            manager.getTransaction().commit();
            return department;
        }catch (Exception ex) {
            throw new Exception("Error al insertar Department "+ ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
    }

    /**
     * actualiza un department en la base de datos
     * @param department a actualizar
     * @return department si ha actualizado correctamente
     * @throws Exception si la actualizacion falla
     */
    @Override
    public Department update(Department department) throws Exception {
        controller.open();
        try{
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            manager.merge(department);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al actualizar Department con id" + department.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return department;
    }

    /**
     * elimina un department en la base de datos
     * @param department a eliminar
     * @return department si ha eliminado correctamente
     * @throws Exception si la eliminacion falla
     */
    @Override
    public Department delete(Department department) throws Exception {
        controller.open();
        try {
            EntityManager manager = controller.getManager();
            manager.getTransaction().begin();
            Department found = manager.find(Department.class, department.getId());
            if(found !=null) manager.remove(found);
            manager.getTransaction().commit();
        }catch (Exception ex) {
            throw new Exception("Error al borrar departamento con id" + department.getId() + " " + ex.getMessage());
        }finally {
            if (controller.getTransaction().isActive()) {
                controller.getTransaction().rollback();
            }
            controller.close();
        }
        return department;
    }
}
