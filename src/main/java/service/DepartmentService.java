package service;

import dao.Department;
import dao.Programmer;
import dto.DepartmentDTO;
import dto.ProgrammerDTO;
import mapper.DepartmentMapper;
import mapper.ProgrammerMapper;
import repository.DepartmentRepository;
import repository.ProgrammerRepository;

import java.util.List;

public class DepartmentService extends BaseService<Department, String, DepartmentRepository>{

    private DepartmentMapper mapper = new DepartmentMapper();
    protected DepartmentService(DepartmentRepository repository) {
        super(repository);
    }

    public List<DepartmentDTO> getAllDepartments() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    public DepartmentDTO getDepartmentById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    public DepartmentDTO insertDepartment(DepartmentDTO departmentDTO) throws Exception {
        if (checkDepartment(departmentDTO) != null) {
            Department department = this.save(mapper.fromDTO(departmentDTO));
            return mapper.toDTO(department);
        }else {
            throw new Exception(
                    "Error inserting department with id " + departmentDTO.getId()+" " +
                            ": Programmer doesn't meet requirements"
            );

        }
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) throws Exception {
        if (checkDepartment(departmentDTO) != null) {
            Department department = this.update(mapper.fromDTO(departmentDTO));
            return mapper.toDTO(department);
        }else {
            throw new Exception(
                    "Error updating programmer with id " + departmentDTO.getId()+" " +
                            ": Programmer doesn't meet requirements"
            );
        }
    }

    public DepartmentDTO deleteDepartment(DepartmentDTO departmentDTO) throws Exception {
        Department department = this.delete(mapper.fromDTO(departmentDTO));
        return mapper.toDTO(department);
    }

    //todo
    private DepartmentDTO checkDepartment(DepartmentDTO departmentDTO) {
        return departmentDTO;
    }
}
