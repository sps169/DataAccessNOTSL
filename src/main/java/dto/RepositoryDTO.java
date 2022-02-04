package dto;

import com.fasterxml.jackson.annotation.JsonValue;
import dao.Commit;
import dao.Issue;
import dao.Project;
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
 * Clase POJO de transporte de datos para repository. Implementa
 * etiquetas JSON para su serialización
 */
public class RepositoryDTO {
    private String id;
    private String name;
    private Date creationDate;
    private Project project;
    private Set<Commit> commits;
    private Set<Issue> issues;

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
                ", creationDate=" + creationDate +
                ", project=" + project +
                ", commits=" + commits +
                ", issues=" + issues +
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
        RepositoryDTO that = (RepositoryDTO) o;
        return Objects.equals(id, that.id);
    }
}
