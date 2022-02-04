package service;

import controller.DepartmentController;
import dao.*;
import dto.CommitDTO;
import manager.HibernateController;
import mapper.CommitMapper;
import repository.*;

import java.util.List;

public class CommitService extends BaseService<Commit, String, CommitRepository> {
    public CommitService(CommitRepository repository) {
        super(repository);
    }

    private final CommitMapper mapper = new CommitMapper();

    public List<CommitDTO> getAllCommits() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    public CommitDTO getCommitById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    public CommitDTO insertCommit(CommitDTO commitDTO) throws Exception {
        Commit commit = this.save(mapper.fromDTO(commitDTO));
        return mapper.toDTO(commit);
    }

    public CommitDTO updateCommit(CommitDTO commitDTO) throws Exception {
        Commit commit = this.update(mapper.fromDTO(commitDTO));
        return mapper.toDTO(commit);
    }

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
