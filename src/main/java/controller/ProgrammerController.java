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

    public ProgrammerDTO insertProgrammer(ProgrammerDTO programmerDTO) {
        try {
            return service.insertProgrammer(programmerDTO);
        }catch (Exception ex) {
            System.err.println("Error inserting programmer with id "+ programmerDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public ProgrammerDTO updateProgrammer(ProgrammerDTO programmerDTO) {
        try {
            return service.updateProgrammer(programmerDTO);
        }catch (Exception ex) {
            System.err.println("Error updating programmer with id"+ programmerDTO.getId());
            return null;
        }
    }

    public ProgrammerDTO deleteProgrammer(ProgrammerDTO programmerDTO) {
        try {
            return service.deleteProgrammer(programmerDTO);
        }catch (Exception ex) {
            System.err.println("Error updating programmer with id"+ programmerDTO.getId());
            return null;
        }
    }
}
