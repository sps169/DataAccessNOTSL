package controller;

import dto.ProjectDTO;
import service.ProjectService;

import java.util.List;

import static controller.BaseController.jsonMapper;

/**
 * Controlador para la entidad proyecto. Implementa un CRUD y sus metodos retornan JSON
 * con los resultados de las operaciones. Implementa un patron singleton.
 */
public class ProjectController {
    private static ProjectController controller;
    private final ProjectService service;

    private ProjectController (ProjectService service) {
        this.service = service;
    }

    public static ProjectController getInstance(ProjectService service) {
        if (controller == null) {
            controller = new ProjectController(service);
        }
        return controller;
    }

    /**
     * Obtener todas las entidades proyecto
     * @return String JSON con todos los proyectos
     */
    public String getAllProjects() {
        try {
            return jsonMapper.writeValueAsString(service.getAllProjects());
        }catch (Exception ex) {
            System.err.println("Error retrieving all projects "+ ex.getMessage());
            return null;
        }
    }

    /**
     * Obtener entidad proyecto segun la id
     * @param id String id del proyecto
     * @return String JSON con todos los proyectos
     */
    public String getProjectById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getProjectById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving project with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    /**
     * inserta un proyecto en la base de datos
     * @param projectDTO proyecto a insertar
     * @return String JSON correcta si la operación se ha realizado, null en caso de no completarse
     */
    public String insertProject(ProjectDTO projectDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertProject(projectDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting project with id "+ projectDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    /**
     * actualiza un proyecto en la base de datos
     * @param projectDTO a actualizar
     * @return String JSON si la operacion se realiza, null en caso de no realizarse
     */
    public String updateProject(ProjectDTO projectDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateProject(projectDTO));
        }catch (Exception ex) {
            System.err.println("Error updating project with id"+ projectDTO.getId() + ex.getMessage());
            return null;
        }
    }

    /**
     * elimina un proyecto de la base de datos
     * @param projectDTO a eliminar
     * @return String JSON si la operacion se realiza, null en caso de no realizars
     */
    public String deleteProject(ProjectDTO projectDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteProject(projectDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting project with id"+ projectDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
