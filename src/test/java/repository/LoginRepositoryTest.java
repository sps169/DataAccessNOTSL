package repository;

import app.DataAccessNOSL;
import dao.*;
import manager.HibernateController;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class LoginRepositoryTest {

    static LoginRepository repository = new LoginRepository(HibernateController.getInstance());
    static Login login1 = new Login(
            "53de5166-7ca8-4ceb-a1d2-20e85b53240f",
            null,
            Date.from(Instant.now()),
            UUID.randomUUID(),
            true
    );

    static Login login2 = new Login(
            "16811613-55f7-4d07-8a97-9a594c34d134",
            null,
            Date.from(Instant.now()),
            UUID.randomUUID(),
            false
    );

    static Login login2Updated = new Login(
            "16811613-55f7-4d07-8a97-9a594c34d134",
            null,
            Date.from(Instant.now()),
            UUID.fromString("1397c742-642f-4cca-a79f-2551abe13978"),
            false
    );

    @BeforeAll
    static void beforeAll() throws Exception {
        DataAccessNOSL.getInstance().deleteDB();
        repository.save(login1);

    }
    @Test
    @Order(1)
    void findAll() throws Exception {
        assertEquals(1, repository.findAll().size());
    }

    @Test
    @Order(2)
    void correctGetById() throws Exception {
        assertEquals(login1.getId(), repository.getById("53de5166-7ca8-4ceb-a1d2-20e85b53240f").getId());
    }

    @Test
    @Order(3)
    void save() throws Exception {
        assertEquals(login2, repository.save(login2));
    }

    @Test
    @Order(4)
    void update() throws Exception {
        assertEquals(login2Updated, repository.update(login2Updated));
    }

    @Test
    @Order(5)
    void delete() throws Exception {
        assertEquals(login2Updated, repository.delete(login2Updated));
    }

    @AfterAll
    static void afterAll() throws Exception {
        repository.delete(login1);
    }
}