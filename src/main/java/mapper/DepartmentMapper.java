//package mapper;
//
//import dao.Department;
//import dto.DepartmentDTO;
//
//public class DepartmentMapper extends BaseMapper<Department, DepartmentDTO> {
//    @Override
//    public Department fromDTO(DepartmentDTO item) {
//        return new Department(item.getId(), item.getName(),item.getBudget(),item.getOngoingProjects(),
//                item.getEndedProjects(),item.getBoss(),item.getHistoricBosses());
//    }
//
//    @Override
//    public DepartmentDTO toDTO(Department item) {
//        return new DepartmentDTO(item.getId(), item.getName(),item.getBudget(),item.getOngoingProjects(),
//                item.getEndedProjects(),item.getBoss(),item.getHistoricBosses());
//    }
//}
