package controller;

import dto.RepositoryDTO;
import service.RepositoryService;

import java.util.List;

import static controller.BaseController.jsonMapper;

/**
 * Controlador para la entidad repositorio. Implementa un CRUD y sus metodos retornan JSON
 * con los resultados de las operaciones. Implementa un patron singleton.
 */
public class RepositoryController {
    private static RepositoryController controller;
    private final RepositoryService service;

    private RepositoryController (RepositoryService service) {
        this.service = service;
    }

    /**
     * obtener instancia singleton
     * @return instancia de RepositoryController
     */
    public static RepositoryController getInstance(RepositoryService service) {
        if (controller == null) {
            controller = new RepositoryController(service);
        }
        return controller;
    }

    /**
     * Obtener todas las entidades repositorio
     * @return String JSON con todos los repositorios
     */
    public String getAllRepositories() {
        try {
            return jsonMapper.writeValueAsString(service.getAllRepositories());
        }catch (Exception ex) {
            System.err.println("Error retrieving all repositorys "+ ex.getMessage());
            return null;
        }
    }

    /**
     * Obtener entidad repositorio segun la id
     * @param id String id del repositorio
     * @return String JSON con todos los repositorios
     */
    public String getRepositoryById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getRepositoryById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving repository with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    /**
     * inserta un repositorio en la base de datos
     * @param repositoryDTO repositorio a insertar
     * @return String JSON correcta si la operación se ha realizado, null en caso de no completarse
     */
    public String insertRepository(RepositoryDTO repositoryDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertRepository(repositoryDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting repository with id "+ repositoryDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    /**
     * actualiza un repositorio en la base de datos
     * @param repositoryDTO a actualizar
     * @return String JSON si la operacion se realiza, null en caso de no realizarse
     */
    public String updateRepository(RepositoryDTO repositoryDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateRepository(repositoryDTO));
        }catch (Exception ex) {
            System.err.println("Error updating repository with id"+ repositoryDTO.getId() + ex.getMessage());
            return null;
        }
    }
    
    /**
     * elimina un repositorio de la base de datos
     * @param repositoryDTO a eliminar
     * @return String JSON si la operacion se realiza, null en caso de no realizars
     */
    public String deleteRepository(RepositoryDTO repositoryDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteRepository(repositoryDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting repository with id"+ repositoryDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
