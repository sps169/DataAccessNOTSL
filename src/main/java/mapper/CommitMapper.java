package mapper;

import dao.Commit;
import dto.CommitDTO;
/**
 * Clase de mapeo de datos de Commit y CommitDTO. Implementa interfaz BaseMapper
 */
public class CommitMapper extends BaseMapper<Commit, CommitDTO> {
    /**
     * metodo para mapear desde un CommitDto a Commit
     * @param item CommitDTO
     * @return Commit
     */
    @Override
    public Commit fromDTO(CommitDTO item) {
        return new Commit(item.getId(),item.getTitle(),item.getText(),item.getDate(),item.getRepository(),
                item.getIssue(), item.getProject(),item.getProgrammer());
    }
    /**
     * metodo para mapear desde un Commit a CommitDto
     * @param item CommitDTO
     * @return CommitDto
     */
    @Override
    public CommitDTO toDTO(Commit item) {
        return new CommitDTO(item.getId(),item.getTitle(),item.getText(),item.getDate(),item.getRepository(),
                item.getIssue(), item.getProject(),item.getProgrammer());
    }
}
