package service;

import dao.Repository;
import dto.RepositoryDTO;
import mapper.RepositoryMapper;
import repository.RepositoryRepository;

import java.util.List;

public class RepositoryService extends BaseService<Repository,String,RepositoryRepository>{
    private RepositoryMapper mapper = new RepositoryMapper();
    public RepositoryService(RepositoryRepository repository) {
        super(repository);
    }

    public List<RepositoryDTO> getAllRepositorys() throws Exception {
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
        Repository repository = this.delete(mapper.fromDTO(repositoryDTO));
        return mapper.toDTO(repository);
    }

    private RepositoryDTO checkRepository(RepositoryDTO repositoryDTO) {
        return null;
    }
}
