package dao;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Clase POJO que modela un Commit. Implementa Etiquetas JPA para el modelo
 * de la base de datos.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Commit.findAll", query = "SELECT l FROM Commit l")
})

public class Commit {
    private String id;
    private String title;
    private String text;
    private Date date;
    private Repository repository;
    private Issue issue;
    private Project project;
    private Programmer programmer;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @OneToOne
    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    /**
     * metodo toString ampliado con relaciones
     * @return String completa del objeto
     */
    public String fullToString() {
        return "Commit{" +
                "id:'" + id + '\'' +
                ", title:'" + title + '\'' +
                ", text:'" + text + '\'' +
                ", date:" + date +
                ", repository:" + repository +
                ", issue:" + issue +
                ", project:" + project +
                ", programmer:" + programmer +
                '}';
    }

    /**
     * metodo toString sin recursividad
     * @return String del objeto
     */
    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"title\":\"" + title + '\"' +
                ", \"text\":\"" + text + '\"' +
                ", \"date\":\"" + date + "\"" +
                '}';
    }

    /**
     * metodo equals
     * @param o objeto al que compara
     * @return true si el id coincide, false si no
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commit commit = (Commit) o;
        return Objects.equals(id, commit.id);
    }
}
