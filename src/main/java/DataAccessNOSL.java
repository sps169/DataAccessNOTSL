import dao.Login;
import manager.HibernateController;
import repository.LoginRepository;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

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

        Login login1 = new Login();
        login1.setId("02fbb3dd-c1c4-43c4-8606-248463d99f00");
        login1.setTime(Date.from(Instant.now()));
        login1.setActive(true);
        login1.setToken(UUID.randomUUID());

        Login login2 = new Login();
        login2.setId("425ff092-e3a7-41bc-b9e9-2984295a846e");
        login2.setTime(Date.from(Instant.now()));
        login2.setActive(false);
        login2.setToken(UUID.randomUUID());


        try {
            login1 = repo.save(login1);
            login2 = repo.save(login2);

            repo.findAll().forEach(System.out::println);
            if (repo.delete(login1) != null) System.out.println("true");
            if (repo.delete(login2) != null) System.out.println("true");
        }catch (Exception e) {
            System.out.println("Error de ejecución: " + e.getMessage());
        }
    }
}
