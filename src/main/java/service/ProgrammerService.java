package service;

import controller.DepartmentController;
import dao.Department;
import dao.Login;
import dao.Programmer;
import dao.Project;
import dto.LoginDTO;
import dto.ProgrammerDTO;
import manager.HibernateController;
import mapper.LoginMapper;
import mapper.ProgrammerMapper;
import repository.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Servicio para la entidad programmer. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class ProgrammerService extends BaseService<Programmer, String, ProgrammerRepository> {

    private ProgrammerMapper mapper = new ProgrammerMapper();
    public ProgrammerService(ProgrammerRepository repository) {
        super(repository);
    }

    /**
     * Obtener todas las entidades programmer
     * @return Lista con todos los programmers
     */
    public List<ProgrammerDTO> getAllProgrammers() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    /**
     * Obtener entidad programmer segun la id
     * @param id String id del programmer
     * @return Lista con todos los programmers
     */
    public ProgrammerDTO getProgrammerById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    /**
     * inserta un programmer en la base de datos
     * @param programmerDTO programmer a insertar
     * @return Lista de programmer correcta si la operación se ha realizado, null en caso de no completarse
     */
    public ProgrammerDTO insertProgrammer(ProgrammerDTO programmerDTO) throws Exception {
        if (checkProgrammer(programmerDTO) != null) {
            Programmer programmer = this.save(mapper.fromDTO(programmerDTO));
            return mapper.toDTO(programmer);
        }else {
            throw new Exception(
                    "Error inserting programmer with id " + programmerDTO.getId()+" " +
                            ": Programmer doesn't meet requirements"
            );

        }
    }

    /**
     * actualiza un programmer en la base de datos
     * @param programmerDTO a actualizar
     * @return Lista de programmer si la operacion se realiza, null en caso de no realizarse
     */
    public ProgrammerDTO updateProgrammer(ProgrammerDTO programmerDTO) throws Exception {
        if (checkProgrammer(programmerDTO) != null) {
            Programmer programmer = this.update(mapper.fromDTO(programmerDTO));
            return mapper.toDTO(programmer);
        }else {
            throw new Exception(
                    "Error updating programmer with id " + programmerDTO.getId()+" " +
                            ": Programmer doesn't meet requirements"
            );

        }
    }

    /**
     * elimina un programmer de la base de datos. Se asegura de quitar sus relaciones en otras
     * entidades con las que pueda estar relacionada.
     * @param programmerDTO a eliminar
     * @return Lista de programmer si la operacion se realiza, null en caso de no realizars
     */
    public ProgrammerDTO deleteProgrammer(ProgrammerDTO programmerDTO) throws Exception {
        ProjectRepository projectRepository = new ProjectRepository(HibernateController.getInstance());
        if (programmerDTO.getActiveProjects() != null) {
            programmerDTO.getActiveProjects().forEach(p -> {
                if (p.getBoss().getId().equals(programmerDTO.getId())) {
                    p.setBoss(null);
                    try {
                        projectRepository.update(p);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        programmerDTO.setActiveProjects(null);

        DepartmentRepository departmentRepository = new DepartmentRepository(HibernateController.getInstance());
        try {
            Department department = departmentRepository.getById(programmerDTO.getDepartment().getId());
            if (department.getBoss().getId().equals(programmerDTO.getId()))
                department.setBoss(null);
            department.getHistoricBosses().remove(mapper.fromDTO(programmerDTO));
        }catch (Exception ex) {
            System.out.println("El programador no tiene departamento!");
        }
        programmerDTO.setDepartment(null);

        CommitRepository commitRepository = new CommitRepository(HibernateController.getInstance());
        programmerDTO.getCommits().forEach(c ->{
            c.setProgrammer(null);
            try {
                commitRepository.update(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        programmerDTO.setCommits(null);

        IssueRepository issueRepository = new IssueRepository(HibernateController.getInstance());
        programmerDTO.getIssues().forEach(i -> {
            i.getProgrammers().remove(mapper.fromDTO(programmerDTO));
            try {
                issueRepository.update(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        programmerDTO.setIssues(null);

        LoginService loginService = new LoginService(new LoginRepository(HibernateController.getInstance()));
        LoginMapper loginMapper = new LoginMapper();
        programmerDTO.getLogins().forEach(l -> {
            try {
                loginService.deleteLogin(loginMapper.toDTO(l));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        programmerDTO.setLogins(null);
        updateProgrammer(programmerDTO);
        Programmer programmer = this.delete(mapper.fromDTO(programmerDTO));
        return mapper.toDTO(programmer);
    }

    private ProgrammerDTO checkProgrammer(ProgrammerDTO programmerDTO) {
//
//        if (!programmerDTO.getActiveProjects().isEmpty()) {
//            DepartmentController departmentController = DepartmentController.getInstance(
//                    new DepartmentService(
//                            new DepartmentRepository(
//                                    HibernateController.getInstance())));
//            if(departmentController.getAllDepartments().stream()
//                    .anyMatch(d -> d.getBoss().getId().equals(programmerDTO.getId())))
//                return null;
//        }
        return programmerDTO;
    }
}
