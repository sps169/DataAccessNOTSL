package controller;

import dto.ProgrammerDTO;
import service.ProgrammerService;

import java.util.List;

import static controller.BaseController.jsonMapper;

/**
 * Controlador para la entidad programador. Implementa un CRUD y sus metodos retornan JSON
 * con los resultados de las operaciones. Implementa un patron singleton.
 */
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

    /**
     * Obtener todas las entidades programador
     * @return String JSON con todos los programadors
     */
    public String getAllProgrammers() {
        try {
            return jsonMapper.writeValueAsString(service.getAllProgrammers());
        }catch (Exception ex) {
            System.err.println("Error retrieving all programmers "+ ex.getMessage());
            return null;
        }
    }

    /**
     * Obtener entidad programador segun la id
     * @param id String id del programador
     * @return String JSON con todos los programadors
     */
    public String getProgrammerById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getProgrammerById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving programmer with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    /**
     * inserta un programador en la base de datos
     * @param programmerDTO programador a insertar
     * @return String JSON correcta si la operación se ha realizado, null en caso de no completarse
     */
    public String insertProgrammer(ProgrammerDTO programmerDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertProgrammer(programmerDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting programmer with id "+ programmerDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    /**
     * actualiza un programador en la base de datos
     * @param programmerDTO a actualizar
     * @return String JSON si la operacion se realiza, null en caso de no realizarse
     */
    public String updateProgrammer(ProgrammerDTO programmerDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateProgrammer(programmerDTO));
        }catch (Exception ex) {
            System.err.println("Error updating programmer with id"+ programmerDTO.getId() + " " + ex.getMessage());
            return null;
        }
    }

    /**
     * elimina un programador de la base de datos
     * @param programmerDTO a eliminar
     * @return String JSON si la operacion se realiza, null en caso de no realizars
     */
    public ProgrammerDTO deleteProgrammer(ProgrammerDTO programmerDTO) {
        try {
            return service.deleteProgrammer(programmerDTO);
        }catch (Exception ex) {
            System.err.println("Error deleting programmer with id"+ programmerDTO.getId() + " " + ex.getMessage());
            return null;
        }
    }
}
