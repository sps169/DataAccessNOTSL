package controller;

import dto.CommitDTO;
import service.CommitService;

import java.util.List;

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

    public List<CommitDTO> getAllCommits() {
        try {
            return service.getAllCommits();
        }catch (Exception ex) {
            System.err.println("Error retrieving all commits "+ ex.getMessage());
            return null;
        }
    }

    public CommitDTO getCommitById(String id) {
        try {
            return service.getCommitById(id);
        }catch (Exception ex) {
            System.err.println("Error retreiving commit with id: '" + id + "'" + ex.getMessage());
            return null;
        }
    }

    public CommitDTO insertCommit(CommitDTO commitDTO) {
        try {
            return service.insertCommit(commitDTO);
        }catch (Exception ex) {
            System.err.println("Error inserting commit with id "+ commitDTO.getId() +" into database: " + ex.getMessage());
            return null;
        }
    }

    public CommitDTO updateCommit(CommitDTO commitDTO) {
        try {
            return service.updateCommit(commitDTO);
        }catch (Exception ex) {
            System.err.println("Error updating commit with id"+ commitDTO.getId());
            return null;
        }
    }

    public CommitDTO deleteCommit(CommitDTO commitDTO) {
        try {
            return service.deleteCommit(commitDTO);
        }catch (Exception ex) {
            System.err.println("Error updating commit with id"+ commitDTO.getId());
            return null;
        }
    }
}
