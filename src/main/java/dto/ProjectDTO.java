package dto;

import dao.Programmer;
import dao.Repository;
import dao.Technology;
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
public class ProjectDTO {
    private String id;
    private String name;
    private double budget;
    private Date startDate;
    private Date endDate;
    private Set<Technology> technologies;
    private Repository repository;
    private Programmer boss;


    public String basicToString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", repository=" + repository.basicToString() +
                ", boss=" + boss.basicToString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTO that = (ProjectDTO) o;
        return Objects.equals(id, that.id);

    }

}
