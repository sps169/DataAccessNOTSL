package controller;

import dto.LoginDTO;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class LoginControllerTest {

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
    
    @BeforeAll
    static void setup() {

    }

    @Test
    @Order(1)
    void getAllLogins() {
    }

    @Test
    @Order(2)
    void getLoginById() {
    }

    @Test
    @Order(3)
    void insertLogin() {
    }

    @Test
    @Order(4)
    void updateLogin() {
    }

    @Test
    @Order(5)
    void deleteLogin() {
    }
}