package service;

import dao.Department;
import dao.Programmer;
import dao.Project;
import dto.DepartmentDTO;
import manager.HibernateController;
import mapper.DepartmentMapper;
import mapper.ProjectMapper;
import repository.DepartmentRepository;
import repository.ProgrammerRepository;
import repository.ProjectRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DepartmentService extends BaseService<Department, String, DepartmentRepository>{

    private final DepartmentMapper mapper = new DepartmentMapper();
    public DepartmentService(DepartmentRepository repository) {
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
        ProgrammerRepository programmerRepository = new ProgrammerRepository(HibernateController.getInstance());
        System.out.println("Cleaning programmers references to this department");
        programmerRepository.findAll().stream().filter(p -> p.getDepartment().getId().equals(departmentDTO.getId())).forEach(s -> {
            s.setDepartment(null);
            try {
                programmerRepository.update(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        departmentDTO.setHistoricBosses(null);
        departmentDTO.setBoss(null);
        this.updateDepartment(departmentDTO);

        ProjectService projectService = new ProjectService(new ProjectRepository(HibernateController.getInstance()));
        ProjectMapper projectMapper = new ProjectMapper();
        Set<Project> projects = departmentDTO.getOngoingProjects();
        projects.addAll(departmentDTO.getEndedProjects());
        System.out.println("Cleaning Departments projects");
        projects.forEach(p -> {
            try {
                System.out.println("Fetching department's project");
                Project project = projectService.getById(p.getId());
                System.out.println("Deleting Project from Department");
                projectService.deleteProject(projectMapper.toDTO(project));
                System.out.println("Department's project was successfully deleted");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Department's project wasn't deleted");
            }
        });
        departmentDTO.setOngoingProjects(null);
        departmentDTO.setEndedProjects(null);
        this.updateDepartment(departmentDTO);
        System.out.println("Deleting department");
        Department department = this.delete(mapper.fromDTO(departmentDTO));
        System.out.println("Department was successfully deleted");
        return mapper.toDTO(department);
    }

    //todo
    private DepartmentDTO checkDepartment(DepartmentDTO departmentDTO) {
//        if (!departmentDTO.getBoss().getActiveProjects().isEmpty())
//            return null;
        return departmentDTO;
    }
}
