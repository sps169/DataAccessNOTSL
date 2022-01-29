package mapper;

import dao.Programmer;
import dto.ProgrammerDTO;

public class ProgrammerMapper extends BaseMapper<Programmer, ProgrammerDTO> {
    @Override
    public Programmer fromDTO(ProgrammerDTO item) {
        return new Programmer(item.getId(),item.getName(),item.getMail(),item.getEntryDate(),item.getDepartment(),
                item.getSalary(),item.getPassword(),item.getActiveProjects(),item.getCommits(),item.getIssues(),
                item.getLogins());
    }

    @Override
    public ProgrammerDTO toDTO(Programmer item) {
        return new ProgrammerDTO(item.getId(),item.getName(),item.getMail(),item.getEntryDate(),item.getDepartment(),
                item.getSalary(),item.getPassword(),item.getActiveProjects(),item.getCommits(),item.getIssues(),
                item.getLogins());
    }
}
