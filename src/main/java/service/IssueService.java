package service;


import dao.Issue;
import dao.Programmer;
import dao.Project;
import dao.Repository;
import dto.IssueDTO;
import manager.HibernateController;
import mapper.IssueMapper;
import repository.IssueRepository;
import repository.ProgrammerRepository;
import repository.ProjectRepository;
import repository.RepositoryRepository;

import java.util.List;

public class IssueService extends BaseService<Issue,String, IssueRepository> {
    private final IssueMapper mapper = new IssueMapper();
    public IssueService(IssueRepository repository) {
        super(repository);
    }

    public List<IssueDTO> getAllIssues() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    public IssueDTO getIssueById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    public IssueDTO insertIssue(IssueDTO issueDTO) throws Exception {
        if (checkIssue(issueDTO) != null) {
            Issue issue = this.save(mapper.fromDTO(issueDTO));
            return mapper.toDTO(issue);
        }else {
            throw new Exception(
                    "Error inserting issue with id " + issueDTO.getId()+" " +
                            ": Issue doesn't meet requirements"
            );

        }
    }

    public IssueDTO updateIssue(IssueDTO issueDTO) throws Exception {
        if (checkIssue(issueDTO) != null) {
            Issue issue = this.update(mapper.fromDTO(issueDTO));
            return mapper.toDTO(issue);
        }else {
            throw new Exception(
                    "Error updating issue with id " + issueDTO.getId()+" " +
                            ": Issue doesn't meet requirements"
            );

        }
    }

    public IssueDTO deleteIssue(IssueDTO issueDTO) throws Exception {
        ProgrammerRepository programmerRepository = new ProgrammerRepository(HibernateController.getInstance());
        System.out.println("Fetching issue's programmers for issue delete");
        issueDTO.getProgrammers().forEach(p -> {
            try {
                Programmer programmer = programmerRepository.getById(p.getId());
                programmer.getIssues().stream()
                        .filter(i -> i.getId().equals(issueDTO.getId()))
                        .findAny()
                        .ifPresent(programmer.getIssues()::remove);
                programmerRepository.update(programmer);
                System.out.println("Updated now issueless programmer");
            } catch (Exception e) {
                System.out.println("Could not update issueless programmer");
                e.printStackTrace();
            }
        });
        issueDTO.setProgrammers(null);
        this.updateIssue(issueDTO);

        RepositoryRepository repositoryRepository = new RepositoryRepository(HibernateController.getInstance());
        System.out.println("Fetching issue's repository to erase issue");
        Repository repository = repositoryRepository.getById(issueDTO.getRepository().getId());
        repository.getIssues().remove(mapper.fromDTO(issueDTO));
        System.out.println("Updating now issueless repository");
        repositoryRepository.update(repository);
        issueDTO.setRepository(null);
        this.updateIssue(issueDTO);

        System.out.println("Deleting issue");
        Issue issue = this.delete(mapper.fromDTO(issueDTO));
        System.out.println("Issue deleted successfully");
        return mapper.toDTO(issue);
    }

    private IssueDTO checkIssue(IssueDTO issueDTO) {
        return issueDTO;
    }
}
