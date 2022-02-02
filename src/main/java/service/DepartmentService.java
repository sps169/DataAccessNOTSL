package service;

import dao.Department;
import dto.DepartmentDTO;
import mapper.DepartmentMapper;
import repository.DepartmentRepository;

import java.util.List;

public class DepartmentService extends BaseService<Department, String, DepartmentRepository>{

    private final DepartmentMapper mapper = new DepartmentMapper();
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
                            ": Department doesn't meet requirements"
            );

        }
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) throws Exception {
        if (checkDepartment(departmentDTO) != null) {
            Department department = this.update(mapper.fromDTO(departmentDTO));
            return mapper.toDTO(department);
        }else {
            throw new Exception(
                    "Error updating department with id " + departmentDTO.getId()+" " +
                            ": Department doesn't meet requirements"
            );
        }
    }

    public DepartmentDTO deleteDepartment(DepartmentDTO departmentDTO) throws Exception {
        Department department = this.delete(mapper.fromDTO(departmentDTO));
        return mapper.toDTO(department);
    }

    //todo
    private DepartmentDTO checkDepartment(DepartmentDTO departmentDTO) {
        boolean isCorrect = true;
        if (!departmentDTO.getBoss().getActiveProjects().isEmpty())
            isCorrect = false;
        if (isCorrect)
            return departmentDTO;
        else
            return null;
    }
}
