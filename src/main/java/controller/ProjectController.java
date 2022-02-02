package controller;

import dto.ProjectDTO;
import service.ProjectService;

import java.util.List;

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

    public List<ProjectDTO> getAllProjects() {
        try {
            return service.getAllProjects();
        }catch (Exception ex) {
            System.err.println("Error retrieving all projects "+ ex.getMessage());
            return null;
        }
    }

    public ProjectDTO getProjectById(String id) {
        try {
            return service.getProjectById(id);
        }catch (Exception ex) {
            System.err.println("Error retreiving project with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public ProjectDTO insertProject(ProjectDTO projectDTO) {
        try {
            return service.insertProject(projectDTO);
        }catch (Exception ex) {
            System.err.println("Error inserting project with id "+ projectDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public ProjectDTO updateProject(ProjectDTO projectDTO) {
        try {
            return service.updateProject(projectDTO);
        }catch (Exception ex) {
            System.err.println("Error updating project with id"+ projectDTO.getId());
            return null;
        }
    }

    public ProjectDTO deleteProject(ProjectDTO projectDTO) {
        try {
            return service.deleteProject(projectDTO);
        }catch (Exception ex) {
            System.err.println("Error updating project with id"+ projectDTO.getId());
            return null;
        }
    }
}
