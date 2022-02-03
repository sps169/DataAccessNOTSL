package dto;

import dao.Issue;
import dao.Programmer;
import dao.Project;
import dao.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class CommitDTO {
    private String id;
    private String title;
    private String text;
    private Date date;
    private Repository repository;
    private Issue issue;
    private Project project;
    private Programmer programmer;

    @Override
    public String toString() {
        return "CommitDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", repository=" + repository +
                ", issue=" + issue +
                ", project=" + project +
                ", programmer=" + programmer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitDTO commitDTO = (CommitDTO) o;
        return Objects.equals(id, commitDTO.id);
    }
}
