package dao;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Department {
    private UUID id;
    private String name;
    private double budget;
    private Set<Project> ongoingProjects;
    private Set<Project> endedProjects;
    private Programmer boss;
    private Set<Programmer> historicBosses;

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    @Embedded
    public Programmer getBoss() {
        return boss;
    }

    public void setBoss(Programmer boss) {
        this.boss = boss;
    }

    @ManyToMany
    @JoinTable(
            name = "historic_bosses",
            joinColumns = @JoinColumn(name = "id_department"),
            inverseJoinColumns = @JoinColumn(name = "id_boss"))
    public Set<Programmer> getHistoricBosses() {
        return historicBosses;
    }

    public void setHistoricBosses(Set<Programmer> historicBosses) {
        this.historicBosses = historicBosses;
    }
}
