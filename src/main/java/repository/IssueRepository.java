//package repository;
//
//import dao.Commit;
//import dao.Issue;
//import manager.DBController;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//public class IssueRepository implements CRUDRepository<Issue,String> {
//    private final DBController controller;
//
//    public IssueRepository(DBController controller){
//        this.controller = controller;
//    }
//
//    @Override
//    public List<Issue> findAll() throws Exception {
//        controller.open();
//        EntityManager manager = controller.getManager();
//        TypedQuery<Issue> query = manager.createNamedQuery("Issue.findAll", Issue.class);
//        List<Issue> issueList = query.getResultList();
//        controller.close();
//        return issueList;
//    }
//
//    @Override
//    public Issue getById(String id) throws Exception {
//        controller.open();
//        EntityManager manager = controller.getManager();
//        Issue issue = manager.find(Issue.class, id);
//        controller.close();
//        return issue;
//    }
//
//    @Override
//    public Issue save(Issue issue) throws Exception {
//        controller.open();
//        try {
//            EntityManager manager = controller.getManager();
//            manager.getTransaction().begin();
//            issue.setId(null);
//            manager.persist(issue);
//            manager.getTransaction().commit();
//            return issue;
//        }catch (Exception ex) {
//            throw new Exception("Error al insertar Issue "+ ex.getMessage());
//        }finally {
//            if (controller.getTransaction().isActive()) {
//                controller.getTransaction().rollback();
//            }
//            controller.close();
//        }
//    }
//
//    @Override
//    public Issue update(Issue issue) throws Exception {
//        controller.open();
//        try{
//            EntityManager manager = controller.getManager();
//            manager.getTransaction().begin();
//            manager.merge(issue);
//            manager.getTransaction().commit();
//        }catch (Exception ex) {
//            throw new Exception("Error al actualizar Issue con id" + issue.getId() + " " + ex.getMessage());
//        }finally {
//            if (controller.getTransaction().isActive()) {
//                controller.getTransaction().rollback();
//            }
//            controller.close();
//        }
//        return issue;
//    }
//
//    @Override
//    public Issue delete(Issue issue) throws Exception {
//        controller.open();
//        try {
//            EntityManager manager = controller.getManager();
//            manager.getTransaction().begin();
//            Commit found = manager.find(Commit.class, issue.getId());
//            if(found !=null) manager.remove(found);
//            manager.getTransaction().commit();
//        }catch (Exception ex) {
//            throw new Exception("Error al borrar Issue con id" + issue.getId() + " " + ex.getMessage());
//        }finally {
//            if (controller.getTransaction().isActive()) {
//                controller.getTransaction().rollback();
//            }
//            controller.close();
//        }
//        return issue;
//    }
//}
