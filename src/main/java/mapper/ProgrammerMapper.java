package mapper;

import dao.Programmer;
import dto.ProgrammerDTO;
/**
 * Clase de mapeo de datos de Programmer y ProgrammerDTO. Implementa interfaz BaseMapper
 */
public class ProgrammerMapper extends BaseMapper<Programmer, ProgrammerDTO> {
    /**
     * metodo para mapear desde un ProgrammerDto a Programmer
     * @param item ProgrammerDTO
     * @return Programmer
     */
    @Override
    public Programmer fromDTO(ProgrammerDTO item) {
        return new Programmer(
                item.getId(),
                item.getName(),
                item.getMail(),
                item.getEntryDate(),
                item.getDepartment(),
                item.getSalary(),
                item.getPassword(),
                item.getTechnologies(),
                item.getActiveProjects(),
                item.getCommits(),
                item.getIssues(),
                item.getLogins()
                );
    }
    /**
     * metodo para mapear desde un Programmer a ProgrammerDto
     * @param item Programmer
     * @return ProgrammerDTO
     */
    @Override
    public ProgrammerDTO toDTO(Programmer item) {
        return new ProgrammerDTO(
                item.getId(),
                item.getName(),
                item.getMail(),
                item.getEntryDate(),
                item.getDepartment(),
                item.getSalary(),
                item.getPassword(),
                item.getTechnologies(),
                item.getActiveProjects(),
                item.getCommits(),
                item.getIssues(),
                item.getLogins()
                );
    }
}
