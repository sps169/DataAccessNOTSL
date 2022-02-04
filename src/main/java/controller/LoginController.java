package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.LoginDTO;
import service.LoginService;

import java.util.List;

import static controller.BaseController.jsonMapper;

/**
 * Controlador para la entidad login. Implementa un CRUD y sus metodos retornan JSON
 * con los resultados de las operaciones. Implementa un patron singleton.
 */
public class LoginController {
    private LoginService service;
    private static LoginController instance;

    private LoginController (LoginService service) {
        this.service = service;
    }

    public static LoginController getInstance(LoginService service) {
        if (instance == null) {
            instance = new LoginController(service);
        }
        return instance;
    }

    /**
     * Obtener todas las entidades login
     * @return String JSON con todos los logins
     */
    public String getAllLogins() {
        try {
            return jsonMapper.writeValueAsString(service.getAllLogins());
        }catch (Exception ex) {
            System.err.println("Error retrieving all logins "+ ex.getMessage());
            return null;
        }
    }

    /**
     * Obtener entidad login segun la id
     * @param id String id del login
     * @return String JSON con todos los logins
     */
    public String getLoginById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getLoginById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving login with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    /**
     * inserta un login en la base de datos
     * @param login login a insertar
     * @return String JSON correcta si la operación se ha realizado, null en caso de no completarse
     */
    public String insertLogin(LoginDTO login) {
        try {
            return jsonMapper.writeValueAsString(service.insertLogin(login));
        }catch (Exception ex) {
            System.err.println("Error inserting login with id "+ login.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    /**
     * actualiza un login en la base de datos
     * @param login a actualizar
     * @return String JSON si la operacion se realiza, null en caso de no realizarse
     */
    public String updateLogin(LoginDTO login) {
        try {
            return jsonMapper.writeValueAsString(service.updateLogin(login));
        }catch (Exception ex) {
            System.err.println("Error updating login with id"+ login.getId() + ex.getMessage());
            return null;
        }
    }

    /**
     * elimina un login de la base de datos
     * @param login a eliminar
     * @return String JSON si la operacion se realiza, null en caso de no realizars
     */
    public LoginDTO deleteLogin(LoginDTO login) {
        try {
            return service.deleteLogin(login);
        }catch (Exception ex) {
            System.err.println("Error deleting login with id"+ login.getId() + ex.getMessage());
            return null;
        }
    }
}
