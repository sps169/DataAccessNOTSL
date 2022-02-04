package controller;

import dto.RepositoryDTO;
import service.RepositoryService;

import java.util.List;

import static controller.BaseController.jsonMapper;

public class RepositoryController {
    private static RepositoryController controller;
    private final RepositoryService service;

    private RepositoryController (RepositoryService service) {
        this.service = service;
    }

    public static RepositoryController getInstance(RepositoryService service) {
        if (controller == null) {
            controller = new RepositoryController(service);
        }
        return controller;
    }

    public String getAllRepositories() {
        try {
            return jsonMapper.writeValueAsString(service.getAllRepositories());
        }catch (Exception ex) {
            System.err.println("Error retrieving all repositorys "+ ex.getMessage());
            return null;
        }
    }

    public String getRepositoryById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getRepositoryById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving repository with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public String insertRepository(RepositoryDTO repositoryDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertRepository(repositoryDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting repository with id "+ repositoryDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public String updateRepository(RepositoryDTO repositoryDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateRepository(repositoryDTO));
        }catch (Exception ex) {
            System.err.println("Error updating repository with id"+ repositoryDTO.getId() + ex.getMessage());
            return null;
        }
    }

    public String deleteRepository(RepositoryDTO repositoryDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteRepository(repositoryDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting repository with id"+ repositoryDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
