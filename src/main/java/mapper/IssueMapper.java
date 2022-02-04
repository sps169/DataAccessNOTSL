package mapper;

import dao.Issue;
import dto.IssueDTO;
/**
 * Clase de mapeo de datos de Issue y IssueDTO. Implementa interfaz BaseMapper
 */
public class IssueMapper extends BaseMapper<Issue, IssueDTO> {
    /**
     * metodo para mapear desde un IssueDto a Issue
     * @param item IssueDTO
     * @return Issue
     */
    @Override
    public Issue fromDTO(IssueDTO item) {
        return new Issue(item.getId(),item.getTitle(),item.getText(),item.getDate(),item.getProgrammers(),
                item.getProject(), item.getRepository());
    }
    /**
     * metodo para mapear desde un Issue a IssueDto
     * @param item Issue
     * @return IssueDTO
     */
    @Override
    public IssueDTO toDTO(Issue item) {
        return new IssueDTO(item.getId(),item.getTitle(),item.getText(),item.getDate(),item.getProgrammers(),
                item.getProject(), item.getRepository());
    }
}
