package dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Clase POJO que modela un Issue. Implementa Etiquetas JPA para el modelo
 * de la base de datos.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Issue.findAll", query = "SELECT l FROM Issue l")
})

public class Issue {
    private String id;
    private String title;
    private String text;
    private Date date;
    private Set<Programmer> programmers;
    private Project project;
    private Repository repository;

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

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Programmer> getProgrammers() {
        return programmers;
    }

    public void setProgrammers(Set<Programmer> programmers) {
        this.programmers = programmers;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    @ManyToOne
    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    /**
     * metodo toString sin recursividad
     * @return String del objeto
     */
    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
    /**
     * metodo toString ampliado con relaciones
     * @return String completa del objeto
     */
    public String fullToString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", programmers=" + programmers +
                ", project=" + project +
                ", repository=" + repository +
                '}';
    }
    /**
     * metodo equals
     * @param o objeto a comparar
     * @return true si la id coincide, false si no
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(id, issue.id);
    }
}
