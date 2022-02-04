package controller;

import dto.IssueDTO;
import service.IssueService;

import java.util.List;

import static controller.BaseController.jsonMapper;

/**
 * Controlador para la entidad issue. Implementa un CRUD y sus metodos retornan JSON
 * con los resultados de las operaciones. Implementa un patron singleton.
 */
public class IssueController {
    private static IssueController controller;
    private final IssueService service;

    private IssueController (IssueService service) {
        this.service = service;
    }

    /**
     * obtener instancia singleton
     * @return instancia de IssueController
     */
    public static IssueController getInstance(IssueService service) {
        if (controller == null) {
            controller = new IssueController(service);
        }
        return controller;
    }

    /**
     * Obtener todas las entidades issue
     * @return String JSON con todos los issues
     */
    public String getAllIssues() {
        try {
            return jsonMapper.writeValueAsString(service.getAllIssues());
        }catch (Exception ex) {
            System.err.println("Error retrieving all issues "+ ex.getMessage());
            return null;
        }
    }

    /**
     * Obtener entidad issue segun la id
     * @param id String id del issue
     * @return String JSON con todos los issues
     */
    public String getIssueById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getIssueById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving issue with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    /**
     * inserta un issue en la base de datos
     * @param issueDTO issue a insertar
     * @return String JSON correcta si la operación se ha realizado, null en caso de no completarse
     */
    public String insertIssue(IssueDTO issueDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertIssue(issueDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting issue with id "+ issueDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    /**
     * actualiza un issue en la base de datos
     * @param issueDTO a actualizar
     * @return String JSON si la operacion se realiza, null en caso de no realizarse
     */
    public String updateIssue(IssueDTO issueDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateIssue(issueDTO));
        }catch (Exception ex) {
            System.err.println("Error updating issue with id"+ issueDTO.getId() + ex.getMessage());
            return null;
        }
    }

    /**
     * elimina un issue de la base de datos
     * @param issueDTO a eliminar
     * @return String JSON si la operacion se realiza, null en caso de no realizars
     */
    public String deleteIssue(IssueDTO issueDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteIssue(issueDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting issue with id"+ issueDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
