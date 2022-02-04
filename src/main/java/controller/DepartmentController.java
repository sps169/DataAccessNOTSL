package controller;

import dto.DepartmentDTO;
import service.DepartmentService;

import java.util.List;

import static controller.BaseController.jsonMapper;

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

    public String getAllDepartments() {
        try {
            return jsonMapper.writeValueAsString(service.getAllDepartments());
        }catch (Exception ex) {
            System.err.println("Error retrieving all departments "+ ex.getMessage());
            return null;
        }
    }

    public String getDepartmentById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getDepartmentById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving department with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public String insertDepartment(DepartmentDTO departmentDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertDepartment(departmentDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting department with id "+ departmentDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public String updateDepartment(DepartmentDTO departmentDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateDepartment(departmentDTO));
        }catch (Exception ex) {
            System.err.println("Error updating department with id"+ departmentDTO.getId() + ex.getMessage());
            return null;
        }
    }

    public String deleteDepartment(DepartmentDTO departmentDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteDepartment(departmentDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting department with id"+ departmentDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
