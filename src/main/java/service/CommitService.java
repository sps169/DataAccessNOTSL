package service;

import controller.DepartmentController;
import dao.*;
import dto.CommitDTO;
import manager.HibernateController;
import mapper.CommitMapper;
import repository.*;

import java.util.List;

/**
 * Servicio para la entidad commit. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class CommitService extends BaseService<Commit, String, CommitRepository> {

    /**
     * Constructor con dependencia de repositorio
     * @param repository inyección de dependencia de repositorio
     */
    public CommitService(CommitRepository repository) {
        super(repository);
    }

    private final CommitMapper mapper = new CommitMapper();

    /**
     * Obtener todas las entidades commit
     * @return Lista con todos los commits
     */
    public List<CommitDTO> getAllCommits() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    /**
     * Obtener entidad commit segun la id
     * @param id String id del commit
     * @return Lista con todos los commits
     */
    public CommitDTO getCommitById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    /**
     * inserta un commit en la base de datos
     * @param commitDTO commit a insertar
     * @return Lista de commit correcta si la operación se ha realizado, null en caso de no completarse
     */
    public CommitDTO insertCommit(CommitDTO commitDTO) throws Exception {
        Commit commit = this.save(mapper.fromDTO(commitDTO));
        return mapper.toDTO(commit);
    }

    /**
     * actualiza un commit en la base de datos
     * @param commitDTO a actualizar
     * @return Lista de commit si la operacion se realiza, null en caso de no realizarse
     */
    public CommitDTO updateCommit(CommitDTO commitDTO) throws Exception {
        Commit commit = this.update(mapper.fromDTO(commitDTO));
        return mapper.toDTO(commit);
    }

    /**
     * elimina un commit de la base de datos. Se asegura de quitar sus relaciones en otras
     * entidades con las que pueda estar relacionada.
     * @param commitDTO a eliminar
     * @return Lista de commit si la operacion se realiza, null en caso de no realizars
     */
    public CommitDTO deleteCommit(CommitDTO commitDTO) throws Exception {
        RepositoryRepository repositoryRepository = new RepositoryRepository(HibernateController.getInstance());
        System.out.println("Fetching commit's repository to erase commit");
        Repository repository = repositoryRepository.getById(commitDTO.getRepository().getId());
        CommitMapper commitMapper = new CommitMapper();
        repository.getCommits().remove(commitMapper.fromDTO(commitDTO));
        System.out.println("Updating now commitless repository");
        repositoryRepository.update(repository);
        commitDTO.setRepository(null);
        this.updateCommit(commitDTO);

        ProgrammerRepository programmerRepository = new ProgrammerRepository(HibernateController.getInstance());
        System.out.println("Fetching commit's programmer to erase commit");
        Programmer programmer = programmerRepository.getById(commitDTO.getProgrammer().getId());
        programmer.getCommits().remove(commitMapper.fromDTO(commitDTO));
        System.out.println("Updating now commitless programmer");
        programmerRepository.update(programmer);
        commitDTO.setProgrammer(null);
        this.updateCommit(commitDTO);

        System.out.println("Deleting commit");
        Commit commit = this.delete(mapper.fromDTO(commitDTO));
        System.out.println("Commit successfully deleted");
        return mapper.toDTO(commit);
    }

    private CommitDTO checkCommit(CommitDTO commitDTO) {
//        if (!commitDTO.getProgrammer().getActiveProjects().contains(commitDTO.getProject()))
//            return null;
        return commitDTO;
    }
}
