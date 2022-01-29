package dao;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Department {
    private String id;
    private String name;
    private double budget;
    private Set<Project> ongoingProjects;
    private Set<Project> endedProjects;
    private Programmer boss;
    private Set<Programmer> historicBosses;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    @Type(type = "objectid")
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

    @OneToOne
    public Programmer getBoss() {
        return boss;
    }

    public void setBoss(Programmer boss) {
        this.boss = boss;
    }

    @ManyToMany
    public Set<Programmer> getHistoricBosses() {
        return historicBosses;
    }

    public void setHistoricBosses(Set<Programmer> historicBosses) {
        this.historicBosses = historicBosses;
    }
}
