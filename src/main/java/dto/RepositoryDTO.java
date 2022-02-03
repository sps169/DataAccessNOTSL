package dto;

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

    public String basicToString() {
        return "RepositoryDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", project=" + project.basicToString() +
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
