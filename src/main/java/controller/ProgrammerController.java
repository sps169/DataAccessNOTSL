package controller;

import dto.ProgrammerDTO;
import service.ProgrammerService;

import java.util.List;

public class ProgrammerController {
    private static ProgrammerController controller;
    private ProgrammerService service;

    private ProgrammerController (ProgrammerService service) {
        this.service = service;
    }

    public static ProgrammerController getInstance(ProgrammerService service) {
        if (controller == null) {
            controller = new ProgrammerController(service);
        }
        return controller;
    }

    public List<ProgrammerDTO> getAllProgrammers() {
        try {
            return service.getAllProgrammers();
        }catch (Exception ex) {
            System.err.println("Error retrieving all programmers "+ ex.getMessage());
            return null;
        }
    }

    public ProgrammerDTO getProgrammerById(String id) {
        try {
            return service.getProgrammerById(id);
        }catch (Exception ex) {
            System.err.println("Error retreiving programmer with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public ProgrammerDTO insertProgrammer(ProgrammerDTO login) {
        try {
            return service.insertProgrammer(login);
        }catch (Exception ex) {
            System.err.println("Error inserting programmer with id "+ login.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public ProgrammerDTO updateProgrammer(ProgrammerDTO login) {
        try {
            return service.updateProgrammer(login);
        }catch (Exception ex) {
            System.err.println("Error updating programmer with id"+ login.getId());
            return null;
        }
    }

    public ProgrammerDTO deleteProgrammer(ProgrammerDTO login) {
        try {
            return service.deleteProgrammer(login);
        }catch (Exception ex) {
            System.err.println("Error updating programmer with id"+ login.getId());
            return null;
        }
    }
}
