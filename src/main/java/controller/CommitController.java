package controller;

import dto.CommitDTO;
import service.CommitService;

import java.util.List;

import static controller.BaseController.jsonMapper;

public class CommitController {
    private static CommitController controller;
    private final CommitService service;

    private CommitController (CommitService service) {
        this.service = service;
    }

    public static CommitController getInstance(CommitService service) {
        if (controller == null) {
            controller = new CommitController(service);
        }
        return controller;
    }

    public String getAllCommits() {
        try {
            return jsonMapper.writeValueAsString(service.getAllCommits());
        }catch (Exception ex) {
            System.err.println("Error retrieving all commits "+ ex.getMessage());
            return null;
        }
    }

    public String getCommitById(String id) {
        try {
            return jsonMapper.writeValueAsString(service.getCommitById(id));
        }catch (Exception ex) {
            System.err.println("Error retreiving commit with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public String insertCommit(CommitDTO commitDTO) {
        try {
            return jsonMapper.writeValueAsString(service.insertCommit(commitDTO));
        }catch (Exception ex) {
            System.err.println("Error inserting commit with id "+ commitDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public String updateCommit(CommitDTO commitDTO) {
        try {
            return jsonMapper.writeValueAsString(service.updateCommit(commitDTO));
        }catch (Exception ex) {
            System.err.println("Error updating commit with id"+ commitDTO.getId() + ex.getMessage());
            return null;
        }
    }

    public String deleteCommit(CommitDTO commitDTO) {
        try {
            return jsonMapper.writeValueAsString(service.deleteCommit(commitDTO));
        }catch (Exception ex) {
            System.err.println("Error deleting commit with id"+ commitDTO.getId() + ex.getMessage());
            return null;
        }
    }
}
