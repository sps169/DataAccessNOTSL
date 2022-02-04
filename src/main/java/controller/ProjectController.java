package controller;

import dto.ProjectDTO;
import service.ProjectService;

import java.util.List;

import static controller.BaseController.jsonMapper;

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

    public String getAllProjects() {
        try {
            return jsonMapper.writeValueAsString(service.getAllProjects());
        }catch (Exception ex) {
            System.err.println("Error retrieving all projects "+ ex.getMessage());
            return null;
        }
    }

    public String getProjectById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getProjectById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving project with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public String insertProject(ProjectDTO projectDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertProject(projectDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting project with id "+ projectDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public String updateProject(ProjectDTO projectDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateProject(projectDTO));
        }catch (Exception ex) {
            System.err.println("Error updating project with id"+ projectDTO.getId() + ex.getMessage());
            return null;
        }
    }

    public String deleteProject(ProjectDTO projectDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteProject(projectDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting project with id"+ projectDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
