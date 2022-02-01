package service;

import dao.Login;
import dao.Programmer;
import dto.LoginDTO;
import dto.ProgrammerDTO;
import mapper.ProgrammerMapper;
import repository.ProgrammerRepository;

import java.util.List;

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
        Programmer programmer = this.save(mapper.fromDTO(programmerDTO));
        return mapper.toDTO(programmer);
    }

    public ProgrammerDTO updateProgrammer(ProgrammerDTO programmerDTO) throws Exception {
        Programmer programmer = this.update(mapper.fromDTO(programmerDTO));
        return mapper.toDTO(programmer);
    }

    public ProgrammerDTO deleteProgrammer(ProgrammerDTO programmerDTO) throws Exception {
        Programmer programmer = this.delete(mapper.fromDTO(programmerDTO));
        return mapper.toDTO(programmer);
    }
}
