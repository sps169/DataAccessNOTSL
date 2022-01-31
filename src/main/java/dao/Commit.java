//package dao;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Type;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@NamedQueries({
//        @NamedQuery(name = "Commit.findAll", query = "SELECT l FROM Commit l")
//})
//public class Commit {
//    private String id;
//    private String title;
//    private String text;
//    private LocalDateTime date;
//    private Repository repository;
//    private Project project;
//    private Programmer programmer;
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
//    @ManyToOne
//    public Repository getRepository() {
//        return repository;
//    }
//
//    public void setRepository(Repository repository) {
//        this.repository = repository;
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
//    @ManyToOne
//    public Programmer getProgrammer() {
//        return programmer;
//    }
//
//    public void setProgrammer(Programmer programmer) {
//        this.programmer = programmer;
//    }
//
//    public String basicToString() {
//        return "Commit{" +
//                "id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", text='" + text + '\'' +
//                ", date=" + date +
//                ", repository=" + repository.basicToString() +
//                ", project=" + project.basicToString() +
//                ", programmer=" + programmer.basicToString() +
//                '}';
//    }
//}
