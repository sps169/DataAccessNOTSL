package controller;

import dto.DepartmentDTO;
import service.DepartmentService;

import java.util.List;

import static controller.BaseController.jsonMapper;

/**
 * Controlador para la entidad departamento. Implementa un CRUD y sus metodos retornan JSON
 * con los resultados de las operaciones. Implementa un patron singleton.
 */
public class DepartmentController {
    private static DepartmentController controller;
    private final DepartmentService service;

    private DepartmentController (DepartmentService service) {
        this.service = service;
    }

    public static DepartmentController getInstance(DepartmentService service) {
        if (controller == null) {
            controller = new DepartmentController(service);
        }
        return controller;
    }

    /**
     * Obtener todas las entidades departamento
     * @return String JSON con todos los departamentos
     */
    public String getAllDepartments() {
        try {
            return jsonMapper.writeValueAsString(service.getAllDepartments());
        }catch (Exception ex) {
            System.err.println("Error retrieving all departments "+ ex.getMessage());
            return null;
        }
    }

    /**
     * Obtener entidad departamento segun la id
     * @param id String id del departamento
     * @return String JSON con todos los departamentos
     */
    public String getDepartmentById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getDepartmentById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving department with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    /**
     * inserta un departamento en la base de datos
     * @param departmentDTO departamento a insertar
     * @return String JSON correcta si la operación se ha realizado, null en caso de no completarse
     */
    public String insertDepartment(DepartmentDTO departmentDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertDepartment(departmentDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting department with id "+ departmentDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    /**
     * actualiza un departamento en la base de datos
     * @param departmentDTO a actualizar
     * @return String JSON si la operacion se realiza, null en caso de no realizarse
     */
    public String updateDepartment(DepartmentDTO departmentDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateDepartment(departmentDTO));
        }catch (Exception ex) {
            System.err.println("Error updating department with id"+ departmentDTO.getId() + ex.getMessage());
            return null;
        }
    }

    /**
     * elimina un departamento de la base de datos
     * @param departmentDTO a eliminar
     * @return String JSON si la operacion se realiza, null en caso de no realizars
     */
    public String deleteDepartment(DepartmentDTO departmentDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteDepartment(departmentDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting department with id"+ departmentDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
