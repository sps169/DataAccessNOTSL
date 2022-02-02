package controller;

import dto.DepartmentDTO;
import service.DepartmentService;

import java.util.List;

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

    public List<DepartmentDTO> getAllDepartments() {
        try {
            return service.getAllDepartments();
        }catch (Exception ex) {
            System.err.println("Error retrieving all departments "+ ex.getMessage());
            return null;
        }
    }

    public DepartmentDTO getDepartmentById(String id) {
        try {
            return service.getDepartmentById(id);
        }catch (Exception ex) {
            System.err.println("Error retreiving department with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public DepartmentDTO insertDepartment(DepartmentDTO departmentDTO) {
        try {
            return service.insertDepartment(departmentDTO);
        }catch (Exception ex) {
            System.err.println("Error inserting department with id "+ departmentDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        try {
            return service.updateDepartment(departmentDTO);
        }catch (Exception ex) {
            System.err.println("Error updating department with id"+ departmentDTO.getId());
            return null;
        }
    }

    public DepartmentDTO deleteDepartment(DepartmentDTO departmentDTO) {
        try {
            return service.deleteDepartment(departmentDTO);
        }catch (Exception ex) {
            System.err.println("Error updating department with id"+ departmentDTO.getId());
            return null;
        }
    }
}
