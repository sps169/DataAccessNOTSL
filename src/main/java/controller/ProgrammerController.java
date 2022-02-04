package controller;

import dto.ProgrammerDTO;
import service.ProgrammerService;

import java.util.List;

import static controller.BaseController.jsonMapper;

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

    public String getAllProgrammers() {
        try {
            return jsonMapper.writeValueAsString(service.getAllProgrammers());
        }catch (Exception ex) {
            System.err.println("Error retrieving all programmers "+ ex.getMessage());
            return null;
        }
    }

    public String getProgrammerById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getProgrammerById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving programmer with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public String insertProgrammer(ProgrammerDTO programmerDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertProgrammer(programmerDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting programmer with id "+ programmerDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public String updateProgrammer(ProgrammerDTO programmerDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateProgrammer(programmerDTO));
        }catch (Exception ex) {
            System.err.println("Error updating programmer with id"+ programmerDTO.getId() + " " + ex.getMessage());
            return null;
        }
    }

    public ProgrammerDTO deleteProgrammer(ProgrammerDTO programmerDTO) {
        try {
            return service.deleteProgrammer(programmerDTO);
        }catch (Exception ex) {
            System.err.println("Error deleting programmer with id"+ programmerDTO.getId() + " " + ex.getMessage());
            return null;
        }
    }
}
