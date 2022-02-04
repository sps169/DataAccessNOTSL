package dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
/**
 * Clase POJO que modela un Project. Implementa Etiquetas JPA para el modelo
 * de la base de datos.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Project.findAll", query = "SELECT l FROM Project l")
})
public class Project {
    private String id;
    private String name;
    private double budget;
    private Date startDate;
    private Date endDate;
    private Set<Technology> technologies;
    private Repository repository;
    private Programmer boss;

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

    @Basic
    @Column(nullable = false)
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    public Set<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<Technology> technologies) {
        this.technologies = technologies;
    }

    @OneToOne
    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @OneToOne (cascade = CascadeType.DETACH, orphanRemoval = false)
    public Programmer getBoss() {
        return boss;
    }

    public void setBoss(Programmer boss) {
        this.boss = boss;
    }
    /**
     * metodo toString sin recursividad
     * @return String del objeto
     */
    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                ", budget:" + budget +
                ", startDate:" + startDate +
                ", endDate:" + endDate +
                '}';
    }

    /**
     * metodo toString ampliado con relaciones
     * @return String completa del objeto
     */
    public String fullToString() {
        return "Project{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                ", budget:" + budget +
                ", startDate:" + startDate +
                ", endDate:" + endDate +
                ", technologies:" + technologies +
                ", repository:" + repository +
                ", boss:" + boss +
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
        Project that = (Project) o;
        return Objects.equals(id, that.id);

    }
}
