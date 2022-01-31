//package repository;
//
//import dao.Commit;
//import dao.Programmer;
//import dao.Project;
//import manager.DBController;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//public class ProjectRepository implements CRUDRepository<Project,String> {
//    private final DBController controller;
//
//    public ProjectRepository(DBController controller){
//        this.controller = controller;
//    }
//
//    @Override
//    public List<Project> findAll() throws Exception {
//        controller.open();
//        EntityManager manager = controller.getManager();
//        TypedQuery<Project> query = manager.createNamedQuery("Project.findAll", Project.class);
//        List<Project> projectList = query.getResultList();
//        controller.close();
//        return projectList;
//    }
//
//    @Override
//    public Project getById(String id) throws Exception {
//        controller.open();
//        EntityManager manager = controller.getManager();
//        Project project = manager.find(Project.class, id);
//        controller.close();
//        return project;
//    }
//
//    @Override
//    public Project save(Project project) throws Exception {
//        controller.open();
//        try {
//            EntityManager manager = controller.getManager();
//            manager.getTransaction().begin();
//            project.setId(null);
//            manager.persist(project);
//            manager.getTransaction().commit();
//            return project;
//        }catch (Exception ex) {
//            throw new Exception("Error al insertar Project "+ ex.getMessage());
//        }finally {
//            if (controller.getTransaction().isActive()) {
//                controller.getTransaction().rollback();
//            }
//            controller.close();
//        }
//    }
//
//    @Override
//    public Project update(Project project) throws Exception {
//        controller.open();
//        try{
//            EntityManager manager = controller.getManager();
//            manager.getTransaction().begin();
//            manager.merge(project);
//            manager.getTransaction().commit();
//        }catch (Exception ex) {
//            throw new Exception("Error al actualizar Project con id" + project.getId() + " " + ex.getMessage());
//        }finally {
//            if (controller.getTransaction().isActive()) {
//                controller.getTransaction().rollback();
//            }
//            controller.close();
//        }
//        return project;
//    }
//
//    @Override
//    public Project delete(Project project) throws Exception {
//        controller.open();
//        try {
//            EntityManager manager = controller.getManager();
//            manager.getTransaction().begin();
//            Commit found = manager.find(Commit.class, project.getId());
//            if(found !=null) manager.remove(found);
//            manager.getTransaction().commit();
//        }catch (Exception ex) {
//            throw new Exception("Error al borrar Project con id" + project.getId() + " " + ex.getMessage());
//        }finally {
//            if (controller.getTransaction().isActive()) {
//                controller.getTransaction().rollback();
//            }
//            controller.close();
//        }
//        return project;
//    }
//}
