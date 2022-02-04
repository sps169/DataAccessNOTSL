package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import dao.Commit;
import dao.Issue;
import dao.Project;
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
public class RepositoryDTO {
    private String id;
    private String name;
    private Date creationDate;
    private Project project;
    private Set<Commit> commits;
    private Set<Issue> issues;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositoryDTO that = (RepositoryDTO) o;
        return Objects.equals(id, that.id);
    }
}
