package service;

import controller.DepartmentController;
import dao.Department;
import dao.Programmer;
import dao.Project;
import dao.Repository;
import dto.ProjectDTO;
import manager.HibernateController;
import mapper.DepartmentMapper;
import mapper.ProjectMapper;

import mapper.RepositoryMapper;
import repository.DepartmentRepository;
import repository.ProgrammerRepository;
import repository.ProjectRepository;
import repository.RepositoryRepository;

import java.util.List;
import java.util.Set;

/**
 * Servicio para la entidad proyecto. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class ProjectService extends BaseService<Project, String, ProjectRepository> {
    public ProjectService(ProjectRepository repository) {
        super(repository);
    }

    private final ProjectMapper mapper = new ProjectMapper();

    /**
     * Obtener todas las entidades proyecto
     * @return Lista con todos los proyectos
     */
    public List<ProjectDTO> getAllProjects() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    /**
     * Obtener entidad proyecto segun la id
     * @param id String id del proyecto
     * @return Lista con todos los proyectos
     */
    public ProjectDTO getProjectById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    /**
     * inserta un proyecto en la base de datos
     * @param projectDTO proyecto a insertar
     * @return Lista de proyecto correcta si la operación se ha realizado, null en caso de no completarse
     */
    public ProjectDTO insertProject(ProjectDTO projectDTO) throws Exception {
        Project project = this.save(mapper.fromDTO(projectDTO));
        return mapper.toDTO(project);
    }

    /**
     * actualiza un proyecto en la base de datos
     * @param projectDTO a actualizar
     * @return Lista de proyecto si la operacion se realiza, null en caso de no realizarse
     */
    public ProjectDTO updateProject(ProjectDTO projectDTO) throws Exception {
        Project project = this.update(mapper.fromDTO(projectDTO));
        return mapper.toDTO(project);
    }

    /**
     * elimina un proyecto de la base de datos. Se asegura de quitar sus relaciones en otras
     * entidades con las que pueda estar relacionada.
     * @param projectDTO a eliminar
     * @return Lista de proyecto si la operacion se realiza, null en caso de no realizars
     */
    public ProjectDTO deleteProject(ProjectDTO projectDTO) throws Exception {
        ProgrammerRepository programmerRepository = new ProgrammerRepository(HibernateController.getInstance());
        System.out.println("Fetching projects boss for removing project");
        Programmer boss = programmerRepository.getById(projectDTO.getBoss().getId());
        boss.getActiveProjects().stream()
                .filter(p -> p.getId().equals(projectDTO.getId()))
                .findAny()
                .ifPresent(boss.getActiveProjects()::remove);
        System.out.println("Updating now projectless boss");
        programmerRepository.update(boss);
        projectDTO.setBoss(null);
        this.updateProject(projectDTO);

        programmerRepository.findAll().forEach(p -> {
            p.getActiveProjects().stream().forEach(pr -> {
                if (pr.getId().equals(projectDTO.getId())) {
                    p.getActiveProjects().remove(pr);
                    try {
                        programmerRepository.update(p);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        DepartmentService departmentService = new DepartmentService(new DepartmentRepository(HibernateController.getInstance()));
        DepartmentMapper departmentMapper = new DepartmentMapper();
        departmentService.findAll().stream().filter(d -> {
            Set<Project> projects = d.getOngoingProjects();
            projects.addAll(d.getEndedProjects());
            return projects.stream().anyMatch(p -> p.getId().equals(projectDTO.getId()));
        }).forEach(d -> {
            d.getOngoingProjects().stream().filter(p -> p.getId().equals(projectDTO.getId())).findAny().ifPresent(oP -> {
                d.getOngoingProjects().remove(oP);
                try {
                    departmentService.updateDepartment(departmentMapper.toDTO(d));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            d.getEndedProjects().stream().filter(p -> p.getId().equals(projectDTO.getId())).findAny().ifPresent(eP -> {
                d.getOngoingProjects().remove(eP);
                try {
                    departmentService.updateDepartment(departmentMapper.toDTO(d));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        RepositoryService repositoryService = new RepositoryService (new RepositoryRepository(HibernateController.getInstance()));
        RepositoryMapper repositoryMapper = new RepositoryMapper();
        try {
            System.out.println("Fetching repository from project");
            Repository repository = repositoryService.getById(projectDTO.getRepository().getId());
            System.out.println("Deleting repository from project");
            repositoryService.deleteRepository(repositoryMapper.toDTO(repository));
            System.out.println("Repository from project was successfully erased");
        }catch (Exception ex) {
            System.out.println("Could not erase repository from project");
            ex.printStackTrace();
        }
        projectDTO.setRepository(null);
        this.updateProject(projectDTO);

        System.out.println("Deleting project");
        Project project = this.delete(mapper.fromDTO(projectDTO));
        System.out.println("Project successfully deleted");
        return mapper.toDTO(project);
    }

    private ProjectDTO checkProject(ProjectDTO projectDTO) {
//        if (!projectDTO.getBoss().getActiveProjects().isEmpty())
//            return null;
//        DepartmentController departmentController = DepartmentController.getInstance(
//                new DepartmentService(
//                        new DepartmentRepository(
//                                HibernateController.getInstance())));
//        if(departmentController.getAllDepartments().stream()
//                .anyMatch(d -> d.getBoss().getId().equals(projectDTO.getBoss().getId())))
//            return null;
        return projectDTO;
    }
}
