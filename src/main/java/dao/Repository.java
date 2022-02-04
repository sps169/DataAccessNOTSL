package dao;

import dto.RepositoryDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Repository.findAll", query = "SELECT l FROM Repository l")
})
public class Repository {
    private String id;
    private String name;
    private Date creationDate;
    private Project project;
    private Set<Commit> commits;
    private Set<Issue> issues;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @OneToOne
    @JoinColumn(name = "id_project", referencedColumnName = "id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repository", cascade = CascadeType.REMOVE)
    public Set<Commit> getCommits() {
        return commits;
    }

    public void setCommits(Set<Commit> commits) {
        this.commits = commits;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repository", cascade = CascadeType.REMOVE)
    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                ", creationDate:" + creationDate +
                '}';
    }

    public String fullToString() {
        return "Repository{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                ", creationDate:" + creationDate +
                ", project:" + project +
                ", commits:" + commits +
                ", issues:" + issues +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(id, that.id);
    }
}
