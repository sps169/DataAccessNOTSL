package mapper;

import dao.Department;
import dto.DepartmentDTO;
/**
 * Clase de mapeo de datos de Department y DepartmentDTO. Implementa interfaz BaseMapper
 */
public class DepartmentMapper extends BaseMapper<Department, DepartmentDTO> {
    /**
     * metodo para mapear desde un DepartmentDto a Department
     * @param item DepartmentDTO
     * @return Department
     */
    @Override
    public Department fromDTO(DepartmentDTO item) {
        return new Department(item.getId(), item.getName(),item.getBudget(),item.getOngoingProjects(),
                item.getEndedProjects(),item.getBoss(),item.getHistoricBosses());
    }
    /**
     * metodo para mapear desde un Department a DepartmentDto
     * @param item Department
     * @return DepartmentDTO
     */
    @Override
    public DepartmentDTO toDTO(Department item) {
        return new DepartmentDTO(item.getId(), item.getName(),item.getBudget(),item.getOngoingProjects(),
                item.getEndedProjects(),item.getBoss(),item.getHistoricBosses());
    }
}
