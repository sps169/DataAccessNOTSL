package mapper;

import dao.Project;
import dto.ProjectDTO;
/**
 * Clase de mapeo de datos de Project y ProjectDTO. Implementa interfaz BaseMapper
 */
public class ProjectMapper extends BaseMapper<Project, ProjectDTO> {
    /**
     * metodo para mapear desde un ProjectDto a Project
     * @param item ProjectDTO
     * @return Project
     */
    @Override
    public Project fromDTO(ProjectDTO item) {
        return new Project(item.getId(),item.getName(),item.getBudget(),item.getStartDate(),item.getEndDate(),
                item.getTechnologies(), item.getRepository(),item.getBoss());
    }
    /**
     * metodo para mapear desde un Project a ProjectDto
     * @param item Project
     * @return ProjectDTO
     */
    @Override
    public ProjectDTO toDTO(Project item) {
        return new ProjectDTO(item.getId(),item.getName(),item.getBudget(),item.getStartDate(),item.getEndDate(),
                item.getTechnologies(), item.getRepository(),item.getBoss());
    }
}
