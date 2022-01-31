//package mapper;
//
//import dao.Project;
//import dto.ProjectDTO;
//
//public class ProjectMapper extends BaseMapper<Project, ProjectDTO> {
//    @Override
//    public Project fromDTO(ProjectDTO item) {
//        return new Project(item.getId(),item.getName(),item.getBudget(),item.getStartDate(),item.getEndDate(),
//                item.getRepository(),item.getBoss());
//    }
//
//    @Override
//    public ProjectDTO toDTO(Project item) {
//        return new ProjectDTO(item.getId(),item.getName(),item.getBudget(),item.getStartDate(),item.getEndDate(),
//                item.getRepository(),item.getBoss());
//    }
//}
