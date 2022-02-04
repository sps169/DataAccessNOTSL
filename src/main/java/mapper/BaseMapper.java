package mapper;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Interfaz de mapeo de datos.
 */
public abstract class BaseMapper<T, DTO> {
    /**
     * metodo para mapear desde un listado de Dto a Dao
     * @return List de dao
     */
    public List<T> fromDTO(List<DTO> items) {
        return items.stream().map(this::fromDTO).collect(Collectors.toList());
    }
    /**
     * metodo para mapear desde un Dto a Dao
     * @return dao
     */
    public abstract T fromDTO(DTO item);

    /**
     * metodo para mapear desde un listado de Dao a DTO
     * @return List de DTO
     */
    public List<DTO> toDTO(List<T> items) {
        return items.stream().map(this::toDTO).collect(Collectors.toList());
    }
    /**
     * metodo para mapear desde un Dao a DTO
     * @return DTO
     */
    public abstract DTO toDTO(T item);
}
