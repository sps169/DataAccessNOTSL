import controller.LoginController;
import controller.ProgrammerController;
import dao.*;
import dto.*;
import manager.HibernateController;
import mapper.LoginMapper;
import mapper.ProgrammerMapper;
import repository.LoginRepository;
import repository.ProgrammerRepository;
import service.LoginService;
import service.ProgrammerService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class DataAccessNOSL {

    private static DataAccessNOSL instance;
    private DataAccessNOSL () {

    }

    public static DataAccessNOSL getInstance() {
        if (instance == null) {
            instance = new DataAccessNOSL();
        }
        return instance;
    }

    public void Login () {
        LoginRepository repo = new LoginRepository(HibernateController.getInstance());
        LoginService service = new LoginService(repo);
        LoginController controller = LoginController.getInstance(service);

        Programmer programmer1 = new Programmer();
        programmer1.setId("aa70c819-5753-48d3-90d3-5a04f1dcfb12");
        programmer1.setName("Sergio");
        programmer1.setMail("sps169@outlook.es");
        programmer1.setEntryDate(Date.from(Instant.now()));
        programmer1.setSalary(2000);
        programmer1.setPassword("holi");

        LoginDTO login1 = new LoginDTO();
        login1.setTime(Date.from(Instant.now()));
        login1.setActive(true);
        login1.setToken(UUID.randomUUID());
        login1.setProgrammer(programmer1);

        LoginDTO login2 = new LoginDTO();
        login2.setTime(Date.from(Instant.now()));
        login2.setActive(false);
        login2.setToken(UUID.randomUUID());
        login2.setProgrammer(programmer1);

        controller.insertLogin(login1);
        controller.insertLogin(login2);
//        List<LoginDTO> logins = controller.getAllLogins();
//        logins.forEach(System.out::println);
//        logins.forEach(controller::deleteLogin);
    }

    public void Programmer() {
        ProgrammerController controller = ProgrammerController.getInstance(new ProgrammerService(new ProgrammerRepository(HibernateController.getInstance())));
        LoginController controllerLogins = LoginController.getInstance(new LoginService(new LoginRepository(HibernateController.getInstance())));
        ProgrammerMapper programmerMapper = new ProgrammerMapper();
        LoginMapper loginMapper = new LoginMapper();

        Programmer programmer1 = new Programmer();
        programmer1.setId(UUID.randomUUID().toString());
        programmer1.setName("Sergio");
        programmer1.setMail("sps169@outlook.es");
        programmer1.setEntryDate(Date.from(Instant.now()));
        programmer1.setSalary(2000);
        programmer1.setPassword("holi");

        Login login1 = new Login();
        login1.setId(UUID.randomUUID().toString());
        login1.setTime(Date.from(Instant.now()));
        login1.setActive(true);
        login1.setToken(UUID.randomUUID());
        login1.setProgrammer(programmer1);

        Login login2 = new Login();
        login2.setId(UUID.randomUUID().toString());
        login2.setTime(Date.from(Instant.now()));
        login2.setActive(false);
        login2.setToken(UUID.randomUUID());
        login2.setProgrammer(programmer1);

        HashSet<Login> setLogins = new HashSet<>();
        setLogins.add(login1);
        setLogins.add(login2);
        programmer1.setLogins(setLogins);

        ProgrammerDTO programmerDTO = programmerMapper.toDTO(programmer1);
        try {
            controller.insertProgrammer(programmerDTO);
            controller.getAllProgrammers().forEach(System.out::println);
            controllerLogins.insertLogin(loginMapper.toDTO(login1));
            controllerLogins.insertLogin(loginMapper.toDTO(login2));
            controller.getAllProgrammers().forEach(System.out::println);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void Department(){

        DepartmentDTO deparment1 = new DepartmentDTO(
                UUID.randomUUID().toString(),
                "Microservicios",
                150000L,
                new HashSet<Project>(),
                new HashSet<Project>(),
                new Programmer(),
                new HashSet<Programmer>()
                );

        DepartmentDTO deparment2 = new DepartmentDTO(
                UUID.randomUUID().toString(),
                "Desarrollos Oscuros",
                250000L,
                new HashSet<Project>(),
                new HashSet<Project>(),
                new Programmer(),
                new HashSet<Programmer>()
        );
    }
    public void Commit(){

        CommitDTO commit1 = new CommitDTO(
                UUID.randomUUID().toString(),
                "He parcheado la API Rest",
                "todo el dia parcheando como un señor",
                LocalDateTime.now(),
                new Repository(),
                new Issue(),
                new Project(),
                new Programmer()
        );
        CommitDTO commit2 = new CommitDTO(
                UUID.randomUUID().toString(),
                "Tirado desde punto de restauracion",
                "lo saque de un foro ;)",
                LocalDateTime.now(),
                new Repository(),
                new Issue(),
                new Project(),
                new Programmer()
        );
    }
    public void Issue(){

        IssueDTO issue1 = new IssueDTO(
                UUID.randomUUID().toString(),
                "Nos ha petado la API Rest gente",
                "Vamo a ver que habeis tocado?",
                LocalDateTime.now(),
                new HashSet<Programmer>(),
                new Project(),
                new Repository()
        );

        IssueDTO issue2 = new IssueDTO(
                UUID.randomUUID().toString(),
                "Error al actualizar el windows",
                "microsoft nos la ha vuelto a jugar",
                LocalDateTime.now(),
                new HashSet<Programmer>(),
                new Project(),
                new Repository()
        );
    }
    public void Project(){

        ProjectDTO project1 = new ProjectDTO(
                UUID.randomUUID().toString(),
                "SkyNet Project",
                150000L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                new Repository(),
                new Programmer()
        );

        ProjectDTO project2 = new ProjectDTO(
                UUID.randomUUID().toString(),
                "BigGnome Project",
                250000L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                new Repository(),
                new Programmer()
        );
    }
    public void Repository(){

        RepositoryDTO repository1 = new RepositoryDTO(
                UUID.randomUUID().toString(),
                "Repo el Reponedor",
                LocalDateTime.now(),
                new Project(),
                new HashSet<Commit>(),
                new HashSet<Issue>()
        );
        RepositoryDTO repository2 = new RepositoryDTO(
                UUID.randomUUID().toString(),
                "Repo la Reposteria",
                LocalDateTime.now(),
                new Project(),
                new HashSet<Commit>(),
                new HashSet<Issue>()
        );
    }


}
