package service;

import app.DataAccessNOSL;
import dao.Login;
import dto.LoginDTO;
import org.junit.jupiter.api.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import repository.LoginRepository;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class LoginServiceTest {

    static LoginRepository repository;
    static LoginService service;

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

    static LoginDTO loginDTO1 = new LoginDTO(
            "53de5166-7ca8-4ceb-a1d2-20e85b53240f",
            null,
            Date.from(Instant.now()),
            UUID.randomUUID(),
            true
    );

    static LoginDTO loginDTO2 = new LoginDTO(
            "16811613-55f7-4d07-8a97-9a594c34d134",
            null,
            Date.from(Instant.now()),
            UUID.randomUUID(),
            false
    );

    static LoginDTO loginDTO2Updated = new LoginDTO(
            "16811613-55f7-4d07-8a97-9a594c34d134",
            null,
            Date.from(Instant.now()),
            UUID.fromString("1397c742-642f-4cca-a79f-2551abe13978"),
            false
    );

    static List<Login> logins = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        logins.add(login1);
        logins.add(login2);
        repository = Mockito.mock(LoginRepository.class);
        service = new LoginService(repository);
    }

    @Test
    @Order(1)
    void getAllLogins() throws Exception {
        Mockito.when(repository.findAll()).thenReturn(logins);
        assertEquals(service.getAllLogins().size(), 2);

        Mockito.verify(repository, Mockito.atLeastOnce()).findAll();
    }

    @Test
    @Order(2)
    void getLoginById() throws Exception {
        Mockito.when(repository.getById(login1.getId())).thenReturn(login1);
        assertEquals(service.getLoginById("53de5166-7ca8-4ceb-a1d2-20e85b53240f"), loginDTO1);

        Mockito.verify(repository, Mockito.atLeastOnce()).getById(login1.getId());
    }

    @Test
    @Order(3)
    void insertLogin() throws Exception {
        Mockito.when(repository.save(login2)).thenReturn(login2);
        assertEquals(service.insertLogin(loginDTO2), loginDTO2);

        Mockito.verify(repository, Mockito.atLeastOnce()).save(login2);
    }

    @Test
    @Order(4)
    void updateLogin() throws Exception {
        Mockito.when(repository.update(login2Updated)).thenReturn(login2Updated);
        assertEquals(service.updateLogin(loginDTO2Updated), loginDTO2Updated);

        Mockito.verify(repository, Mockito.atLeastOnce()).update(login2Updated);
    }

    @Test
    @Order(5)
    void deleteLogin() throws Exception {
        Mockito.when(repository.delete(login2Updated)).thenReturn(login2Updated);
        assertEquals(service.deleteLogin(loginDTO2Updated), loginDTO2Updated);

        Mockito.verify(repository, Mockito.atLeastOnce()).delete(login2Updated);
    }
}