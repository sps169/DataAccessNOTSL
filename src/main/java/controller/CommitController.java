package controller;

import dto.CommitDTO;
import service.CommitService;

import java.util.List;

import static controller.BaseController.jsonMapper;

/**
 * Controlador para la entidad commit. Implementa un CRUD y sus metodos retornan JSON
 * con los resultados de las operaciones. Implementa un patron singleton.
 */
public class CommitController {
    private static CommitController controller;
    private final CommitService service;

    private CommitController (CommitService service) {
        this.service = service;
    }

    /**
     * obtener instancia singleton
     * @return instancia de CommitController
     */
    public static CommitController getInstance(CommitService service) {
        if (controller == null) {
            controller = new CommitController(service);
        }
        return controller;
    }

    /**
     * Obtener todas las entidades commit
     * @return String JSON con todos los commits
     */
    public String getAllCommits() {
        try {
            return jsonMapper.writeValueAsString(service.getAllCommits());
        }catch (Exception ex) {
            System.err.println("Error retrieving all commits "+ ex.getMessage());
            return null;
        }
    }

    /**
     * Obtener entidad commit segun la id
     * @param id String id del commit
     * @return String JSON con todos los commits
     */
    public String getCommitById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getCommitById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving commit with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    /**
     * inserta un commit en la base de datos
     * @param commitDTO commit a insertar
     * @return String JSON correcta si la operación se ha realizado, null en caso de no completarse
     */
    public String insertCommit(CommitDTO commitDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertCommit(commitDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting commit with id "+ commitDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    /**
     * actualiza un commit en la base de datos
     * @param commitDTO a actualizar
     * @return String JSON si la operacion se realiza, null en caso de no realizarse
     */
    public String updateCommit(CommitDTO commitDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateCommit(commitDTO));
        }catch (Exception ex) {
            System.err.println("Error updating commit with id"+ commitDTO.getId() + ex.getMessage());
            return null;
        }
    }

    /**
     * elimina un commit de la base de datos
     * @param commitDTO a eliminar
     * @return String JSON si la operacion se realiza, null en caso de no realizars
     */
    public String deleteCommit(CommitDTO commitDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteCommit(commitDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting commit with id"+ commitDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
