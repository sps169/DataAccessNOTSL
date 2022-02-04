package repository;

import dao.Commit;
import dao.Department;
import dao.Issue;
import manager.DBController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DepartmentRepository implements CRUDRepository<Department,String> {
    private final DBController controller;

    public DepartmentRepository(DBController controller){
        this.controller = controller;
    }

    @Override
    public List<Department> findAll() throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        TypedQuery<Department> query = manager.createNamedQuery("Department.findAll", Department.class);
        List<Department> departmentList = query.getResultList();
        controller.close();
        return departmentList;
    }

    @Override
    public Department getById(String id) throws Exception {
        controller.open();
        EntityManager manager = controller.getManager();
        Department department = manager.find(Department.class, id);
        controller.close();
        return department;
    }

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

    public Department saveInSession(Department department) throws Exception {
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
        }
    }

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
