package service;

import controller.DepartmentController;
import dao.Commit;
import dto.CommitDTO;
import manager.HibernateController;
import mapper.CommitMapper;
import repository.DepartmentRepository;
import repository.CommitRepository;

import java.util.List;

public class CommitService extends BaseService<Commit, String, CommitRepository> {
    protected CommitService(CommitRepository repository) {
        super(repository);
    }

    private CommitMapper mapper = new CommitMapper();

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
        Commit commit = this.delete(mapper.fromDTO(commitDTO));
        return mapper.toDTO(commit);
    }

    private CommitDTO checkCommit(CommitDTO commitDTO) {
        if (!commitDTO.getProgrammer().getActiveProjects().contains(commitDTO.getProject()))
            return null;
        return commitDTO;
    }
}
