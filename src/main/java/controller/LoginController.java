package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.LoginDTO;
import service.LoginService;

import java.util.List;

import static controller.BaseController.jsonMapper;

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

    public String getAllLogins() {
        try {
            return jsonMapper.writeValueAsString(service.getAllLogins());
        }catch (Exception ex) {
            System.err.println("Error retrieving all logins "+ ex.getMessage());
            return null;
        }
    }

    public String getLoginById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getLoginById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving login with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public String insertLogin(LoginDTO login) {
        try {
            return jsonMapper.writeValueAsString(service.insertLogin(login));
        }catch (Exception ex) {
            System.err.println("Error inserting login with id "+ login.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public String updateLogin(LoginDTO login) {
        try {
            return jsonMapper.writeValueAsString(service.updateLogin(login));
        }catch (Exception ex) {
            System.err.println("Error updating login with id"+ login.getId() + ex.getMessage());
            return null;
        }
    }

    public LoginDTO deleteLogin(LoginDTO login) {
        try {
            return service.deleteLogin(login);
        }catch (Exception ex) {
            System.err.println("Error deleting login with id"+ login.getId() + ex.getMessage());
            return null;
        }
    }
}
