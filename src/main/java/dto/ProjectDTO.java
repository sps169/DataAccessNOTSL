package dto;

import com.fasterxml.jackson.annotation.JsonValue;
import dao.Programmer;
import dao.Repository;
import dao.Technology;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
/**
 * Clase POJO de transporte de datos para project. Implementa
 * etiquetas JSON para su serialización
 */
public class ProjectDTO {
    private String id;
    private String name;
    private double budget;
    private Date startDate;
    private Date endDate;
    private Set<Technology> technologies;
    private Repository repository;
    private Programmer boss;
    /**
     * metodo toString
     * @return String representando al objeto
     */
    @Override
    @JsonValue
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", technologies=" + technologies +
                ", repository=" + repository +
                ", boss=" + boss +
                '}';
    }
    /**
     * metodo equals
     * @param o objeto a comparar
     * @return true si la id coincide, false si no
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTO that = (ProjectDTO) o;
        return Objects.equals(id, that.id);

    }

}
