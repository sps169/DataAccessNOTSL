package service;

import dao.Commit;
import dao.Issue;
import dao.Project;
import dao.Repository;
import dto.RepositoryDTO;
import manager.HibernateController;
import mapper.CommitMapper;
import mapper.IssueMapper;
import mapper.RepositoryMapper;
import repository.CommitRepository;
import repository.IssueRepository;
import repository.ProjectRepository;
import repository.RepositoryRepository;

import java.util.List;
import java.util.Set;

public class RepositoryService extends BaseService<Repository,String,RepositoryRepository>{
    private RepositoryMapper mapper = new RepositoryMapper();
    public RepositoryService(RepositoryRepository repository) {
        super(repository);
    }

    public List<RepositoryDTO> getAllRepositories() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    public RepositoryDTO getRepositoryById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    public RepositoryDTO insertRepository(RepositoryDTO repositoryDTO) throws Exception {
        if (checkRepository(repositoryDTO) != null) {
            Repository repository = this.save(mapper.fromDTO(repositoryDTO));
            return mapper.toDTO(repository);
        }else {
            throw new Exception(
                    "Error inserting repository with id " + repositoryDTO.getId()+" " +
                            ": Repository doesn't meet requirements"
            );

        }
    }

    public RepositoryDTO updateRepository(RepositoryDTO repositoryDTO) throws Exception {
        if (checkRepository(repositoryDTO) != null) {
            Repository repository = this.update(mapper.fromDTO(repositoryDTO));
            return mapper.toDTO(repository);
        }else {
            throw new Exception(
                    "Error updating repository with id " + repositoryDTO.getId()+" " +
                            ": Repository doesn't meet requirements"
            );

        }
    }

    public RepositoryDTO deleteRepository(RepositoryDTO repositoryDTO) throws Exception {
        ProjectRepository projectRepo = new ProjectRepository(HibernateController.getInstance());
        System.out.println("Fetching repository projects");
        Project project = projectRepo.getById(repositoryDTO.getProject().getId());
        project.setRepository(null);
        System.out.println("Updating project without repository");
        projectRepo.update(project);
        repositoryDTO.setProject(null);
        this.updateRepository(repositoryDTO);

        System.out.println("Erasing commits from repository");
        CommitService commitService = new CommitService(new CommitRepository(HibernateController.getInstance()));
        CommitMapper commitMapper = new CommitMapper();
        repositoryDTO.getCommits().forEach(c ->{
            try {
                System.out.println("Fetching repository's commit");
                Commit commit = commitService.getById(c.getId());
                System.out.println("Deleting repository's commit");
                commitService.deleteCommit(commitMapper.toDTO(commit));
                System.out.println("repository's commit successfully deleted");
            } catch (Exception e) {
                System.out.println("repository's commit couldn't be deleted");
                e.printStackTrace();
            }
        });
        repositoryDTO.setCommits(null);
        this.updateRepository(repositoryDTO);

        System.out.println("Erasing issues from repository");
        IssueService issueService = new IssueService(new IssueRepository(HibernateController.getInstance()));
        IssueMapper issueMapper = new IssueMapper();
        Set<Issue> issues = repositoryDTO.getIssues();
        issues.forEach(i -> {
            try {
                System.out.println("Fetching repository's issue");
                Issue issue = issueService.getById(i.getId());
                System.out.println("Deleting repository's issue");
                issueService.deleteIssue(issueMapper.toDTO(issue));
                System.out.println("repository's issue successfully deleted");
            } catch (Exception e) {
                System.out.println("repository's issue couldn't be deleted");
                e.printStackTrace();
            }
        });
        repositoryDTO.setIssues(null);
        this.updateRepository(repositoryDTO);

        Repository repository = this.delete(mapper.fromDTO(repositoryDTO));
        return mapper.toDTO(repository);
    }

    private RepositoryDTO checkRepository(RepositoryDTO repositoryDTO) {
        return repositoryDTO;
    }
}
