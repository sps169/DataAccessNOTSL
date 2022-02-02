package controller;

import dto.RepositoryDTO;
import service.RepositoryService;

import java.util.List;

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

    public List<RepositoryDTO> getAllRepositorys() {
        try {
            return service.getAllRepositorys();
        }catch (Exception ex) {
            System.err.println("Error retrieving all repositorys "+ ex.getMessage());
            return null;
        }
    }

    public RepositoryDTO getRepositoryById(String id) {
        try {
            return service.getRepositoryById(id);
        }catch (Exception ex) {
            System.err.println("Error retreiving repository with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public RepositoryDTO insertRepository(RepositoryDTO repositoryDTO) {
        try {
            return service.insertRepository(repositoryDTO);
        }catch (Exception ex) {
            System.err.println("Error inserting repository with id "+ repositoryDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public RepositoryDTO updateRepository(RepositoryDTO repositoryDTO) {
        try {
            return service.updateRepository(repositoryDTO);
        }catch (Exception ex) {
            System.err.println("Error updating repository with id"+ repositoryDTO.getId());
            return null;
        }
    }

    public RepositoryDTO deleteRepository(RepositoryDTO repositoryDTO) {
        try {
            return service.deleteRepository(repositoryDTO);
        }catch (Exception ex) {
            System.err.println("Error updating repository with id"+ repositoryDTO.getId());
            return null;
        }
    }
}
