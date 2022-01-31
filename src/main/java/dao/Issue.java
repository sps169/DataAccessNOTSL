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
//        @NamedQuery(name = "Issue.findAll", query = "SELECT l FROM Issue l")
//})
//public class Issue {
//    private String id;
//    private String title;
//    private String text;
//    private LocalDateTime date;
//    private Set<Programmer> programmers;
//    private Project project;
//    private Repository repository;
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
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    @Basic
//    @Column(nullable = false)
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    @Column(nullable = false)
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    @ManyToMany
//    public Set<Programmer> getProgrammers() {
//        return programmers;
//    }
//
//    public void setProgrammers(Set<Programmer> programmers) {
//        this.programmers = programmers;
//    }
//
//    @ManyToOne
//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }
//
//
//    @ManyToOne
//    public Repository getRepository() {
//        return repository;
//    }
//
//    public void setRepository(Repository repository) {
//        this.repository = repository;
//    }
//
//    public String basicToString() {
//        return "Issue{" +
//                "id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", text='" + text + '\'' +
//                ", date=" + date +
//                ", project=" + project.basicToString() +
//                ", repository=" + repository.basicToString() +
//                '}';
//    }
//}
