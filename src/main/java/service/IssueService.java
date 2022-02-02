package service;


import dao.Issue;
import dto.IssueDTO;
import mapper.IssueMapper;
import repository.IssueRepository;

import java.util.List;

public class IssueService extends BaseService<Issue,String, IssueRepository> {
    private IssueMapper mapper = new IssueMapper();
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
        Issue issue = this.delete(mapper.fromDTO(issueDTO));
        return mapper.toDTO(issue);
    }

    private IssueDTO checkIssue(IssueDTO issueDTO) {
        return null;
    }
}
