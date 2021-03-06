package dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
/**
 * Clase POJO que modela un Department. Implementa Etiquetas JPA para el modelo
 * de la base de datos.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Department.findAll", query = "SELECT l FROM Department l")
})

public class Department {
    private String id;
    private String name;
    private double budget;
    private Set<Project> ongoingProjects;
    private Set<Project> endedProjects;
    private Programmer boss;
    private Set<Programmer> historicBosses;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "budget", nullable = false)
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public Set<Project> getOngoingProjects() {
        return ongoingProjects;
    }

    public void setOngoingProjects(Set<Project> ongoingProjects) {
        this.ongoingProjects = ongoingProjects;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public Set<Project> getEndedProjects() {
        return endedProjects;
    }

    public void setEndedProjects(Set<Project> endedProjects) {
        this.endedProjects = endedProjects;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Programmer getBoss() {
        return boss;
    }

    public void setBoss(Programmer boss) {
        this.boss = boss;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Programmer> getHistoricBosses() {
        return historicBosses;
    }

    public void setHistoricBosses(Set<Programmer> historicBosses) {
        this.historicBosses = historicBosses;
    }
    /**
     * metodo toString sin recursividad
     * @return String del objeto
     */
    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }
    /**
     * metodo toString ampliado con relaciones
     * @return String completa del objeto
     */
    public String fullToString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", ongoingProjects=" + ongoingProjects +
                ", endedProjects=" + endedProjects +
                ", boss=" + boss +
                ", historicBosses=" + historicBosses +
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
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }
}
