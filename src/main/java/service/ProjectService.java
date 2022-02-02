package service;

import controller.DepartmentController;
import dao.Project;
import dto.ProjectDTO;
import manager.HibernateController;
import mapper.ProjectMapper;

import repository.DepartmentRepository;
import repository.ProjectRepository;

import java.util.List;

public class ProjectService extends BaseService<Project, String, ProjectRepository> {
    protected ProjectService(ProjectRepository repository) {
        super(repository);
    }

    private ProjectMapper mapper = new ProjectMapper();

    public List<ProjectDTO> getAllProjects() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    public ProjectDTO getProjectById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    public ProjectDTO insertProject(ProjectDTO projectDTO) throws Exception {
        Project project = this.save(mapper.fromDTO(projectDTO));
        return mapper.toDTO(project);
    }

    public ProjectDTO updateProject(ProjectDTO projectDTO) throws Exception {
        Project project = this.update(mapper.fromDTO(projectDTO));
        return mapper.toDTO(project);
    }

    public ProjectDTO deleteProject(ProjectDTO projectDTO) throws Exception {
        Project project = this.delete(mapper.fromDTO(projectDTO));
        return mapper.toDTO(project);
    }

    private ProjectDTO checkProject(ProjectDTO projectDTO) {
        if (!projectDTO.getBoss().getActiveProjects().isEmpty())
            return null;
        DepartmentController departmentController = DepartmentController.getInstance(
                new DepartmentService(
                        new DepartmentRepository(
                                HibernateController.getInstance())));
        if(departmentController.getAllDepartments().stream()
                .anyMatch(d -> d.getBoss().getId().equals(projectDTO.getBoss().getId())))
            return null;
        return projectDTO;
    }
}
