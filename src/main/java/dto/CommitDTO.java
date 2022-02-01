//package dto;
//
//import dao.Programmer;
//import dao.Project;
//import dao.Repository;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//public class CommitDTO {
//    private String id;
//    private String title;
//    private String text;
//    private LocalDateTime date;
//    private Repository repository;
//    private Project project;
//    private Programmer programmer;
//
//    public CommitDTO(String id, String title, String text, LocalDateTime date, Repository repository, Project project, Programmer programmer) {
//        this.id = id;
//        this.title = title;
//        this.text = text;
//        this.date = date;
//        this.repository = repository;
//        this.project = project;
//        this.programmer = programmer;
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
//    public Repository getRepository() {
//        return repository;
//    }
//
//    public void setRepository(Repository repository) {
//        this.repository = repository;
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
//    public Programmer getProgrammer() {
//        return programmer;
//    }
//
//    public void setProgrammer(Programmer programmer) {
//        this.programmer = programmer;
//    }
//
//    @Override
//    public String toString() {
//        return "CommitDTO{" +
//                "id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", text='" + text + '\'' +
//                ", date=" + date +
//                ", repository=" + repository.toString() +
//                ", project=" + project.toString() +
//                ", programmer=" + programmer.basicToString() +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CommitDTO commitDTO = (CommitDTO) o;
//        return Objects.equals(id, commitDTO.id)
//                && Objects.equals(title, commitDTO.title)
//                && Objects.equals(text, commitDTO.text)
//                && Objects.equals(date, commitDTO.date)
//                && Objects.equals(repository, commitDTO.repository)
//                && Objects.equals(project, commitDTO.project)
//                && Objects.equals(programmer, commitDTO.programmer);
//    }
//}
