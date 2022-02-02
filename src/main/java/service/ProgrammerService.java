package service;

import controller.DepartmentController;
import dao.Department;
import dao.Login;
import dao.Programmer;
import dto.LoginDTO;
import dto.ProgrammerDTO;
import manager.HibernateController;
import mapper.ProgrammerMapper;
import repository.DepartmentRepository;
import repository.ProgrammerRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammerService extends BaseService<Programmer, String, ProgrammerRepository> {

    private ProgrammerMapper mapper = new ProgrammerMapper();
    public ProgrammerService(ProgrammerRepository repository) {
        super(repository);
    }

    public List<ProgrammerDTO> getAllProgrammers() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    public ProgrammerDTO getProgrammerById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

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

    public ProgrammerDTO deleteProgrammer(ProgrammerDTO programmerDTO) throws Exception {
        Programmer programmer = this.delete(mapper.fromDTO(programmerDTO));
        return mapper.toDTO(programmer);
    }

    private ProgrammerDTO checkProgrammer(ProgrammerDTO programmerDTO) {
        boolean isCorrect = true;
        if (!programmerDTO.getActiveProjects().isEmpty()) {
            DepartmentController departmentController = DepartmentController.getInstance(
                    new DepartmentService(
                            new DepartmentRepository(
                                    HibernateController.getInstance())));
            if(departmentController.getAllDepartments().stream()
                    .anyMatch(d -> d.getBoss().getId().equals(programmerDTO.getId())))
                isCorrect = false;
        }
        if (isCorrect) return programmerDTO;
        else return null;
    }
}
