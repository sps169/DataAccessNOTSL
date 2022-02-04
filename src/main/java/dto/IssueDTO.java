package dto;

import com.fasterxml.jackson.annotation.JsonValue;
import dao.Programmer;
import dao.Project;
import dao.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
/**
 * Clase POJO de transporte de datos para issue. Implementa
 * etiquetas JSON para su serialización
 */
public class IssueDTO {
    private String id;
    private String title;
    private String text;
    private Date date;
    private Set<Programmer> programmers;
    private Project project;
    private Repository repository;
    /**
     * metodo toString
     * @return String representando al objeto
     */
    @Override
    @JsonValue
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", project=" + project +
                ", repository=" + repository +
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
        IssueDTO issueDTO = (IssueDTO) o;
        return Objects.equals(id, issueDTO.id);
    }
}
