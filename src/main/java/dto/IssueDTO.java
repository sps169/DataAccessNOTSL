//package dto;
//
//import dao.Programmer;
//import dao.Project;
//import dao.Repository;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//import java.util.Set;
//
//public class IssueDTO {
//    private String id;
//    private String title;
//    private String text;
//    private LocalDateTime date;
//    private Set<Programmer> programmers;
//    private Project project;
//    private Repository repository;
//
//    public IssueDTO(String id, String title, String text, LocalDateTime date, Set<Programmer> programmers, Project project, Repository repository) {
//        this.id = id;
//        this.title = title;
//        this.text = text;
//        this.date = date;
//        this.programmers = programmers;
//        this.project = project;
//        this.repository = repository;
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
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public Set<Programmer> getProgrammers() {
//        return programmers;
//    }
//
//    public void setProgrammers(Set<Programmer> programmers) {
//        this.programmers = programmers;
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
//    public Repository getRepository() {
//        return repository;
//    }
//
//    public void setRepository(Repository repository) {
//        this.repository = repository;
//    }
//    public String basicToString() {
//        return "IssueDTO{" +
//                "id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", text='" + text + '\'' +
//                ", date=" + date +
//                ", project=" + project.basicToString() +
//                ", repository=" + repository.basicToString() +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        IssueDTO issueDTO = (IssueDTO) o;
//        return Objects.equals(id, issueDTO.id)
//                && Objects.equals(title, issueDTO.title)
//                && Objects.equals(text, issueDTO.text)
//                && Objects.equals(date, issueDTO.date)
//                && Objects.equals(programmers, issueDTO.programmers)
//                && Objects.equals(project, issueDTO.project)
//                && Objects.equals(repository, issueDTO.repository);
//    }
//}
