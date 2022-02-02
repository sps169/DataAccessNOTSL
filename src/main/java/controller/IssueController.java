package controller;

import dto.IssueDTO;
import service.IssueService;

import java.util.List;

public class IssueController {
    private static IssueController controller;
    private final IssueService service;

    private IssueController (IssueService service) {
        this.service = service;
    }

    public static IssueController getInstance(IssueService service) {
        if (controller == null) {
            controller = new IssueController(service);
        }
        return controller;
    }

    public List<IssueDTO> getAllIssues() {
        try {
            return service.getAllIssues();
        }catch (Exception ex) {
            System.err.println("Error retrieving all issues "+ ex.getMessage());
            return null;
        }
    }

    public IssueDTO getIssueById(String id) {
        try {
            return service.getIssueById(id);
        }catch (Exception ex) {
            System.err.println("Error retreiving issue with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public IssueDTO insertIssue(IssueDTO issueDTO) {
        try {
            return service.insertIssue(issueDTO);
        }catch (Exception ex) {
            System.err.println("Error inserting issue with id "+ issueDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public IssueDTO updateIssue(IssueDTO issueDTO) {
        try {
            return service.updateIssue(issueDTO);
        }catch (Exception ex) {
            System.err.println("Error updating issue with id"+ issueDTO.getId());
            return null;
        }
    }

    public IssueDTO deleteIssue(IssueDTO issueDTO) {
        try {
            return service.deleteIssue(issueDTO);
        }catch (Exception ex) {
            System.err.println("Error updating issue with id"+ issueDTO.getId());
            return null;
        }
    }
}
