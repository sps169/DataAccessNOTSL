package mapper;

import dao.Issue;
import dto.IssueDTO;

public class IssueMapper extends BaseMapper<Issue, IssueDTO> {
    @Override
    public Issue fromDTO(IssueDTO item) {
        return new Issue(item.getId(),item.getTitle(),item.getText(),item.getDate(),item.getProgrammers(),
                item.getProject(), item.getRepository());
    }

    @Override
    public IssueDTO toDTO(Issue item) {
        return new IssueDTO(item.getId(),item.getTitle(),item.getText(),item.getDate(),item.getProgrammers(),
                item.getProject(), item.getRepository());
    }
}
