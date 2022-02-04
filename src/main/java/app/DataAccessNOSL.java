package app;

import controller.*;
import dao.*;
import manager.HibernateController;
import mapper.*;
import repository.*;
import service.*;

import java.time.Instant;
import java.util.*;


public class DataAccessNOSL {

    private static DataAccessNOSL instance;

    ProgrammerController programmerController = ProgrammerController.getInstance(new ProgrammerService(new ProgrammerRepository(HibernateController.getInstance())));
    DepartmentController departmentController = DepartmentController.getInstance(new DepartmentService(new DepartmentRepository(HibernateController.getInstance())));
    ProjectController projectController = ProjectController.getInstance(new ProjectService(new ProjectRepository(HibernateController.getInstance())));
    CommitController commitController = CommitController.getInstance(new CommitService(new CommitRepository(HibernateController.getInstance())));
    IssueController issueController = IssueController.getInstance(new IssueService(new IssueRepository(HibernateController.getInstance())));
    RepositoryController repositoryController = RepositoryController.getInstance(new RepositoryService(new RepositoryRepository(HibernateController.getInstance())));
    LoginController loginController = LoginController.getInstance(new LoginService(new LoginRepository(HibernateController.getInstance())));
    ProgrammerMapper programmerMapper = new ProgrammerMapper();
    DepartmentMapper departmentMapper = new DepartmentMapper();
    ProjectMapper projectMapper = new ProjectMapper();
    CommitMapper commitMapper = new CommitMapper();
    IssueMapper issueMapper = new IssueMapper();
    RepositoryMapper repositoryMapper = new RepositoryMapper();
    LoginMapper loginMapper = new LoginMapper();


    private DataAccessNOSL () {

    }

    public static DataAccessNOSL getInstance() {
        if (instance == null) {
            instance = new DataAccessNOSL();
        }
        return instance;
    }

    public void insertDB() {
        Login login1 = new Login();
        login1.setId("53de5166-7ca8-4ceb-a1d2-20e85b53240f");
        login1.setTime(Date.from(Instant.now()));
        login1.setActive(true);
        login1.setToken(UUID.randomUUID());

        Login login2 = new Login();
        login2.setId("16811613-55f7-4d07-8a97-9a594c34d134");
        login2.setTime(Date.from(Instant.now()));
        login2.setActive(false);
        login2.setToken(UUID.randomUUID());

        Set<Login> logins = new HashSet<>();
        logins.add(login1);
        logins.add(login2);

        Set<Technology> techs = new HashSet<>();
        techs.add(Technology.Java);
        techs.add(Technology.C);
        techs.add(Technology.Python);

        Project project1 = new Project(
                "825ebbe7-fd4c-4d08-a01e-e8f4cdb786a6",
                "SkyNet Project",
                150000L,
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                techs,
                null,
                null
        );

        Issue issue1 = new Issue(
                "f5ba9a6e-070b-48a7-a752-85379b9dbe25",
                "Nos ha petado la API Rest gente",
                "Vamo a ver que habeis tocado?",
                Date.from(Instant.now()),
                new HashSet<>(),
                null,
                null
        );

        Issue issue2 = new Issue(
                "cd27ec39-caa0-41b4-9a66-af1944e84bb3",
                "Error al actualizar el windows",
                "microsoft nos la ha vuelto a jugar",
                Date.from(Instant.now()),
                new HashSet<>(),
                null,
                null
        );

        Commit commit1 = new Commit(
               "1397c742-642f-4cca-a79f-2551abe13978",
                "He parcheado la API Rest",
                "todo el dia parcheando como un señor",
                Date.from(Instant.now()),
                null,
                null,
                null,
                null
        );
        Commit commit2 = new Commit(
                "16a74ec9-2d00-455a-a631-9593f6ee6db0",
                "Tirado desde punto de restauracion",
                "lo saque de un foro ;)",
                Date.from(Instant.now()),
                null,
                null,
                null,
                null
        );

        Repository repository1 = new Repository(
                "ad6ed803-c9c6-42b2-b22e-777c32ca14af",
                "Repo el Reponedor",
                Date.from(Instant.now()),
                null,
                new HashSet<>(),
                new HashSet<>()
        );

        Programmer programmer = new Programmer(
             "169c55cd-d5e2-473d-bea4-0485c1dd80d0\n",
             "Sergio",
             "sps169@outlook.es",
             Date.from(Instant.now()),
                null,
             2000,
             "holi",
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        Department department1 = new Department (
                "094609d7-9b6a-4a86-8a32-f9c44c6d5a40",
                "Microservicios",
                150000L,
                new HashSet<>(),
                new HashSet<>(),
                null,
                new HashSet<>()
        );

        Programmer boss = new Programmer(
                "c35b9e15-76de-4589-a8e2-324f0b7eab36",
                "Fede",
                "fede@outlook.es",
                Date.from(Instant.now()),
                null,
                2000,
                "adios",
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        Programmer deptBoss = new Programmer(
                "cdc22493-1c61-40f5-a187-9c8a858f3ba6",
                "Loli",
                "loli@outlook.es",
                Date.from(Instant.now()),
                null,
                2000,
                "loli",
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );

        System.out.println("Programmer Insertion");
        programmerController.insertProgrammer(programmerMapper.toDTO(programmer));
        programmerController.insertProgrammer(programmerMapper.toDTO(boss));
        programmerController.insertProgrammer(programmerMapper.toDTO(deptBoss));

        System.out.println("Department Insertion");
        departmentController.insertDepartment(departmentMapper.toDTO(department1));

        System.out.println("Project Insertion");
        projectController.insertProject(projectMapper.toDTO(project1));

        System.out.println("Commit insertion");
        commitController.insertCommit(commitMapper.toDTO(commit1));
        commitController.insertCommit(commitMapper.toDTO(commit2));

        System.out.println("Issue insertion");
        issueController.insertIssue(issueMapper.toDTO(issue1));
        issueController.insertIssue(issueMapper.toDTO(issue2));

        System.out.println("Repo insertion");
        repositoryController.insertRepository(repositoryMapper.toDTO(repository1));

        System.out.println("Login insertion");
        loginController.insertLogin(loginMapper.toDTO(login1));
        loginController.insertLogin(loginMapper.toDTO(login2));

        System.out.println("Relationships updates");
        project1.setRepository(repository1);
        project1.setBoss(boss);

        Set<Programmer> programmers = new HashSet<Programmer>();
        programmers.add(programmer);
        programmers.add(boss);
        issue1.setProgrammers(programmers);
        issue1.setProject(project1);
        issue1.setRepository(repository1);

        issue2.setProgrammers(programmers);
        issue2.setProject(project1);
        issue2.setRepository(repository1);


        commit1.setRepository(repository1);
        commit1.setIssue(issue1);
        commit1.setProject(project1);
        commit1.setProgrammer(programmer);

        commit2.setRepository(repository1);
        commit2.setIssue(issue2);
        commit2.setProject(project1);
        commit2.setProgrammer(programmer);

        repository1.setProject(project1);
        Set<Commit> commits = new HashSet<>();
        commits.add(commit1);
        commits.add(commit2);
        repository1.setCommits(commits);
        Set<Issue> issues = new HashSet<>();
        issues.add(issue1);
        issues.add(issue2);
        repository1.setIssues(issues);

        Set<Technology> technologies = new HashSet<>();
        technologies.add(Technology.Java);
        technologies.add(Technology.C);
        programmer.setTechnologies(technologies);

        Set<Project> projects = new HashSet<>();
        projects.add(project1);
        programmer.setActiveProjects(projects);
        programmer.setCommits(commits);
        programmer.setIssues(issues);
        programmer.setLogins(logins);
        programmer.setDepartment(department1);

        department1.setBoss(deptBoss);
        department1.setOngoingProjects(projects);
        Set<Programmer> bosses = new HashSet<>();
        bosses.add(deptBoss);
        department1.setHistoricBosses(bosses);

        boss.setTechnologies(technologies);
        boss.setActiveProjects(projects);
        boss.setIssues(issues);
        boss.setDepartment(department1);

        deptBoss.setDepartment(department1);
        login1.setProgrammer(programmer);
        login2.setProgrammer(programmer);

        loginController.updateLogin(loginMapper.toDTO(login1));
        loginController.updateLogin(loginMapper.toDTO(login2));

        projectController.updateProject(projectMapper.toDTO(project1));

        issueController.updateIssue(issueMapper.toDTO(issue1));
        issueController.updateIssue(issueMapper.toDTO(issue2));

        commitController.updateCommit(commitMapper.toDTO(commit1));
        commitController.updateCommit(commitMapper.toDTO(commit2));

        repositoryController.updateRepository(repositoryMapper.toDTO(repository1));

        programmerController.updateProgrammer(programmerMapper.toDTO(programmer));
        programmerController.updateProgrammer(programmerMapper.toDTO(boss));
        programmerController.updateProgrammer(programmerMapper.toDTO(deptBoss));

        departmentController.updateDepartment(departmentMapper.toDTO(department1));

    }

    public void printDB() {

        System.out.println("PROGRAMMERS:");
        System.out.println(programmerController.getAllProgrammers());

        System.out.println("DEPARTMENTS:");
        System.out.println(departmentController.getAllDepartments());

        System.out.println("PROJECTS:");
        System.out.println(projectController.getAllProjects());

        System.out.println("REPOSITORIES:");
        System.out.println(repositoryController.getAllRepositories());

        System.out.println("COMMITS:");
        System.out.println(commitController.getAllCommits());

        System.out.println("ISSUES:");
        System.out.println(issueController.getAllIssues());

        System.out.println("LOGINS:");
        System.out.println(loginController.getAllLogins());
    }

    public void deleteDB() {
        try {
            ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepository(HibernateController.getInstance()));
            DepartmentService departmentService = new DepartmentService(new DepartmentRepository(HibernateController.getInstance()));

            departmentService.getAllDepartments().forEach(departmentController::deleteDepartment);
            programmerService.getAllProgrammers().forEach(programmerController::deleteProgrammer);
        } catch (Exception e) {
            System.err.println("Error borrando todos los datos de la base de datos");
        }
    }
//    }
//
//    public void Department(){
//
//        DepartmentDTO deparment1 = new DepartmentDTO(
//                UUID.randomUUID().toString(),
//                "Microservicios",
//                150000L,
//                new HashSet<Project>(),
//                new HashSet<Project>(),
//                new Programmer(),
//                new HashSet<Programmer>()
//                );
//
//        DepartmentDTO deparment2 = new DepartmentDTO(
//                UUID.randomUUID().toString(),
//                "Desarrollos Oscuros",
//                250000L,
//                new HashSet<Project>(),
//                new HashSet<Project>(),
//                new Programmer(),
//                new HashSet<Programmer>()
//        );
//    }
//    public void Commit(){
//
//        CommitDTO commit1 = new CommitDTO(
//                UUID.randomUUID().toString(),
//                "He parcheado la API Rest",
//                "todo el dia parcheando como un señor",
//                Date.from(Instant.now()),
//                new Repository(),
//                new Issue(),
//                new Project(),
//                new Programmer()
//        );
//        CommitDTO commit2 = new CommitDTO(
//                UUID.randomUUID().toString(),
//                "Tirado desde punto de restauracion",
//                "lo saque de un foro ;)",
//                Date.from(Instant.now()),
//                new Repository(),
//                new Issue(),
//                new Project(),
//                new Programmer()
//        );
//    }
//    public void Issue(){
//
//        IssueDTO issue1 = new IssueDTO(
//                UUID.randomUUID().toString(),
//                "Nos ha petado la API Rest gente",
//                "Vamo a ver que habeis tocado?",
//                LocalDateTime.now(),
//                new HashSet<Programmer>(),
//                new Project(),
//                new Repository()
//        );
//
//        IssueDTO issue2 = new IssueDTO(
//                UUID.randomUUID().toString(),
//                "Error al actualizar el windows",
//                "microsoft nos la ha vuelto a jugar",
//                LocalDateTime.now(),
//                new HashSet<Programmer>(),
//                new Project(),
//                new Repository()
//        );
//    }
//    public void Project(){
//
//        ProjectDTO project1 = new ProjectDTO(
//                UUID.randomUUID().toString(),
//                "SkyNet Project",
//                150000L,
//                Date.from(Instant.now()),
//                Date.from(Instant.now()),
//                new HashSet<Technology>(),
//                new Repository(),
//                new Programmer()
//        );
//
//        ProjectDTO project2 = new ProjectDTO(
//                UUID.randomUUID().toString(),
//                "BigGnome Project",
//                250000L,
//                Date.from(Instant.now()),
//                Date.from(Instant.now()),
//                new HashSet<Technology>(),
//                new Repository(),
//                new Programmer()
//        );
//    }
//    public void Repository(){
//
//        RepositoryDTO repository1 = new RepositoryDTO(
//                UUID.randomUUID().toString(),
//                "Repo el Reponedor",
//                Date.from(Instant.now()),
//                new Project(),
//                new HashSet<Commit>(),
//                new HashSet<Issue>()
//        );
//        RepositoryDTO repository2 = new RepositoryDTO(
//                UUID.randomUUID().toString(),
//                "Repo la Reposteria",
//                Date.from(Instant.now()),
//                new Project(),
//                new HashSet<Commit>(),
//                new HashSet<Issue>()
//        );
//    }
}
