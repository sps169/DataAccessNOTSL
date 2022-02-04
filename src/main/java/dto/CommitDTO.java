package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import dao.Issue;
import dao.Programmer;
import dao.Project;
import dao.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
/**
 * Clase POJO de transporte de datos para commit. Implementa
 * etiquetas JSON para su serialización
 */
public class CommitDTO {
    private String id;
    private String title;
    private String text;
    private Date date;
    private Repository repository;
    private Issue issue;
    private Project project;
    private Programmer programmer;

    /**
     * metodo toString
     * @return String representando al objeto
     */
    @Override
    @JsonValue
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", title:'" + title + '\'' +
                ", text:'" + text + '\'' +
                ", date:" + date +
                ", repository:" + repository +
                ", issue:" + issue +
                ", project:" + project +
                ", programmer:" + programmer +
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
        CommitDTO commitDTO = (CommitDTO) o;
        return Objects.equals(id, commitDTO.id);
    }
}
