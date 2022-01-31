//package dao;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Type;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.Set;
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@NamedQueries({
//        @NamedQuery(name = "Repository.findAll", query = "SELECT l FROM Repository l")
//})
//public class Repository {
//    private String id;
//    private String name;
//    private LocalDateTime creationDate;
//    private Project project;
//    private Set<Commit> commits;
//    private Set<Issue> issues;
//
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name="uuid", strategy="uuid2")
//    @Type(type = "objectid")
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(nullable = false)
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Column(name = "creation_date", nullable = false)
//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(LocalDateTime creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    @OneToOne
//    @JoinColumn(name = "id_project", referencedColumnName = "id")
//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repository", cascade = CascadeType.REMOVE)
//    public Set<Commit> getCommits() {
//        return commits;
//    }
//
//    public void setCommits(Set<Commit> commits) {
//        this.commits = commits;
//    }
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repository", cascade = CascadeType.REMOVE)
//    public Set<Issue> getIssues() {
//        return issues;
//    }
//
//    public void setIssues(Set<Issue> issues) {
//        this.issues = issues;
//    }
//
//
//    public String basicToString() {
//        return "Repository{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", creationDate=" + creationDate +
//                ", project=" + project.basicToString() +
//                '}';
//    }
//}
