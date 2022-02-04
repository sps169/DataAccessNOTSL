package controller;

import dto.IssueDTO;
import service.IssueService;

import java.util.List;

import static controller.BaseController.jsonMapper;

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

    public String getAllIssues() {
        try {
            return jsonMapper.writeValueAsString(service.getAllIssues());
        }catch (Exception ex) {
            System.err.println("Error retrieving all issues "+ ex.getMessage());
            return null;
        }
    }

    public String getIssueById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getIssueById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving issue with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public String insertIssue(IssueDTO issueDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertIssue(issueDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting issue with id "+ issueDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public String updateIssue(IssueDTO issueDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateIssue(issueDTO));
        }catch (Exception ex) {
            System.err.println("Error updating issue with id"+ issueDTO.getId() + ex.getMessage());
            return null;
        }
    }

    public String deleteIssue(IssueDTO issueDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteIssue(issueDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting issue with id"+ issueDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
