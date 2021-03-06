package mapper;

import dao.Repository;
import dto.RepositoryDTO;
/**
 * Clase de mapeo de datos de Repository y RepositoryDTO. Implementa interfaz BaseMapper
 */
public class RepositoryMapper extends BaseMapper<Repository, RepositoryDTO> {
    /**
     * metodo para mapear desde un RepositoryDto a Repository
     * @return Repository
     */
    @Override
    public Repository fromDTO(RepositoryDTO item) {
        return new Repository(item.getId(),item.getName(),item.getCreationDate(),item.getProject(),item.getCommits(),
                item.getIssues());
    }

    @Override
    public RepositoryDTO toDTO(Repository item) {
        return new RepositoryDTO(item.getId(),item.getName(),item.getCreationDate(),item.getProject(),item.getCommits(),
                item.getIssues());
    }
}
