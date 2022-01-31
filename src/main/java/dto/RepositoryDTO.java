//package dto;
//
//import dao.Commit;
//import dao.Issue;
//import dao.Project;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//import java.util.Set;
//
//public class RepositoryDTO {
//    private String id;
//    private String name;
//    private LocalDateTime creationDate;
//    private Project project;
//    private Set<Commit> commits;
//    private Set<Issue> issues;
//
//    public RepositoryDTO(String id, String name, LocalDateTime creationDate, Project project, Set<Commit> commits, Set<Issue> issues) {
//        this.id = id;
//        this.name = name;
//        this.creationDate = creationDate;
//        this.project = project;
//        this.commits = commits;
//        this.issues = issues;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(LocalDateTime creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }
//
//    public Set<Commit> getCommits() {
//        return commits;
//    }
//
//    public void setCommits(Set<Commit> commits) {
//        this.commits = commits;
//    }
//
//    public Set<Issue> getIssues() {
//        return issues;
//    }
//
//    public void setIssues(Set<Issue> issues) {
//        this.issues = issues;
//    }
//
//    public String basicToString() {
//        return "RepositoryDTO{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", creationDate=" + creationDate +
//                ", project=" + project.basicToString() +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RepositoryDTO that = (RepositoryDTO) o;
//        return Objects.equals(id, that.id)
//                && Objects.equals(name, that.name)
//                && Objects.equals(creationDate, that.creationDate)
//                && Objects.equals(project, that.project)
//                && Objects.equals(commits, that.commits)
//                && Objects.equals(issues, that.issues);
//    }
//}
